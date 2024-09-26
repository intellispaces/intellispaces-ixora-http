package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.annotation.Transition;

@Domain("5f96c856-7ac3-407f-89fb-0137e5936fe8")
public interface HttpResponseDomain extends HttpMessageDomain {

  @Transition("73bac052-7077-43a4-816a-dd929fdc6cd6")
  HttpStatusDomain status();
}
