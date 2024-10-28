package intellispaces.ixora.http;

import intellispaces.jaquarius.annotation.Mapper;
import intellispaces.jaquarius.annotation.ObjectHandle;

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

  @Mapper
  @Override
  public Boolean isOk() {
    return HttpStatuses.ok().code() == code;
  }
}
