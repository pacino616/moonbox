// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto_message.proto

package moonbox.message.protobuf;

public interface CellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protobuf.Cell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string string_value = 1;</code>
   */
  java.lang.String getStringValue();
  /**
   * <code>string string_value = 1;</code>
   */
  com.google.protobuf.ByteString
      getStringValueBytes();

  /**
   * <pre>
   * long, timestamp, date
   * </pre>
   *
   * <code>int64 long_value = 2;</code>
   */
  long getLongValue();

  /**
   * <pre>
   * byte, short, int
   * </pre>
   *
   * <code>int32 int_value = 3;</code>
   */
  int getIntValue();

  /**
   * <code>bool boolean_value = 4;</code>
   */
  boolean getBooleanValue();

  /**
   * <pre>
   * java.math.BigDecimal
   * </pre>
   *
   * <code>.protobuf.BDecimal big_decimal = 5;</code>
   */
  boolean hasBigDecimal();
  /**
   * <pre>
   * java.math.BigDecimal
   * </pre>
   *
   * <code>.protobuf.BDecimal big_decimal = 5;</code>
   */
  moonbox.message.protobuf.BDecimal getBigDecimal();
  /**
   * <pre>
   * java.math.BigDecimal
   * </pre>
   *
   * <code>.protobuf.BDecimal big_decimal = 5;</code>
   */
  moonbox.message.protobuf.BDecimalOrBuilder getBigDecimalOrBuilder();

  /**
   * <pre>
   * Array[Byte] and other java objects
   * </pre>
   *
   * <code>bytes byte_array = 6;</code>
   */
  com.google.protobuf.ByteString getByteArray();

  /**
   * <code>double double_value = 7;</code>
   */
  double getDoubleValue();

  /**
   * <pre>
   * map v6 = 14;
   * struct v6 = 14;
   * </pre>
   *
   * <code>float float_value = 8;</code>
   */
  float getFloatValue();

  public moonbox.message.protobuf.Cell.ObjectsCase getObjectsCase();
}
