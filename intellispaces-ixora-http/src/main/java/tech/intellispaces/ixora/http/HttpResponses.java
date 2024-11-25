package tech.intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponse get(HttpStatus status) {
    return new HttpResponseHandleImpl(status);
  }

  static HttpResponse get(HttpStatus status, InputStream body) {
    return new HttpResponseHandleImpl(status, body);
  }

  static HttpResponse get(HttpStatus status, String body) {
    return new HttpResponseHandleImpl(status, body);
  }

  static HttpResponse get(HttpStatus status, byte[] body) {
    return new HttpResponseHandleImpl(status, body);
  }

  static HttpResponse ok(String body) {
    return get(HttpStatuses.ok(), body);
  }

  static HttpResponse notFound() {
    return get(HttpStatuses.notFound());
  }

  static HttpResponse notFound(String body) {
    return get(HttpStatuses.notFound(), body);
  }
}
