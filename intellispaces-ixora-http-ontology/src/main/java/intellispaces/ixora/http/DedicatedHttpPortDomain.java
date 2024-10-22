package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.traverse.TraverseTypes;
import intellispaces.ixora.data.datastream.InputDataStreamDomain;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.ixora.system.OutboundModulePortDomain;

@Domain("8f8f05c6-18be-4275-87ea-c6384d33cbe3")
public interface DedicatedHttpPortDomain extends OutboundModulePortDomain {

  @Channel("57905f2f-acdb-42be-81a9-daf5d1a27104")
  OutboundModulePortDomain asOutboundModulePort();

  /**
   * Destination server address (IP address + port number or hostname + port number).
   */
  @Channel("a300f5c5-a9c2-4d34-9d89-709f60b39c63")
  String serverAddress();

  /**
   * Destination server port.
   */
  @Channel("b6d40ba5-3582-4e32-bb72-62bfd7e7a0eb")
  Integer port();

  @Channel(
      value = "6d0d1b6b-e887-4a7e-993d-c278e9d94e5d",
      name = "HttpClientExchangeChannel",
      allowedTraverse = TraverseTypes.MappingOfMoving
  )
  HttpResponseDomain exchange(
      String endpoint,
      HttpMethodDomain method,
      HttpHeadersDomain headers,
      InputDataStreamDomain<Byte> body
  ) throws HttpException;
}
