package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortHandle implements MovableTestPort {
  private final MovableInboundHttpPort operativePort;

  public TestPortHandle(MovableInboundHttpPort operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPort getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableTestPort open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPort close() {
    operativePort.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(HttpRequest request) throws HttpException {
    return operativePort.exchange(request);
  }
}
