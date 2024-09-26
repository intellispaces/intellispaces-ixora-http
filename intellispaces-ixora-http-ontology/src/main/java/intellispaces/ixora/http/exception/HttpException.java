package intellispaces.ixora.http.exception;

import intellispaces.framework.core.exception.TraverseException;

public class HttpException extends TraverseException {

  public HttpException(String messageTemplate, Object... messageParams) {
    super(messageTemplate, messageParams);
  }

  public HttpException(Throwable cause, String messageTemplate, Object... messageParams) {
    super(cause, messageTemplate, messageParams);
  }

  public static HttpException withMessage(String messageTemplate, Object... messageParams) {
    return new HttpException(null, messageTemplate, messageParams);
  }

  public static HttpException withCauseAndMessage(Throwable cause, String messageTemplate, Object... messageParams) {
    return new HttpException(cause, messageTemplate, messageParams);
  }
}
