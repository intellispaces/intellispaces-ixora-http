package intellispaces.ixora.http;

public final class HttpStatuses {

  private static final HttpStatus OK = new HttpStatusHandleImpl(200);

  private HttpStatuses() {}

  public static HttpStatus ok() {
    return OK;
  }
}
