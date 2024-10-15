package intellispaces.ixora.http;

import intellispaces.common.base.collection.ArraysFunctions;
import intellispaces.common.base.text.TextFunctions;
import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.data.datastream.ByteInputStream;
import intellispaces.ixora.data.datastream.DataStreams;

import java.io.InputStream;

@ObjectHandle(HttpResponseDomain.class)
abstract class HttpResponseHandle implements UnmovableHttpResponse {
  private final HttpStatus status;
  private final ByteInputStream bodyStream;

  HttpResponseHandle(HttpStatus status, InputStream body) {
    this.status = status;
    this.bodyStream = DataStreams.get(body);
  }

  HttpResponseHandle(HttpStatus status, String body) {
    this(status, TextFunctions.stringToInputStream(body));
  }

  HttpResponseHandle(HttpStatus status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseHandle(HttpStatus status) {
    this(status, InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public HttpStatus status() {
    return this.status;
  }

  @Override
  public ByteInputStream bodyStream() {
    return bodyStream;
  }
}
