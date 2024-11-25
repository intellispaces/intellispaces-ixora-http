package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.Uri;
import tech.intellispaces.ixora.internet.Uris;

public interface HttpRequests {

  static UnmovableHttpRequest get(HttpMethod method, Uri requestURI) {
    return new HttpRequestHandleImpl(method, requestURI);
  }

  static UnmovableHttpRequest get(HttpMethod method, String requestURI) {
    return HttpRequests.get(method, Uris.get(requestURI));
  }
}
