package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;

public interface TestPorts {

  static MovableTestPortHandle get(MovableInboundHttpPortHandle operativePort) {
    return new TestPortHandleSimpleImpl(operativePort);
  }
}
