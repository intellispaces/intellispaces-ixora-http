package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.traverse.TraverseTypes;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.ixora.system.ModulePortDomain;

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
