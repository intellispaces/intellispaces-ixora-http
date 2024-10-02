package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.traverse.TraverseTypes;
import intellispaces.ixora.http.exception.HttpException;

@Domain("27d55c7d-a278-42d6-bda9-63db5114e1ed")
public interface HttpPortDomain {

  @Channel(
      value = "04eae999-4651-447f-82b9-9de3dcc27659",
      name = "HttpPortExchangeTransition",
      allowedTraverse = TraverseTypes.MappingOfMoving
  )
  HttpResponseDomain exchange(HttpRequestDomain request) throws HttpException;
}
