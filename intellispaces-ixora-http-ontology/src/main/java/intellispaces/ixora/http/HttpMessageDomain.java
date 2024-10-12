package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.ixora.data.cursor.CursorDomain;

@Domain("0c5ed260-ccf7-4b7a-970c-12a696a99dbc")
public interface HttpMessageDomain {

  /**
   * The only one cursor associated with the message body.
   */
  @Channel("476d36df-38e9-4340-a870-fe2ee21b400b")
  CursorDomain<Byte> messageBodyCursor();
}
