package intellispaces.ixora.http.annotation.processor;

import intellispaces.common.annotationprocessor.context.AnnotationProcessingContext;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.common.javastatement.method.MethodStatement;
import intellispaces.ixora.http.HttpRequestDomain;
import intellispaces.ixora.http.HttpResponseDomain;
import intellispaces.ixora.http.common.HttpNameConventionFunctions;
import intellispaces.ixora.http.exception.HttpException;
import intellispaces.jaquarius.annotation.Channel;
import intellispaces.jaquarius.annotation.processor.AbstractGenerator;
import intellispaces.jaquarius.space.channel.ChannelFunctions;

import javax.annotation.processing.RoundEnvironment;
import java.util.HashMap;
import java.util.Map;

public class HttpPortExchangeChannelGenerator extends AbstractGenerator {
  private final CustomType portDomain;
  private final CustomType ontology;
  private final MethodStatement channelMethod;
  private String cid;
  private String superChannelSimpleName;

  public HttpPortExchangeChannelGenerator(
      CustomType initiatorType, CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    super(initiatorType, ontology);
    this.portDomain = portDomain;
    this.ontology = ontology;
    this.channelMethod = channelMethod;
  }

  @Override
  public boolean isRelevant(AnnotationProcessingContext context) {
    return true;
  }

  @Override
  public String artifactName() {
    return HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(portDomain, ontology, channelMethod);
  }

  @Override
  protected String templateName() {
    return "/http_port_exchange_channel.template";
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

    vars.put("cid", cid);
    vars.put("superChannelSimpleName", superChannelSimpleName);
    vars.put("channelMethodName", channelMethod.name());
    vars.put("portSimpleName", context.addToImportAndGetSimpleName(portDomain.canonicalName()));
    vars.put("ontologySimpleName", context.addToImportAndGetSimpleName(ontology.canonicalName()));

    return vars;
  }

  @Override
  protected boolean analyzeAnnotatedType(RoundEnvironment roundEnv) {
    context.generatedClassCanonicalName(artifactName());

    context.addImport(Channel.class);
    context.addImport(HttpRequestDomain.class);
    context.addImport(HttpResponseDomain.class);
    context.addImport(HttpException.class);

    cid = ChannelFunctions.computedChannelId(
        HttpNameConventionFunctions.getFormalPortExchangeChannelCanonicalName(portDomain, ontology, channelMethod)
    );
    superChannelSimpleName = context.addToImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortExchangeChannelCanonicalName(portDomain)
    );

    return true;
  }
}
