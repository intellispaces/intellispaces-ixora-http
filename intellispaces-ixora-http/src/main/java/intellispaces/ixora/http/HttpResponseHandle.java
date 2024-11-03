package intellispaces.ixora.http;

import intellispaces.common.base.collection.ArraysFunctions;
import intellispaces.common.base.text.TextFunctions;
import intellispaces.ixora.data.datastream.DataStreams;
import intellispaces.ixora.data.datastream.MovableByteInputStream;
import intellispaces.jaquarius.annotation.Mapper;
import intellispaces.jaquarius.annotation.MapperOfMoving;
import intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.InputStream;

@ObjectHandle(HttpResponseDomain.class)
abstract class HttpResponseHandle implements UnmovableHttpResponse {
  private final HttpStatus status;
  private final MovableByteInputStream bodyStream;

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

  @MapperOfMoving
  @Override
  public MovableByteInputStream bodyStream() {
    return bodyStream;
  }
}
