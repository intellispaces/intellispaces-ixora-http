package intellispaces.ixora.http.engine;

import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.entity.data.Reference;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponse exchange(Reference<?> bridge, HttpRequest request);
}
