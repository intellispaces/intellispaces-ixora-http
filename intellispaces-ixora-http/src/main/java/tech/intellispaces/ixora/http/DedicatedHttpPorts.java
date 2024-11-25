package tech.intellispaces.ixora.http;

public interface DedicatedHttpPorts {

  static MovableDedicatedHttpPort get(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortHandleImpl(baseUrl, httpPort);
  }
}
