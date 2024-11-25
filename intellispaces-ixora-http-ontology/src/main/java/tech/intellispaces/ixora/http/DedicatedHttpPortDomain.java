package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.data.datastream.InputDataStreamDomain;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.ixora.system.OutboundModulePortDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.traverse.TraverseTypes;

@Domain("8f8f05c6-18be-4275-87ea-c6384d33cbe3")
public interface DedicatedHttpPortDomain extends OutboundModulePortDomain {

  @Channel("57905f2f-acdb-42be-81a9-daf5d1a27104")
  OutboundModulePortDomain asOutboundModulePort();

  @Channel("a300f5c5-a9c2-4d34-9d89-709f60b39c63")
  String baseUrl();

  @Channel(
      value = "f525865c-e71d-4150-91d2-6639548ae5aa",
      name = "HttpClientExchangeSimpleChannel",
      allowedTraverse = TraverseTypes.MappingOfMoving
  )
  HttpResponseDomain exchange(
      String endpoint,
      HttpMethodDomain method
  ) throws HttpException;

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
