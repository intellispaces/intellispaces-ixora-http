package intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponse get(HttpStatus status) {
    return new HttpResponseHandleImpl(status);
  }

  static HttpResponse get(HttpStatus status, String messageBody) {
    return new HttpResponseHandleImpl(status, messageBody);
  }

  static HttpResponse get(HttpStatus status, byte[] messageBody) {
    return new HttpResponseHandleImpl(status, messageBody);
  }

  static HttpResponse get(HttpStatus status, InputStream messageBody) {
    return new HttpResponseHandleImpl(status, messageBody);
  }
}
