package intellispaces.ixora.http;

import intellispaces.common.base.exception.UnexpectedViolationException;

public final class HttpStatuses {

  private static final HttpStatus OK = new HttpStatusHandleImpl(200);

  private HttpStatuses() {}

  public static HttpStatus ok() {
    return OK;
  }

  public static HttpStatus of(int code) {
    return switch (code) {
      case 200 -> OK;
      default -> {
        throw UnexpectedViolationException.withMessage("Unsupported HTTP status code: {}", code);
      }
    };
  }
}
