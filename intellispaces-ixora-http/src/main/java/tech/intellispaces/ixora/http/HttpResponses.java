package tech.intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponseHandle get(HttpStatusHandleSimple status) {
    return new HttpResponseHandleSimpleImpl(status);
  }

  static HttpResponseHandle get(HttpStatusHandleSimple status, InputStream body) {
    return new HttpResponseHandleSimpleImpl(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandleSimple status, String body) {
    return new HttpResponseHandleSimpleImpl(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandleSimple status, byte[] body) {
    return new HttpResponseHandleSimpleImpl(status, body);
  }

  static HttpResponseHandle ok(String body) {
    return get(HttpStatuses.ok(), body);
  }

  static HttpResponseHandle notFound() {
    return get(HttpStatuses.notFound());
  }

  static HttpResponseHandle notFound(String body) {
    return get(HttpStatuses.notFound(), body);
  }
}
