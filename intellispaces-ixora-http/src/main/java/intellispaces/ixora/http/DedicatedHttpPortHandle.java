package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.AutoGuide;
import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.MapperOfMoving;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.data.datastream.ByteStreams;
import intellispaces.ixora.data.datastream.InputDataStream;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.ixora.internet.JoinUrlGuide;

@ObjectHandle(DedicatedHttpPortDomain.class)
public abstract class DedicatedHttpPortHandle implements MovableDedicatedHttpPort {
  private final String baseUrl;
  private final MovableHttpPort underlyingPort;

  public DedicatedHttpPortHandle(String baseUrl, MovableHttpPort underlyingPort) {
    this.baseUrl = baseUrl;
    this.underlyingPort = underlyingPort;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public MovableHttpPort getUnderlyingPort() {
    return underlyingPort;
  }

  @AutoGuide
  abstract JoinUrlGuide joinUrlGuide();

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
    String uri = joinUrlGuide().joinUrl(baseUrl, endpoint);
    HttpRequest request = HttpRequests.get(method, uri);
    return underlyingPort.exchange(request);
  }

  @Mapper
  @Override
  public String baseUrl() {
    return baseUrl;
  }
}
