package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.ixora.system.InboundModulePortDomain;

/**
 * Inbound HTTP module port.
 */
@Domain("ce126067-c94b-4fff-b423-a24a3d2fd5c2")
public interface InboundHttpPortDomain extends HttpPortDomain, InboundModulePortDomain {

  @Channel("21ffc30d-ae90-4dd6-90d9-821b2ee821ac")
  HttpPortDomain asHttpPort();

  @Channel("c6153f76-12ef-434e-baed-a5f7fdcb0ee2")
  InboundModulePortDomain asInboundModulePort();

  /**
   * Server port.
   */
  @Channel("71693529-9c92-4a05-bab0-2d9a378ba9ab")
  Integer portNumber();
}
