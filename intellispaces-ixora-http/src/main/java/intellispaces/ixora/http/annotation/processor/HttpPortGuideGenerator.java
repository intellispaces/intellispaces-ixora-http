package intellispaces.ixora.http.annotation.processor;

import intellispaces.common.annotationprocessor.context.AnnotationProcessingContext;
import intellispaces.common.base.exception.NotImplementedException;
import intellispaces.common.base.exception.UnexpectedViolationException;
import intellispaces.common.base.text.TextFunctions;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.common.javastatement.method.MethodParam;
import intellispaces.common.javastatement.method.MethodSignatureDeclarations;
import intellispaces.common.javastatement.method.MethodStatement;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.annotation.QueryParam;
import intellispaces.ixora.http.common.HttpNameConventionFunctions;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.ixora.internet.UriToQueryParamGuide;
import intellispaces.jaquarius.annotation.AutoGuide;
import intellispaces.jaquarius.annotation.MapperOfMoving;
import intellispaces.jaquarius.annotation.processor.AbstractGenerator;
import intellispaces.jaquarius.object.ObjectFunctions;

import javax.annotation.processing.RoundEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HttpPortGuideGenerator extends AbstractGenerator {
  private final CustomType portDomain;
  private final CustomType ontology;
  private final List<Map<String, Object>> guideMethods = new ArrayList<>();
  private final List<Map<String, Object>> ontologyMethods = new ArrayList<>();
  private boolean needUriToQueryParamGuide = false;

  public HttpPortGuideGenerator(
      CustomType initiatorType, CustomType portDomain, CustomType ontology
  ) {
    super(initiatorType, ontology);
    this.portDomain = portDomain;
    this.ontology = ontology;
  }

  @Override
  public boolean isRelevant(AnnotationProcessingContext context) {
    return true;
  }

  @Override
  public String artifactName() {
    return HttpNameConventionFunctions.getPortGuideCanonicalName(ontology);
  }

  @Override
  protected String templateName() {
    return "/http_port_guide.template";
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

    vars.put("portSimpleName", context.addToImportAndGetSimpleName(portDomain.canonicalName()));
    vars.put("ontologySimpleName", context.addToImportAndGetSimpleName(ontology.canonicalName()));
    vars.put("guideMethods", guideMethods);
    vars.put("ontologyMethods", ontologyMethods);
    vars.put("needUriToQueryParamGuide", needUriToQueryParamGuide);

    return vars;
  }

  @Override
  protected boolean analyzeAnnotatedType(RoundEnvironment roundEnv) {
    context.generatedClassCanonicalName(artifactName());

    context.addImport(MapperOfMoving.class);

    analyzeMethods();
    return true;
  }

  private void analyzeMethods() {
    for (MethodStatement method : annotatedType.actualMethods()) {
      guideMethods.add(buildGuideMethod(method));
      ontologyMethods.add(buildMethod(method));
    }
  }

  private Map<String, Object> buildGuideMethod(MethodStatement method) {
    Map<String, Object> map = new HashMap<>();
    map.put("channelClass", context.addToImportAndGetSimpleName(
        HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(portDomain, ontology, method)));
    map.put("signature", buildGuideMethodSignature(method));
    map.put("body", buildGuideMethodBody(method));
    return map;
  }

  private String buildGuideMethodSignature(MethodStatement method) {
    var sb = new StringBuilder();
    sb.append(context.addToImportAndGetSimpleName(HttpResponse.class));
    sb.append(" ");
    sb.append(buildGuideMethodName(method));
    sb.append("(");
    sb.append(context.addToImportAndGetSimpleName(ObjectFunctions.getCommonObjectHandleTypename(portDomain)));
    sb.append(" port, ");
    sb.append(context.addToImportAndGetSimpleName(HttpRequest.class));
    sb.append(" request) throws ");
    sb.append(context.addToImportAndGetSimpleName(HttpException.class));
    return sb.toString();
  }

  private String buildGuideMethodName(MethodStatement method) {
    var sb = new StringBuilder();
    sb.append("exchange");
    sb.append(TextFunctions.capitalizeFirstLetter(method.name()));
    for (MethodParam param : method.params()) {
      sb.append(TextFunctions.capitalizeFirstLetter(param.name()));
    }
    return sb.toString();
  }

  private String buildGuideMethodBody(MethodStatement method) {
    var sb = new StringBuilder();
    for (MethodParam param : method.params()) {
      appendMethodArgumentExtractorDeclaration(sb, param);
    }
    sb.append("return ");
    sb.append(method.name());
    sb.append("(");
    for (MethodParam param : method.params()) {
      appendMethodArgumentValueDeclaration(sb, param);
    }
    sb.append(");");
    return sb.toString();
  }

  private Map<String, Object> buildMethod(MethodStatement method) {
    Map<String, Object> map = new HashMap<>();

    String signature = MethodSignatureDeclarations.build(method)
        .get(context::addImport, context::addToImportAndGetSimpleName);
    map.put("signature", signature);
    return map;
  }

  private void appendMethodArgumentExtractorDeclaration(StringBuilder sb, MethodParam param) {
    Function<MethodParam, String> mapper = getMethodArgumentExtractorDeclarationMapper(param);
    if (mapper == null) {
      throw UnexpectedViolationException.withMessage("Could not find method argument extractor declaration napper");
    }
    sb.append(mapper.apply(param));
  }

  private void appendMethodArgumentValueDeclaration(StringBuilder sb, MethodParam param) {
    Function<MethodParam, String> mapper = getMethodArgumentValueDeclarationMapper(param);
    if (mapper == null) {
      throw UnexpectedViolationException.withMessage("Could not find method argument declaration napper");
    }
    sb.append(mapper.apply(param));
  }

  protected Function<MethodParam, String> getMethodArgumentExtractorDeclarationMapper(MethodParam param) {
    if (param.type().isCustomTypeReference() && HttpRequest.class.getCanonicalName().equals(
        param.type().asCustomTypeReferenceOrElseThrow().targetClass().getCanonicalName())
    ) {
      return this::buildMethodArgumentExtractorDeclarationEmpty;
    }
    if (param.hasAnnotation(QueryParam.class)) {
      return this::buildMethodQueryParamArgumentExtractorDeclaration;
    }
    return null;
  }

  protected Function<MethodParam, String> getMethodArgumentValueDeclarationMapper(MethodParam param) {
    if (param.type().isCustomTypeReference() && HttpRequest.class.getCanonicalName().equals(
        param.type().asCustomTypeReferenceOrElseThrow().targetClass().getCanonicalName())
    ) {
      return this::buildMethodArgumentValueDeclarationDirect;
    }
    if (param.hasAnnotation(QueryParam.class)) {
      return this::buildMethodQueryParamArgumentValueDeclaration;
    }
    return null;
  }

  private String buildMethodQueryParamArgumentExtractorDeclaration(MethodParam param) {
    QueryParam queryParam = param.selectAnnotation(QueryParam.class).orElseThrow();
    String queryParamName = queryParam.value();
    String valueVariable = param.name() + "Value";
    String valuesVariable = param.name() + "Values";
    String castedValueVariable = param.name() + "Casted";
    includeUriToQueryParamGuide();

    StringBuilder sb = new StringBuilder();
    sb.append(context.addToImportAndGetSimpleName(intellispaces.ixora.data.collection.List.class));
    sb.append("<String> ");
    sb.append(valuesVariable);
    sb.append(" = uriToQueryParamGuide().map(request.requestURI(), \"");
    sb.append(queryParamName);
    sb.append("\");\n");
    sb.append("if (").append(valuesVariable).append(".size() == 0) {\n");
    sb.append("  throw ")
        .append(context.addToImportAndGetSimpleName(UnexpectedViolationException.class))
        .append(".withMessage(\"Query parameter '")
        .append(queryParamName)
        .append("' was not found\");\n");
    sb.append("} else if (").append(valuesVariable).append(".size() > 1) {\n");
    sb.append("  throw ")
        .append(context.addToImportAndGetSimpleName(UnexpectedViolationException.class))
        .append(".withMessage(\"Multiple query parameter '")
        .append(queryParamName)
        .append("' values found\");\n");
    sb.append("}\n");
    sb.append("String ");
    sb.append(valueVariable);
    sb.append(" = ");
    sb.append(valuesVariable);
    sb.append(".get(0);\n");
    sb.append(param.type().actualDeclaration(context::addToImportAndGetSimpleName));
    sb.append(" ");
    sb.append(castedValueVariable);
    sb.append(" = ");
    if (String.class.getCanonicalName().equals(param.type().asCustomTypeReferenceOrElseThrow().targetType().canonicalName())) {
      sb.append(valueVariable);
      sb.append(";\n");
    } else {
      throw NotImplementedException.withCode("14ruHA");
    }
    return sb.toString();
  }

  private String buildMethodQueryParamArgumentValueDeclaration(MethodParam param) {
    return param.name() + "Casted";
  }

  private void includeUriToQueryParamGuide() {
    needUriToQueryParamGuide = true;
    context.addImport(AutoGuide.class);
    context.addImport(UriToQueryParamGuide.class);
  }

  private String buildMethodArgumentExtractorDeclarationEmpty(MethodParam param) {
    return "";
  }

  private String buildMethodArgumentValueDeclarationDirect(MethodParam param) {
    return param.name();
  }
}