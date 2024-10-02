package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;

@Domain("ce126067-c94b-4fff-b423-a24a3d2fd5c2")
public interface InboundHttpPortDomain extends HttpPortDomain {

  @Channel("71693529-9c92-4a05-bab0-2d9a378ba9ab")
  Integer portNumber();
}
