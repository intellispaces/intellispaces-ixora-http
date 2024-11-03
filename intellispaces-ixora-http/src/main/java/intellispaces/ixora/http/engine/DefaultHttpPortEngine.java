package intellispaces.ixora.http.engine;

import intellispaces.common.action.conditional.ConditionalActions;
import intellispaces.common.base.exception.NotImplementedException;
import intellispaces.common.base.type.Classes;
import intellispaces.common.base.type.Reference;
import intellispaces.common.base.type.References;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.pathtree.FinalExecutor;
import intellispaces.ixora.http.pathtree.PathSegment;
import intellispaces.ixora.http.pathtree.PathTreeFunctions;
import intellispaces.ixora.http.port.PortDescriptor;

import java.util.List;

public class DefaultHttpPortEngine implements HttpPortEngine {

  @Override
  public Reference<?> bridge(Object port, Class<?> portDomain) {
    return References.get(new PortDescriptor(port, portDomain));
  }

  @Override
  public HttpResponse exchange(Reference<?> reference, HttpRequest request) {
    var descriptor = (PortDescriptor) reference.get();

    List<PathSegment> rootSegments = ConditionalActions.requestIfAbsent(
        Classes.ofList(PathSegment.class), PortDescriptor.class
    ).execute(
        descriptor,
        PortDescriptor::rootSegments,
        PortDescriptor::setRootSegments,
        () -> PathTreeFunctions.readPathTree(descriptor.port(), descriptor.portDomain())
    );

    FinalExecutor executor = PathTreeFunctions.findExecution(request, rootSegments);
    if (executor == null) {
      throw NotImplementedException.withCode("YNScfA");
    }
    return executor.exchange(descriptor.port(), request);
  }
}
