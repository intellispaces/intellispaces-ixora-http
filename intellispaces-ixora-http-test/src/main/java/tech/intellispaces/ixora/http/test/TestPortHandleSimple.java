package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortHandleSimple implements MovableTestPortHandle {
  private final MovableInboundHttpPortHandle operativePort;

  public TestPortHandleSimple(MovableInboundHttpPortHandle operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPortHandle getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableTestPortHandle open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPortHandle close() {
    operativePort.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponseHandle exchange(HttpRequestHandle request) throws HttpException {
    return operativePort.exchange(request);
  }
}
