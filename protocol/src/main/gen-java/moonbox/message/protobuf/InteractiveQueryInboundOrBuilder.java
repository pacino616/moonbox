// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto_message.proto

package moonbox.message.protobuf;

public interface InteractiveQueryInboundOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protobuf.InteractiveQueryInbound)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string token = 1;</code>
   */
  java.lang.String getToken();
  /**
   * <code>string token = 1;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <code>string session_id = 2;</code>
   */
  java.lang.String getSessionId();
  /**
   * <code>string session_id = 2;</code>
   */
  com.google.protobuf.ByteString
      getSessionIdBytes();

  /**
   * <pre>
   *Seq[String]
   * </pre>
   *
   * <code>repeated string sql = 3;</code>
   */
  java.util.List<java.lang.String>
      getSqlList();
  /**
   * <pre>
   *Seq[String]
   * </pre>
   *
   * <code>repeated string sql = 3;</code>
   */
  int getSqlCount();
  /**
   * <pre>
   *Seq[String]
   * </pre>
   *
   * <code>repeated string sql = 3;</code>
   */
  java.lang.String getSql(int index);
  /**
   * <pre>
   *Seq[String]
   * </pre>
   *
   * <code>repeated string sql = 3;</code>
   */
  com.google.protobuf.ByteString
      getSqlBytes(int index);

  /**
   * <code>.google.protobuf.Int32Value fetch_size = 4;</code>
   */
  boolean hasFetchSize();
  /**
   * <code>.google.protobuf.Int32Value fetch_size = 4;</code>
   */
  com.google.protobuf.Int32Value getFetchSize();
  /**
   * <code>.google.protobuf.Int32Value fetch_size = 4;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getFetchSizeOrBuilder();

  /**
   * <code>.google.protobuf.Int64Value max_rows = 5;</code>
   */
  boolean hasMaxRows();
  /**
   * <code>.google.protobuf.Int64Value max_rows = 5;</code>
   */
  com.google.protobuf.Int64Value getMaxRows();
  /**
   * <code>.google.protobuf.Int64Value max_rows = 5;</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getMaxRowsOrBuilder();
}
