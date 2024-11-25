package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.MovableInboundHttpPort;

public interface TestPorts {

  static MovableTestPort get(MovableInboundHttpPort operativePort) {
    return new TestPortHandleImpl(operativePort);
  }
}
