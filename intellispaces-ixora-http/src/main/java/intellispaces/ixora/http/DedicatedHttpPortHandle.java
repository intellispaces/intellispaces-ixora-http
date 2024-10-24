package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.MapperOfMoving;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.data.datastream.ByteStreams;
import intellispaces.ixora.data.datastream.InputDataStream;
import intellispaces.ixora.http.exception.HttpException;

@ObjectHandle(DedicatedHttpPortDomain.class)
public abstract class DedicatedHttpPortHandle implements MovableDedicatedHttpPort {
  private final String baseUrl;
  private final MovableHttpPort httpPort;

  public DedicatedHttpPortHandle(String baseUrl, MovableHttpPort httpPort) {
    this.baseUrl = baseUrl;
    this.httpPort = httpPort;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public MovableHttpPort getHttpPort() {
    return httpPort;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(
      String endpoint, HttpMethod method
  ) throws HttpException {
    return exchange(
        endpoint,
        method,
        null,
        ByteStreams.empty()
    );
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(
      String endpoint,
      HttpMethod method,
      HttpHeaders headers,
      InputDataStream<Byte> body
  ) throws HttpException {
    String uri = buildRequestURI(endpoint);
    HttpRequest request = HttpRequests.get(method, uri);
    return httpPort.exchange(request);
  }

  private String buildRequestURI(String endpoint) {
    return baseUrl + endpoint;
  }

  @Mapper
  @Override
  public String baseUrl() {
    return baseUrl;
  }
}
