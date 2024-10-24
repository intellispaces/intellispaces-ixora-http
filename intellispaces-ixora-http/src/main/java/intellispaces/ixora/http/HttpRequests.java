package intellispaces.ixora.http;

public interface HttpRequests {

  static UnmovableHttpRequest get(HttpMethod method, String requestURI) {
    return new HttpRequestHandleImpl(method, requestURI);
  }
}
