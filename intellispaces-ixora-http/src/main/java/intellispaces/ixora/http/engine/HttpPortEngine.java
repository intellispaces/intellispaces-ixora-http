package intellispaces.ixora.http.engine;

import intellispaces.common.base.data.Reference;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponse exchange(Reference<?> bridge, HttpRequest request);
}
