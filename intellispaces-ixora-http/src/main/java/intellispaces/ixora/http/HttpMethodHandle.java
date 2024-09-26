package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;

@ObjectHandle(value = HttpMethodDomain.class, name = "HttpMethodHandleImpl")
abstract class HttpMethodHandle implements UnmovableHttpMethod {
  private final String name;

  HttpMethodHandle(String name) {
    this.name = name;
  }

  @Mapper
  @Override
  public String name() {
    return name;
  }
}
