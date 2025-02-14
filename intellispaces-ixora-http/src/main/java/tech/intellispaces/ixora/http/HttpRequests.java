package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.UriHandle;
import tech.intellispaces.ixora.internet.Uris;

public interface HttpRequests {

  static UnmovableHttpRequestHandle get(HttpMethodHandle method, UriHandle requestURI) {
    return new HttpRequestHandleImplWrapper(method, requestURI);
  }

  static UnmovableHttpRequestHandle get(HttpMethodHandle method, String requestURI) {
    return HttpRequests.get(method, Uris.get(requestURI));
  }
}
