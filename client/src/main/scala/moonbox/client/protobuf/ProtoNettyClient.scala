package moonbox.client.protobuf

import java.io.IOException
import java.net.{InetSocketAddress, SocketAddress}
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

import io.netty.bootstrap.Bootstrap
import io.netty.channel._
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.protobuf.{ProtobufDecoder, ProtobufEncoder, ProtobufVarint32FrameDecoder, ProtobufVarint32LengthFieldPrepender}
import io.netty.util.concurrent.DefaultThreadFactory
import moonbox.client.entity.{JobState, MoonboxRow, MoonboxRowSet}
import moonbox.client.{ClientInterface, ClientOptions}
import moonbox.message.protobuf._
import moonbox.protocol.DataType
import moonbox.protocol.DataType._
import moonbox.protocol.util.{JavaSerializer, ProtoInboundMessageBuilder, SchemaUtil}

import scala.collection.JavaConverters._

private[client] object ProtoNettyClient {
  val daemonNioEventLoopGroup = new NioEventLoopGroup(0, new DefaultThreadFactory(this.getClass, true))
  val nioEventLoopGroup = new NioEventLoopGroup()
}

private[client] class ProtoNettyClient(clientOptions: ClientOptions) extends ClientInterface {

  import ProtoNettyClient._

  def this(host: String, port: Int, timeout: Int) = this(ClientOptions.builder().host(host).port(port).timeout(timeout).build())
  def this(host: String, port: Int) = this(ClientOptions.builder().host(host).port(port).build())
  def this(host: String) = this(ClientOptions.builder().host(host).build())
  def this() = this(ClientOptions.builder().build())

  /* val */
  private val host = clientOptions.host
  private val port = clientOptions.port
  private val CONNECTION_TIMEOUT_MILLIS = 1000 * 60 // ms
  private val messageId = new AtomicLong(0L)
  private val promises = new ConcurrentHashMap[Long, ChannelPromise]
  private val responses = new ConcurrentHashMap[Long, ProtoMessage]
  private val callbacks = new ConcurrentHashMap[Long, ProtoMessage => Any]
  /* var */
  private var channel: Channel = _
  private var connected: Boolean = false
  private var readTimeout: Int = 1000 * clientOptions.timeout // time unit: ms
  private var dataFetchClient: ProtoNettyClient = _

  @throws(classOf[Exception])
  def connect(): ProtoNettyClient = {
    if (isConnected()) {
      return this
    }
    val bootstrap = new Bootstrap()
      .group(daemonNioEventLoopGroup)
      .channel(classOf[NioSocketChannel])
      .option[java.lang.Boolean](ChannelOption.SO_KEEPALIVE, true)
      .option[java.lang.Boolean](ChannelOption.TCP_NODELAY, true)
      .option[java.lang.Integer](ChannelOption.SO_RCVBUF, 10240)
      .option[java.lang.Integer](ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT_MILLIS)
      .handler(new ChannelInitializer[SocketChannel]() {
        override def initChannel(ch: SocketChannel) = {
          ch.pipeline
            .addLast(new ProtobufVarint32FrameDecoder())
            .addLast("decoder", new ProtobufDecoder(ProtoMessage.getDefaultInstance))
            .addLast("protobuf message handler", new ProtoMessageHandler(promises, responses, callbacks))
            .addLast(new ProtobufVarint32LengthFieldPrepender())
            .addLast("encoder", new ProtobufEncoder())
        }
      })
    val channelFuture = bootstrap.connect(new InetSocketAddress(host, port))
    if (!channelFuture.await(CONNECTION_TIMEOUT_MILLIS)) {
      throw new IOException(s"Connecting to $host timed out ($CONNECTION_TIMEOUT_MILLIS ms)")
    } else if (channelFuture.cause != null) {
      channelFuture.cause.printStackTrace()
      throw new IOException(s"Failed to connect to $host", channelFuture.cause)
    }
    channel = channelFuture.channel()
    this.connected = true
    this
  }

  override def setReadTimeout(milliseconds: Int): Unit = {
    readTimeout = milliseconds
  }
  /* time unit: ms */
  override def getReadTimeout(): Int = readTimeout
  override def getRemoteAddress(): SocketAddress = {
    if (channel != null && isConnected) channel.remoteAddress()
    else throw new ChannelException("Channel unestablished")
  }

  def genMessageId: Long = messageId.getAndIncrement()
  def sendWithCallback(msg: Any, callback: Any => Any) = {
    msg match {
      case in: ProtoMessage =>
        val msgId = in.getMessageId
        callbacks.put(msgId, callback)
        sendMessage(msg)
      case other => throw new Exception(s"Unsupported message: $other")
    }
  }
  @throws(classOf[Exception])
  def sendMessageSync(message: Any, timeout: Long = readTimeout): ProtoMessage = {
    message match {
      case in: ProtoMessage =>
        val messageId = in.getMessageId
        try {
          val promise = channel.newPromise()
          promises.put(messageId, promise)
          sendMessage(in)
          if (timeout == 0) {
            promise.await()
          } else if (!promise.await(timeout)) {
            throw new Exception(s"No response within $timeout ms")
          }
          if (!promise.isSuccess) {
            throw promise.cause()
          }
          responses.get(messageId)
        } finally release(messageId)
      case other => throw new Exception(s"Unsupported message: $other")
    }
  }
  @throws(classOf[Exception]) def sendMessage(message: Any): ChannelFuture = channel.writeAndFlush(message)
  def isConnected(): Boolean = this.connected
  def isActive(): Boolean = channel.isActive
  def close(): Unit = {
    /* EventLoopGroup should not be shutdown */
    if (channel != null) channel.close()
  }
  private def release(key: Long): Unit = {
    promises.remove(key)
    responses.remove(key)
  }

  override def setDataFetchClient(client: ClientInterface) = {
    client match {
      case proto: ProtoNettyClient => dataFetchClient = proto
      case other => throw new Exception(s"DataFetchClient type should be consistent with ${this.getClass.getTypeName}, but got ${other.getClass.getTypeName}")
    }
  }
  override def login(username: String, password: String): String = {
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setLoginInbound(ProtoInboundMessageBuilder.loginInbound(username, password))
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasLoginOutbound) {
      val out = resp.getLoginOutbound
      out.getError match {
        case "" | null => out.getToken
        case error => throw new Exception(s"USER($username) login failed: ERROR=$error, TOKEN=${out.getToken}")
      }
    } else throw new Exception(s"Invalid message format: $resp")
  }
  override def logout(token: String): Boolean = {
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setLogoutInbound(ProtoInboundMessageBuilder.logoutInbound(token))
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasLogoutOutbound) {
      val out = resp.getLogoutOutbound
      out.getError match {
        case "" | null => true
        case error => throw new Exception(s"Logout failed: ERROR=$error, TOKEN=$token")
      }
    } else throw new Exception(s"Invalid message format: $resp")
  }

  override def openSession(token: String, database: String, isLocal: Boolean): (String, String, Int) = {
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setOpenSessionInbound(ProtoInboundMessageBuilder.openSessionInbound(token, database, isLocal))
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasOpenSessionOutbound) {
      val out = resp.getOpenSessionOutbound
      out.getError match {
        case "" | null => (out.getSessionId, out.getWorkerHost, out.getWorkerPort)
        case error => throw new Exception(s"Open session failed: ERROR=$error, TOKEN=$token, SessionId=${out.getSessionId}")
      }
    } else throw new Exception(s"Invalid message format: $resp")
  }

  override def closeSession(token: String, sessionId: String): Boolean = {
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setCloseSessionInbound(ProtoInboundMessageBuilder.closeSessionInbound(token, sessionId))
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasCloseSessionOutbound) {
      val out = resp.getCloseSessionOutbound
      out.getError match {
        case "" | null => true
        case error => throw new Exception(s"Close session failed: ERROR=$error, TOKEN=$token, SessionId=$sessionId")
      }
    } else throw new Exception(s"Invalid message format: $resp")
  }

  override def interactiveQuery(token: String, sessionId: String, sqls: Seq[String], fetchSize: Int, timeout: Int): MoonboxRowSet = {
    val outbound = query(token, sessionId, sqls, fetchSize, timeout)
    toMoonboxRowSet(sessionId, fetchSize, outbound, timeout)
  }

  override def batchQuery(token: String, sqls: Seq[String], config: String): String = {
    checkConnected()
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setBatchQueryInbound(ProtoInboundMessageBuilder.batchQueryInbound(token, sqls.asJava, config))
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasBatchQueryOutbound) {
      val out = resp.getBatchQueryOutbound
      out.getError match {
        case "" | null => out.getJobId
        case error => throw new Exception(s"Batch query error: ERROR=$error, SQLs=$sqls")
      }
    } else throw new Exception(s"Unknown message: $resp")
  }

  override def batchQueryProgress(token: String, jobId: String): JobState = {
    checkConnected()
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setBatchQueryProgressInbound(BatchQueryProgressInbound.newBuilder().setToken(token).setJobId(jobId).build())
      .build()
    val resp = sendMessageSync(msg)
    if (resp.hasBatchQueryProgressOutbound){
      val out = resp.getBatchQueryProgressOutbound
      JobState(out.getMessage, out.getState)
    } else throw new Exception(s"Unknown message: $resp")
  }
  override def cancelQuery(token: String, jobId: String, sessionId: String): Boolean = {
    checkConnected()
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setCancelQueryInbound(ProtoInboundMessageBuilder.cancelQueryInbound(token, jobId, sessionId))
      .build()
    val resp = sendMessageSync(msg, CONNECTION_TIMEOUT_MILLIS)
    if (resp.hasCancelQueryOutbound) {
      val out = resp.getCancelQueryOutbound
      out.getError match {
        case "" | null => true
        case error => throw new Exception(s"Cancel query error: ERROR=$error, TOKEN=$token, JobId=$jobId, SessionId=$sessionId")
      }
    } else throw new Exception(s"Unknown message: $resp")
  }

  /* for interactive query */
  private def query(token: String, sessionId: String, sqls: Seq[String], fetchSize: Int, timeout: Int): InteractiveQueryOutbound = {
    checkConnected()
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setInteractiveQueryInbound(ProtoInboundMessageBuilder.interactiveQueryInbound(token, sessionId, sqls.asJava, Some(fetchSize), None))
      .build()
    val resp = sendMessageSync(msg, timeout)
    if (resp.hasInteractiveQueryOutbound) {
      val out = resp.getInteractiveQueryOutbound
      out.getError match {
        case "" | null => out
        case error => throw new Exception(s"Interactive query error: ERROR=$error, SQLs=$sqls")
      }
    } else throw new Exception(s"Unknown message: $resp")
  }

  private def interactiveNextResult(sessionId: String, fetchSize: Int, timeout: Int): ResultData = {
    checkConnected()
    val msg = ProtoMessage.newBuilder()
      .setMessageId(genMessageId)
      .setInteractiveNextResultInbound(ProtoInboundMessageBuilder.interactiveNextResultInbound(sessionId, fetchSize))
      .build()
    val resp = dataFetchClient.sendMessageSync(msg, timeout)
    if (resp.hasInteractiveNextResultOutbound){
      val out = resp.getInteractiveNextResultOutbound
      out.getError match {
        case "" | null => out.getData
        case error => throw new Exception(s"Fetch next result error: ERROR=$error")
      }
    } else throw new Exception(s"Unknown message: $resp")
  }

  private def toMoonboxRowSet(sessionId: String, fetchSize: Int, outbound: InteractiveQueryOutbound, timeout: Int): MoonboxRowSet = {
    var resultData: ResultData = {
      if (outbound.hasResultData) {
        outbound.getResultData  /* for direct data */
      } else interactiveNextResult(sessionId, fetchSize, timeout) /* for query sqls */
    }
    val schema = resultData.getSchema
    val dataTypeSchema = schemaToDataType(schema)
    val rowIterator = new Iterator[MoonboxRow] {
      var internalIter = rowsInData(resultData.getData, dataTypeSchema).toIterator
      override def hasNext: Boolean = internalIter.hasNext || resultData.getHasNext
      override def next(): MoonboxRow = {
        if (internalIter.hasNext) {
          new MoonboxRow(internalIter.next().toArray)
        } else if (resultData.getHasNext) {
          resultData = interactiveNextResult(sessionId, fetchSize, timeout)
          internalIter = rowsInData(resultData.getData, dataTypeSchema).toIterator
          next()
        } else throw new Exception("No more iterable MoonboxRow.")
      }
    }
    new MoonboxRowSet(rowIterator.asJava, schema)
  }
  
  private def schemaToDataType(schema: String): Array[DataType] = {
    val parsed = SchemaUtil.parse(schema)
    SchemaUtil.schemaToDataType(parsed).map(_._2)
  }

  private def rowsInData(data: Data, schema: Array[DataType]): Seq[Seq[Any]] = {
    data.getRowList.asScala.map { row =>
      row.getCellList.asScala.zipWithIndex.map(cell => cellConvert(cell._1, schema(cell._2)))
    }
  }
  private def cellConvert(cell: Cell, dataType: DataType): Any = {
    dataType match {
      case DECIMAL => toJavaBigDecimal(cell.getBigDecimal)  /* proto.BDecimal => java.math.BigDecimal */
      case BINARY => cell.getByteArray.toByteArray
      case BOOLEAN => cell.getBooleanValue
      case VARCHAR | STRING => cell.getStringValue
      case TIMESTAMP => new java.sql.Timestamp(cell.getLongValue)  /* long => java.sql.timestamp */
      case DOUBLE => cell.getDoubleValue
      case FLOAT => cell.getFloatValue
      case INTEGER => cell.getIntValue
      case LONG => cell.getLongValue
      case SHORT => cell.getIntValue.toShort  /* int => short */
      case BYTE => cell.getIntValue.toByte  /* int => byte */
      case DATE => new java.sql.Date(cell.getLongValue)  /* long => java.sql.Date */
      case _ => JavaSerializer.deserialize[Object](cell.getByteArray.toByteArray)  /* other types => java.lang.Object deserialized by java serializer */
      // case CHAR =>
      // case NULL =>
      // case OBJECT =>
      // case STRUCT =>
      // case MAP =>
      // case ARRAY =>
    }
  }

  private def toJavaBigDecimal(decimal: BDecimal): java.math.BigDecimal ={
    val intVal = new java.math.BigInteger(decimal.getIntVal.getValue.toByteArray)
    val scale = decimal.getScale
    new java.math.BigDecimal(intVal, scale)
  }

  private def checkConnected(): Unit = {
    if (!isActive) {
      throw new Exception("Connection unestablished.")
    }
  }
}