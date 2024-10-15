package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;

@ObjectHandle(HttpStatusDomain.class)
abstract class HttpStatusHandle implements UnmovableHttpStatus {
  private final int code;

  HttpStatusHandle(int code) {
    this.code = code;
  }

  @Mapper
  @Override
  public Integer code() {
    return code;
  }
}
