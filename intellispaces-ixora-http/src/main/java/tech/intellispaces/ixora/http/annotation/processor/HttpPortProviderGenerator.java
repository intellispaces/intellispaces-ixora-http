package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;
import tech.intellispaces.java.reflection.customtype.CustomType;

public class HttpPortProviderGenerator extends JaquariusArtifactGenerator {
  private String handleImplSimpleName;
  private String movableHandleSimpleName;

  public HttpPortProviderGenerator(CustomType entityType) {
    super(entityType);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortProviderCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_provider.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ObjectHandle.class);
    addImport(Mover.class);
    addImport(MapperOfMoving.class);
    addImport(HttpResponse.class);
    addImport(HttpRequest.class);
    addImport(HttpException.class);
    addImport(MovableInboundHttpPort.class);

    handleImplSimpleName = addToImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortHandleImplCanonicalName(sourceArtifact())
    );
    movableHandleSimpleName = addToImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName())
    );

    addVariable("handleImplSimpleName", handleImplSimpleName);
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
