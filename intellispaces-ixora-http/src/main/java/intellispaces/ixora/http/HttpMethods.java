package intellispaces.ixora.http;

public final class HttpMethods {

  private static final HttpMethod GET = new HttpMethodHandleImpl("GET");

  private static final HttpMethod HEAD = new HttpMethodHandleImpl("HEAD");

  private static final HttpMethod POST = new HttpMethodHandleImpl("POST");

  private static final HttpMethod PUT = new HttpMethodHandleImpl("PUT");

  private static final HttpMethod PATCH = new HttpMethodHandleImpl("PATCH");

  private static final HttpMethod DELETE = new HttpMethodHandleImpl("DELETE");

  private static final HttpMethod OPTIONS = new HttpMethodHandleImpl("OPTIONS");

  private static final HttpMethod TRACE = new HttpMethodHandleImpl("TRACE");

  private HttpMethods() {}

  public static HttpMethod get() {
    return GET;
  }

  public static HttpMethod head() {
    return HEAD;
  }

  public static HttpMethod post() {
    return POST;
  }

  public static HttpMethod put() {
    return PUT;
  }

  public static HttpMethod patch() {
    return PATCH;
  }

  public static HttpMethod delete() {
    return DELETE;
  }

  public static HttpMethod options() {
    return OPTIONS;
  }

  public static HttpMethod trace() {
    return TRACE;
  }
}
