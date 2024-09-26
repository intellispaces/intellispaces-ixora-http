package intellispaces.ixora.http;

import intellispaces.common.base.collection.ArraysFunctions;
import intellispaces.common.base.text.TextFunctions;
import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.structures.collection.Cursor;
import intellispaces.ixora.structures.collection.Cursors;

import java.io.InputStream;

@ObjectHandle(value = HttpResponseDomain.class, name = "HttpResponseHandleImpl")
abstract class HttpResponseHandle implements UnmovableHttpResponse {
  private final HttpStatus status;
  private final InputStream messageBody;
  private final Cursor<Byte> cursor;

  HttpResponseHandle(HttpStatus status, InputStream messageBody) {
    this.status = status;
    this.messageBody = messageBody;
    this.cursor = Cursors.get(messageBody);
  }

  HttpResponseHandle(HttpStatus status, String messageBody) {
    this(status, TextFunctions.stringToInputStream(messageBody));
  }

  HttpResponseHandle(HttpStatus status, byte[] messageBody) {
    this(status, ArraysFunctions.arrayToInputStream(messageBody));
  }

  HttpResponseHandle(HttpStatus status) {
    this(status, InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public HttpStatus status() {
    return this.status;
  }

  @Mapper
  @Override
  public Cursor<Byte> messageBodyCursor() {
    return this.cursor;
  }
}
