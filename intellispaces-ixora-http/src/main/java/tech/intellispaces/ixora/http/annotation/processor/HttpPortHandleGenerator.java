package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.entity.entity.Reference;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.engine.HttpPortEngines;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.annotation.processor.AbstractGenerator;
import tech.intellispaces.jaquarius.common.NameConventionFunctions;
import tech.intellispaces.java.annotation.context.AnnotationProcessingContext;
import tech.intellispaces.java.reflection.customtype.CustomType;

import javax.annotation.processing.RoundEnvironment;
import java.util.HashMap;
import java.util.Map;

public class HttpPortHandleGenerator extends AbstractGenerator {
  private String movableHandleSimpleName;

  public HttpPortHandleGenerator(CustomType initiatorType, CustomType entityType) {
    super(initiatorType, entityType);
  }

  @Override
  public boolean isRelevant(AnnotationProcessingContext context) {
    return true;
  }

  @Override
  public String artifactName() {
    return HttpNameConventionFunctions.getPortHandleCanonicalName(annotatedType);
  }

  @Override
  protected String templateName() {
    return "/http_port_handle.template";
  }

  @Override
  protected Map<String, Object> templateVariables() {
    Map<String, Object> vars = new HashMap<>();
    vars.put("packageName", context.packageName());
    vars.put("sourceCanonicalName", sourceClassCanonicalName());
    vars.put("sourceSimpleName", sourceClassSimpleName());
    vars.put("classSimpleName", context.generatedClassSimpleName());
    vars.put("generatedAnnotation", makeGeneratedAnnotation());
    vars.put("importedClasses", context.getImports());

    vars.put("movableHandleSimpleName", movableHandleSimpleName);

    return vars;
  }

  @Override
  protected boolean analyzeAnnotatedType(RoundEnvironment roundEnv) {
    context.generatedClassCanonicalName(artifactName());

    context.addImport(ObjectHandle.class);
    context.addImport(Mover.class);
    context.addImport(MapperOfMoving.class);
    context.addImport(HttpResponse.class);
    context.addImport(HttpRequest.class);
    context.addImport(HttpException.class);
    context.addImport(MovableInboundHttpPort.class);
    context.addImport(Reference.class);
    context.addImport(HttpPortEngines.class);

    movableHandleSimpleName = context.addToImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(annotatedType.canonicalName())
    );
    return true;
  }
}
