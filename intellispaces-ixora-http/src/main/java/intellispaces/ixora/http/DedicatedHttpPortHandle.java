package intellispaces.ixora.http;

import intellispaces.ixora.data.datastream.ByteStreams;
import intellispaces.ixora.data.datastream.InputDataStream;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.ixora.internet.JoinUrlGuide;
import intellispaces.ixora.internet.Uri;
import intellispaces.ixora.internet.Uris;
import intellispaces.jaquarius.annotation.AutoGuide;
import intellispaces.jaquarius.annotation.Mapper;
import intellispaces.jaquarius.annotation.MapperOfMoving;
import intellispaces.jaquarius.annotation.ObjectHandle;

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
    Uri uri = Uris.get(joinUrlGuide().map(baseUrl, endpoint));
    HttpRequest request = HttpRequests.get(method, uri);
    return underlyingPort.exchange(request);
  }

  @Mapper
  @Override
  public String baseUrl() {
    return baseUrl;
  }
}
