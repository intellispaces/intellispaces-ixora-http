package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.data.datastream.ByteInputStream;
import intellispaces.ixora.data.datastream.DataStreams;

import java.io.InputStream;

@ObjectHandle(HttpRequestDomain.class)
abstract class HttpRequestHandle implements UnmovableHttpRequest {
  private final HttpMethod method;
  private final String requestURI;
  private final ByteInputStream bodyStream;

  HttpRequestHandle(HttpMethod method, String requestURI) {
    this.method = method;
    this.requestURI = requestURI;
    this.bodyStream = DataStreams.get(InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public String requestURI() {
    return this.requestURI;
  }

  @Mapper
  @Override
  public HttpMethod method() {
    return this.method;
  }

  @Override
  public ByteInputStream bodyStream() {
    return bodyStream;
  }
}
