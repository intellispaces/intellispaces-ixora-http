package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.ixora.system.ModulePortDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.traverse.TraverseTypes;

/**
 * HTTP module port.
 */
@Domain("27d55c7d-a278-42d6-bda9-63db5114e1ed")
public interface HttpPortDomain extends ModulePortDomain {

  @Channel("489c861a-13d4-47c7-9a88-8d5f48e83aff")
  ModulePortDomain asModulePort();

  @Channel(
      value = "04eae999-4651-447f-82b9-9de3dcc27659",
      name = "HttpPortExchangeChannel",
      allowedTraverse = TraverseTypes.MappingOfMoving
  )
  HttpResponseDomain exchange(HttpRequestDomain request) throws HttpException;
}
