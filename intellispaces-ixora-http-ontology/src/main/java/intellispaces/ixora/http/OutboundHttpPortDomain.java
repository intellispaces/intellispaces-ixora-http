package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.annotation.Transition;

@Domain("20f03cb9-c0b5-4ae4-9736-0468cdddd12b")
public interface OutboundHttpPortDomain extends HttpPortDomain {

  @Transition("a300f5c5-a9c2-4d34-9d89-709f60b39c63")
  String server();

  @Transition("b6d40ba5-3582-4e32-bb72-62bfd7e7a0eb")
  Integer portNumber();
}