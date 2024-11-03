package intellispaces.ixora.http.annotation.processor;

import intellispaces.common.annotationprocessor.context.AnnotationProcessingContext;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.ixora.http.common.HttpNameConventionFunctions;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.jaquarius.annotation.MapperOfMoving;
import intellispaces.jaquarius.annotation.Mover;
import intellispaces.jaquarius.annotation.ObjectHandle;
import intellispaces.jaquarius.annotation.processor.AbstractGenerator;
import intellispaces.jaquarius.common.NameConventionFunctions;

import javax.annotation.processing.RoundEnvironment;
import java.util.HashMap;
import java.util.Map;

public class HttpPortProviderGenerator extends AbstractGenerator {
  private String handleImplSimpleName;
  private String movableHandleSimpleName;

  public HttpPortProviderGenerator(CustomType initiatorType, CustomType entityType) {
    super(initiatorType, entityType);
  }

  @Override
  public boolean isRelevant(AnnotationProcessingContext context) {
    return true;
  }

  @Override
  public String artifactName() {
    return HttpNameConventionFunctions.getPortProviderCanonicalName(annotatedType);
  }

  @Override
  protected String templateName() {
    return "/http_port_provider.template";
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

    vars.put("handleImplSimpleName", handleImplSimpleName);
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

    handleImplSimpleName = context.addToImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortHandleImplCanonicalName(annotatedType)
    );
    movableHandleSimpleName = context.addToImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(annotatedType.canonicalName())
    );

    return true;
  }
}
