package tech.intellispaces.ixora.http;

import tech.intellispaces.general.exception.UnexpectedExceptions;

public final class HttpStatuses {

  public static HttpStatusHandleSimple ok() {
    return STATUS_OK;
  }

  public static HttpStatusHandleSimple created() {
    return STATUS_CREATED;
  }

  public static HttpStatusHandleSimple accepted() {
    return STATUS_ACCEPTED;
  }

  public static HttpStatusHandleSimple noContent() {
    return STATUS_NO_CONTENT;
  }

  public static HttpStatusHandleSimple movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  public static HttpStatusHandleSimple notModified() {
    return STATUS_NOT_MODIFIED;
  }

  public static HttpStatusHandleSimple badRequest() {
    return STATUS_BAD_REQUEST;
  }

  public static HttpStatusHandleSimple unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  public static HttpStatusHandleSimple forbidden() {
    return STATUS_FORBIDDEN;
  }

  public static HttpStatusHandleSimple notFound() {
    return STATUS_NOT_FOUND;
  }

  public static HttpStatusHandleSimple notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  public static HttpStatusHandleSimple internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  public static HttpStatusHandleSimple get(int code) {
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

  private static final HttpStatusHandleSimple STATUS_OK = new HttpStatusHandleSimpleImpl(CODE_OK);
  private static final HttpStatusHandleSimple STATUS_CREATED = new HttpStatusHandleSimpleImpl(CODE_CREATED);
  private static final HttpStatusHandleSimple STATUS_ACCEPTED = new HttpStatusHandleSimpleImpl(CODE_ACCEPTED);
  private static final HttpStatusHandleSimple STATUS_NO_CONTENT = new HttpStatusHandleSimpleImpl(CODE_NO_CONTENT);
  private static final HttpStatusHandleSimple STATUS_MOVED_PERMANENTLY = new HttpStatusHandleSimpleImpl(CODE_MOVED_PERMANENTLY);
  private static final HttpStatusHandleSimple STATUS_NOT_MODIFIED = new HttpStatusHandleSimpleImpl(CODE_NOT_MODIFIED);
  private static final HttpStatusHandleSimple STATUS_BAD_REQUEST = new HttpStatusHandleSimpleImpl(CODE_BAD_REQUEST);
  private static final HttpStatusHandleSimple STATUS_UNAUTHORIZED = new HttpStatusHandleSimpleImpl(CODE_UNAUTHORIZED);
  private static final HttpStatusHandleSimple STATUS_FORBIDDEN = new HttpStatusHandleSimpleImpl(CODE_FORBIDDEN);
  private static final HttpStatusHandleSimple STATUS_NOT_FOUND = new HttpStatusHandleSimpleImpl(CODE_NOT_FOUND);
  private static final HttpStatusHandleSimple STATUS_NOT_ACCEPTABLE = new HttpStatusHandleSimpleImpl(CODE_NOT_ACCEPTABLE);
  private static final HttpStatusHandleSimple STATUS_INTERNAL_SERVER_ERROR = new HttpStatusHandleSimpleImpl(CODE_INTERNAL_SERVER_ERROR);
}
