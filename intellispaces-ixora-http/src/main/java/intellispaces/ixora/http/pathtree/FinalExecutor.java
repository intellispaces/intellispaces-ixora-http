package intellispaces.ixora.http.pathtree;

import intellispaces.common.action.Action2;
import intellispaces.common.javastatement.method.MethodSignature;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.jaquarius.action.TraverseActions;
import intellispaces.jaquarius.channel.Channel1;

public class FinalExecutor {
  private final Object port;
  private final Class<? extends Channel1> channelClass;
  private final MethodSignature method;
  private final Action2<HttpResponse, Object, HttpRequest> action;

  public FinalExecutor(
      Object port,
      Class<? extends Channel1> channelClass,
      MethodSignature method
  ) {
    this.port = port;
    this.channelClass = channelClass;
    this.method = method;
    this.action = makeAction(port, channelClass);
  }

  public MethodSignature method() {
    return method;
  }

  public HttpResponse exchange(Object port, HttpRequest request) {
    return action.execute(port, request);
  }

  @SuppressWarnings("unchecked, rawtypes")
  private Action2<HttpResponse, Object, HttpRequest> makeAction(
      Object port, Class<? extends Channel1> channelClass
  ) {
    return (Action2) TraverseActions.mapOfMovingThruChannel1(port.getClass(), channelClass);
  }
}