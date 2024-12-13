package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.annotationprocessor.ArtifactGenerator;
import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.annotationprocessor.ArtifactProcessor;
import tech.intellispaces.annotationprocessor.ArtifactValidator;
import tech.intellispaces.ixora.http.annotation.HttpPort;
import tech.intellispaces.jaquarius.annotationprocessor.AnnotationProcessorConstants;
import tech.intellispaces.jaquarius.annotationprocessor.AnnotationProcessorFunctions;
import tech.intellispaces.java.reflection.customtype.CustomType;
import tech.intellispaces.java.reflection.method.MethodStatement;

import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

public class HttpPortProcessor extends ArtifactProcessor {

  public HttpPortProcessor() {
    super(ElementKind.INTERFACE, HttpPort.class, AnnotationProcessorConstants.SOURCE_VERSION);
  }

  @Override
  public boolean isApplicable(CustomType entityType) {
    return AnnotationProcessorFunctions.isAutoGenerationEnabled(entityType);
  }

  @Override
  public ArtifactValidator validator() {
    return null;
  }

  @Override
  public List<ArtifactGenerator> makeGenerators(CustomType portDomain, ArtifactGeneratorContext context) {
    List<ArtifactGenerator> generators = new ArrayList<>();
    generators.add(new HttpPortHandleGenerator(portDomain));
    generators.add(new HttpPortProviderGenerator(portDomain));

    List<CustomType> ontologies = portDomain.selectAnnotation(HttpPort.class.getCanonicalName()).orElseThrow()
        .value().orElseThrow()
        .asArray().orElseThrow()
        .elements().stream()
        .map(e -> e.asClass().orElseThrow().type())
        .toList();
    for (CustomType ontology : ontologies) {
      generators.add(new HttpPortGuideGenerator(portDomain, ontology));
      addHttpPortExchangeChannels(generators, portDomain, ontology);
    }
    return generators;
  }

  private void addHttpPortExchangeChannels(
      List<ArtifactGenerator> generators,
      CustomType portDomain,
      CustomType ontology
  ) {
    ontology.actualMethods().forEach(m -> addHttpPortExchangeChannels(generators, portDomain, ontology, m));
  }

  private void addHttpPortExchangeChannels(
      List<ArtifactGenerator> generators,
      CustomType portDomain,
      CustomType ontology,
      MethodStatement channelMethod
  ) {
    generators.add(
      new HttpPortExchangeChannelGenerator(portDomain, ontology, channelMethod)
    );
  }
}
