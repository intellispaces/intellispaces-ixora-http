package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.data.datastream.ByteInputStreamDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.annotation.Movable;

@Domain("0c5ed260-ccf7-4b7a-970c-12a696a99dbc")
public interface HttpMessageDomain {

  /**
   * The byte stream associated with the HTTP message body.</p>
   *
   * There is only one stream associated with the request.
   */
  @Channel("476d36df-38e9-4340-a870-fe2ee21b400b")
  @Movable
  ByteInputStreamDomain bodyStream();
}
