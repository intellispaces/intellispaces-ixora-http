package intellispaces.ixora.http;

import intellispaces.ixora.internet.Uri;
import intellispaces.ixora.internet.Uris;

public interface HttpRequests {

  static UnmovableHttpRequest get(HttpMethod method, Uri requestURI) {
    return new HttpRequestHandleImpl(method, requestURI);
  }

  static UnmovableHttpRequest get(HttpMethod method, String requestURI) {
    return HttpRequests.get(method, Uris.get(requestURI));
  }
}
