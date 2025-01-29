package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(HttpMethodDomain.class)
abstract class HttpMethodHandleSimple implements UnmovableHttpMethodHandle {
  private final String name;

  HttpMethodHandleSimple(String name) {
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
