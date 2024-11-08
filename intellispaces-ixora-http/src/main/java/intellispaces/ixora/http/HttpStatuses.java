package intellispaces.ixora.http;

import intellispaces.common.base.exception.UnexpectedExceptions;

public final class HttpStatuses {

  public static HttpStatus ok() {
    return STATUS_OK;
  }

  public static HttpStatus created() {
    return STATUS_CREATED;
  }

  public static HttpStatus accepted() {
    return STATUS_ACCEPTED;
  }

  public static HttpStatus noContent() {
    return STATUS_NO_CONTENT;
  }

  public static HttpStatus movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  public static HttpStatus notModified() {
    return STATUS_NOT_MODIFIED;
  }

  public static HttpStatus badRequest() {
    return STATUS_BAD_REQUEST;
  }

  public static HttpStatus unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  public static HttpStatus forbidden() {
    return STATUS_FORBIDDEN;
  }

  public static HttpStatus notFound() {
    return STATUS_NOT_FOUND;
  }

  public static HttpStatus notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  public static HttpStatus internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  public static HttpStatus get(int code) {
    return switch (code) {
      case CODE_OK -> STATUS_OK;
      case CODE_CREATED -> STATUS_CREATED;
      case CODE_ACCEPTED -> STATUS_ACCEPTED;
      case CODE_NO_CONTENT -> STATUS_NO_CONTENT;
      case CODE_MOVED_PERMANENTLY -> STATUS_MOVED_PERMANENTLY;
      case CODE_NOT_MODIFIED -> STATUS_NOT_MODIFIED;
      case CODE_BAD_REQUEST -> STATUS_BAD_REQUEST;
      case CODE_UNAUTHORIZED -> STATUS_UNAUTHORIZED;
      case CODE_FORBIDDEN -> STATUS_FORBIDDEN;
      case CODE_NOT_FOUND -> STATUS_NOT_FOUND;
      case CODE_NOT_ACCEPTABLE -> STATUS_NOT_ACCEPTABLE;
      case CODE_INTERNAL_SERVER_ERROR -> STATUS_INTERNAL_SERVER_ERROR;
      default -> {
        throw UnexpectedExceptions.withMessage("Unsupported HTTP status code: {}", code);
      }
    };
  }

  private HttpStatuses() {}

  private static final int CODE_OK = 200;
  private static final int CODE_CREATED = 201;
  private static final int CODE_ACCEPTED = 202;
  private static final int CODE_NO_CONTENT = 204;
  private static final int CODE_MOVED_PERMANENTLY = 301;
  private static final int CODE_NOT_MODIFIED = 304;
  private static final int CODE_BAD_REQUEST = 400;
  private static final int CODE_UNAUTHORIZED = 401;
  private static final int CODE_FORBIDDEN = 403;
  private static final int CODE_NOT_FOUND = 404;
  private static final int CODE_NOT_ACCEPTABLE = 406;
  private static final int CODE_INTERNAL_SERVER_ERROR = 500;

  private static final HttpStatus STATUS_OK = new HttpStatusHandleImpl(CODE_OK);
  private static final HttpStatus STATUS_CREATED = new HttpStatusHandleImpl(CODE_CREATED);
  private static final HttpStatus STATUS_ACCEPTED = new HttpStatusHandleImpl(CODE_ACCEPTED);
  private static final HttpStatus STATUS_NO_CONTENT = new HttpStatusHandleImpl(CODE_NO_CONTENT);
  private static final HttpStatus STATUS_MOVED_PERMANENTLY = new HttpStatusHandleImpl(CODE_MOVED_PERMANENTLY);
  private static final HttpStatus STATUS_NOT_MODIFIED = new HttpStatusHandleImpl(CODE_NOT_MODIFIED);
  private static final HttpStatus STATUS_BAD_REQUEST = new HttpStatusHandleImpl(CODE_BAD_REQUEST);
  private static final HttpStatus STATUS_UNAUTHORIZED = new HttpStatusHandleImpl(CODE_UNAUTHORIZED);
  private static final HttpStatus STATUS_FORBIDDEN = new HttpStatusHandleImpl(CODE_FORBIDDEN);
  private static final HttpStatus STATUS_NOT_FOUND = new HttpStatusHandleImpl(CODE_NOT_FOUND);
  private static final HttpStatus STATUS_NOT_ACCEPTABLE = new HttpStatusHandleImpl(CODE_NOT_ACCEPTABLE);
  private static final HttpStatus STATUS_INTERNAL_SERVER_ERROR = new HttpStatusHandleImpl(CODE_INTERNAL_SERVER_ERROR);
}
