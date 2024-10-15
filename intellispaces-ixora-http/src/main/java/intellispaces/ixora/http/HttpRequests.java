package intellispaces.ixora.http;

public interface HttpRequests {

  static HttpRequest get(HttpMethod method, String requestURI) {
    return new HttpRequestHandleImpl(method, requestURI);
  }
}
