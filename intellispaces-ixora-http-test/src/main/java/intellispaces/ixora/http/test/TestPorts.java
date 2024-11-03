package intellispaces.ixora.http.test;

import intellispaces.ixora.http.MovableInboundHttpPort;

public interface TestPorts {

  static MovableTestPort get(MovableInboundHttpPort operativePort) {
    return new TestPortHandleImpl(operativePort);
  }
}
