package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.annotation.Movable;
import intellispaces.ixora.data.datastream.ByteInputStreamDomain;

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
