// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto_message.proto

package moonbox.message.protobuf;

/**
 * Protobuf type {@code protobuf.Row}
 */
public  final class Row extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:protobuf.Row)
    RowOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Row.newBuilder() to construct.
  private Row(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Row() {
    cell_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Row(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              cell_ = new java.util.ArrayList<moonbox.message.protobuf.Cell>();
              mutable_bitField0_ |= 0x00000001;
            }
            cell_.add(
                input.readMessage(moonbox.message.protobuf.Cell.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        cell_ = java.util.Collections.unmodifiableList(cell_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return moonbox.message.protobuf.ProtoMessageOuterClass.internal_static_protobuf_Row_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return moonbox.message.protobuf.ProtoMessageOuterClass.internal_static_protobuf_Row_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            moonbox.message.protobuf.Row.class, moonbox.message.protobuf.Row.Builder.class);
  }

  public static final int CELL_FIELD_NUMBER = 1;
  private java.util.List<moonbox.message.protobuf.Cell> cell_;
  /**
   * <code>repeated .protobuf.Cell cell = 1;</code>
   */
  public java.util.List<moonbox.message.protobuf.Cell> getCellList() {
    return cell_;
  }
  /**
   * <code>repeated .protobuf.Cell cell = 1;</code>
   */
  public java.util.List<? extends moonbox.message.protobuf.CellOrBuilder> 
      getCellOrBuilderList() {
    return cell_;
  }
  /**
   * <code>repeated .protobuf.Cell cell = 1;</code>
   */
  public int getCellCount() {
    return cell_.size();
  }
  /**
   * <code>repeated .protobuf.Cell cell = 1;</code>
   */
  public moonbox.message.protobuf.Cell getCell(int index) {
    return cell_.get(index);
  }
  /**
   * <code>repeated .protobuf.Cell cell = 1;</code>
   */
  public moonbox.message.protobuf.CellOrBuilder getCellOrBuilder(
      int index) {
    return cell_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < cell_.size(); i++) {
      output.writeMessage(1, cell_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < cell_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, cell_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof moonbox.message.protobuf.Row)) {
      return super.equals(obj);
    }
    moonbox.message.protobuf.Row other = (moonbox.message.protobuf.Row) obj;

    boolean result = true;
    result = result && getCellList()
        .equals(other.getCellList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getCellCount() > 0) {
      hash = (37 * hash) + CELL_FIELD_NUMBER;
      hash = (53 * hash) + getCellList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static moonbox.message.protobuf.Row parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static moonbox.message.protobuf.Row parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static moonbox.message.protobuf.Row parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static moonbox.message.protobuf.Row parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static moonbox.message.protobuf.Row parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static moonbox.message.protobuf.Row parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(moonbox.message.protobuf.Row prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code protobuf.Row}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:protobuf.Row)
      moonbox.message.protobuf.RowOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return moonbox.message.protobuf.ProtoMessageOuterClass.internal_static_protobuf_Row_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return moonbox.message.protobuf.ProtoMessageOuterClass.internal_static_protobuf_Row_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              moonbox.message.protobuf.Row.class, moonbox.message.protobuf.Row.Builder.class);
    }

    // Construct using moonbox.message.protobuf.Row.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCellFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (cellBuilder_ == null) {
        cell_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        cellBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return moonbox.message.protobuf.ProtoMessageOuterClass.internal_static_protobuf_Row_descriptor;
    }

    public moonbox.message.protobuf.Row getDefaultInstanceForType() {
      return moonbox.message.protobuf.Row.getDefaultInstance();
    }

    public moonbox.message.protobuf.Row build() {
      moonbox.message.protobuf.Row result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public moonbox.message.protobuf.Row buildPartial() {
      moonbox.message.protobuf.Row result = new moonbox.message.protobuf.Row(this);
      int from_bitField0_ = bitField0_;
      if (cellBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          cell_ = java.util.Collections.unmodifiableList(cell_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.cell_ = cell_;
      } else {
        result.cell_ = cellBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof moonbox.message.protobuf.Row) {
        return mergeFrom((moonbox.message.protobuf.Row)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(moonbox.message.protobuf.Row other) {
      if (other == moonbox.message.protobuf.Row.getDefaultInstance()) return this;
      if (cellBuilder_ == null) {
        if (!other.cell_.isEmpty()) {
          if (cell_.isEmpty()) {
            cell_ = other.cell_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureCellIsMutable();
            cell_.addAll(other.cell_);
          }
          onChanged();
        }
      } else {
        if (!other.cell_.isEmpty()) {
          if (cellBuilder_.isEmpty()) {
            cellBuilder_.dispose();
            cellBuilder_ = null;
            cell_ = other.cell_;
            bitField0_ = (bitField0_ & ~0x00000001);
            cellBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCellFieldBuilder() : null;
          } else {
            cellBuilder_.addAllMessages(other.cell_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      moonbox.message.protobuf.Row parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (moonbox.message.protobuf.Row) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<moonbox.message.protobuf.Cell> cell_ =
      java.util.Collections.emptyList();
    private void ensureCellIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        cell_ = new java.util.ArrayList<moonbox.message.protobuf.Cell>(cell_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        moonbox.message.protobuf.Cell, moonbox.message.protobuf.Cell.Builder, moonbox.message.protobuf.CellOrBuilder> cellBuilder_;

    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public java.util.List<moonbox.message.protobuf.Cell> getCellList() {
      if (cellBuilder_ == null) {
        return java.util.Collections.unmodifiableList(cell_);
      } else {
        return cellBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public int getCellCount() {
      if (cellBuilder_ == null) {
        return cell_.size();
      } else {
        return cellBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public moonbox.message.protobuf.Cell getCell(int index) {
      if (cellBuilder_ == null) {
        return cell_.get(index);
      } else {
        return cellBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder setCell(
        int index, moonbox.message.protobuf.Cell value) {
      if (cellBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCellIsMutable();
        cell_.set(index, value);
        onChanged();
      } else {
        cellBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder setCell(
        int index, moonbox.message.protobuf.Cell.Builder builderForValue) {
      if (cellBuilder_ == null) {
        ensureCellIsMutable();
        cell_.set(index, builderForValue.build());
        onChanged();
      } else {
        cellBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder addCell(moonbox.message.protobuf.Cell value) {
      if (cellBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCellIsMutable();
        cell_.add(value);
        onChanged();
      } else {
        cellBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder addCell(
        int index, moonbox.message.protobuf.Cell value) {
      if (cellBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCellIsMutable();
        cell_.add(index, value);
        onChanged();
      } else {
        cellBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder addCell(
        moonbox.message.protobuf.Cell.Builder builderForValue) {
      if (cellBuilder_ == null) {
        ensureCellIsMutable();
        cell_.add(builderForValue.build());
        onChanged();
      } else {
        cellBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder addCell(
        int index, moonbox.message.protobuf.Cell.Builder builderForValue) {
      if (cellBuilder_ == null) {
        ensureCellIsMutable();
        cell_.add(index, builderForValue.build());
        onChanged();
      } else {
        cellBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder addAllCell(
        java.lang.Iterable<? extends moonbox.message.protobuf.Cell> values) {
      if (cellBuilder_ == null) {
        ensureCellIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, cell_);
        onChanged();
      } else {
        cellBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder clearCell() {
      if (cellBuilder_ == null) {
        cell_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        cellBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public Builder removeCell(int index) {
      if (cellBuilder_ == null) {
        ensureCellIsMutable();
        cell_.remove(index);
        onChanged();
      } else {
        cellBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public moonbox.message.protobuf.Cell.Builder getCellBuilder(
        int index) {
      return getCellFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public moonbox.message.protobuf.CellOrBuilder getCellOrBuilder(
        int index) {
      if (cellBuilder_ == null) {
        return cell_.get(index);  } else {
        return cellBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public java.util.List<? extends moonbox.message.protobuf.CellOrBuilder> 
         getCellOrBuilderList() {
      if (cellBuilder_ != null) {
        return cellBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(cell_);
      }
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public moonbox.message.protobuf.Cell.Builder addCellBuilder() {
      return getCellFieldBuilder().addBuilder(
          moonbox.message.protobuf.Cell.getDefaultInstance());
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public moonbox.message.protobuf.Cell.Builder addCellBuilder(
        int index) {
      return getCellFieldBuilder().addBuilder(
          index, moonbox.message.protobuf.Cell.getDefaultInstance());
    }
    /**
     * <code>repeated .protobuf.Cell cell = 1;</code>
     */
    public java.util.List<moonbox.message.protobuf.Cell.Builder> 
         getCellBuilderList() {
      return getCellFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        moonbox.message.protobuf.Cell, moonbox.message.protobuf.Cell.Builder, moonbox.message.protobuf.CellOrBuilder> 
        getCellFieldBuilder() {
      if (cellBuilder_ == null) {
        cellBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            moonbox.message.protobuf.Cell, moonbox.message.protobuf.Cell.Builder, moonbox.message.protobuf.CellOrBuilder>(
                cell_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        cell_ = null;
      }
      return cellBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:protobuf.Row)
  }

  // @@protoc_insertion_point(class_scope:protobuf.Row)
  private static final moonbox.message.protobuf.Row DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new moonbox.message.protobuf.Row();
  }

  public static moonbox.message.protobuf.Row getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Row>
      PARSER = new com.google.protobuf.AbstractParser<Row>() {
    public Row parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Row(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Row> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Row> getParserForType() {
    return PARSER;
  }

  public moonbox.message.protobuf.Row getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

