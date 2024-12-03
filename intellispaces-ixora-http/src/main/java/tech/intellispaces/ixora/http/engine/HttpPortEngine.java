package tech.intellispaces.ixora.http.engine;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.entity.entity.Reference;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponse exchange(Reference<?> bridge, HttpRequest request);
}
