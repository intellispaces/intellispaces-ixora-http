package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Mapper;
import intellispaces.framework.core.annotation.ObjectHandle;

@ObjectHandle(HttpMethodDomain.class)
abstract class HttpMethodHandle implements UnmovableHttpMethod {
  private final String name;

  HttpMethodHandle(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Mapper
  @Override
  public String name() {
    return name;
  }

  @Mapper
  @Override
  public Boolean isGet() {
    return HttpMethods.get().name().equals(name);
  }
}
