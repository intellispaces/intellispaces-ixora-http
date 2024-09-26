package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.annotation.Transition;

@Domain("346dee4e-e618-4b0e-83d5-dd070f607e4e")
public interface HttpStatusDomain {

  @Transition("7f6f42ae-a190-467f-a08e-e19f07bb538b")
  int code();
}
