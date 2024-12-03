package tech.intellispaces.ixora.http;

import tech.intellispaces.general.collection.ArraysFunctions;
import tech.intellispaces.general.text.StringFunctions;
import tech.intellispaces.ixora.data.datastream.DataStreams;
import tech.intellispaces.ixora.data.datastream.MovableByteInputStream;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

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
    this(status, StringFunctions.stringToInputStream(body));
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
