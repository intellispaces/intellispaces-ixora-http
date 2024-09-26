package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;

@ObjectHandle(value = HttpRequestDomain.class, name = "HttpRequestHandleImpl")
abstract class HttpRequestHandle implements UnmovableHttpRequest {
  private final String requestURI;
  private final HttpMethod method;

  HttpRequestHandle(String requestURI, HttpMethod method) {
    this.requestURI = requestURI;
    this.method = method;
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
}
