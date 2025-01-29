package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(HttpStatusDomain.class)
abstract class HttpStatusHandleSimple implements UnmovableHttpStatusHandle {
  private final int code;

  HttpStatusHandleSimple(int code) {
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
