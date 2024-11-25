package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.system.OutboundModulePortDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;

/**
 * Outbound HTTP module port.
 */
@Domain("20f03cb9-c0b5-4ae4-9736-0468cdddd12b")
public interface OutboundHttpPortDomain extends HttpPortDomain, OutboundModulePortDomain {

  @Channel("ee91aeee-5401-438c-81b1-df3948624bf2")
  HttpPortDomain asHttpPort();

  @Channel("b1865f7e-8249-4e81-81ef-95cab84afe81")
  OutboundModulePortDomain asOutboundModulePort();
}
