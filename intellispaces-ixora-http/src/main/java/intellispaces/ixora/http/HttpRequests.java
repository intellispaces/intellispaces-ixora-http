package intellispaces.ixora.http;

public interface HttpRequests {

  static HttpRequest get(String requestURI, HttpMethod method) {
    return new HttpRequestHandleImpl(requestURI, method);
  }
}
