package intellispaces.ixora.http.test;

import intellispaces.framework.core.annotation.MapperOfMoving;
import intellispaces.framework.core.annotation.Mover;
import intellispaces.framework.core.annotation.ObjectHandle;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.http.exception.HttpException;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortHandle implements MovableTestPort {
  private final MovableInboundHttpPort port;

  public TestPortHandle(MovableInboundHttpPort port) {
    this.port = port;
  }

  public MovableInboundHttpPort getPort() {
    return port;
  }

  @Mover
  @Override
  public MovableTestPort open() {
    port.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPort close() {
    port.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(HttpRequest request) throws HttpException {
    return port.exchange(request);
  }
}
