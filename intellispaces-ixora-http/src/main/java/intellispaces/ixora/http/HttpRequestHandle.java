package intellispaces.ixora.http;

import intellispaces.ixora.data.datastream.DataStreams;
import intellispaces.ixora.data.datastream.MovableByteInputStream;
import intellispaces.ixora.internet.Uri;
import intellispaces.jaquarius.annotation.Mapper;
import intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.InputStream;

@ObjectHandle(HttpRequestDomain.class)
abstract class HttpRequestHandle implements UnmovableHttpRequest {
  private final HttpMethod method;
  private final Uri requestURI;
  private final MovableByteInputStream bodyStream;

  HttpRequestHandle(HttpMethod method, Uri requestURI) {
    this.method = method;
    this.requestURI = requestURI;
    this.bodyStream = DataStreams.get(InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public Uri requestURI() {
    return this.requestURI;
  }

  @Mapper
  @Override
  public HttpMethod method() {
    return this.method;
  }

  @Override
  public MovableByteInputStream bodyStream() {
    return bodyStream;
  }
}
