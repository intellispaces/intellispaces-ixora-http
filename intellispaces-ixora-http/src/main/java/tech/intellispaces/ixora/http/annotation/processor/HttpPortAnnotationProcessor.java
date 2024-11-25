package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.ixora.http.annotation.HttpPort;
import tech.intellispaces.jaquarius.annotation.processor.AnnotationProcessorFunctions;
import tech.intellispaces.java.annotation.AnnotatedTypeProcessor;
import tech.intellispaces.java.annotation.generator.Generator;
import tech.intellispaces.java.annotation.validator.AnnotatedTypeValidator;
import tech.intellispaces.java.reflection.customtype.CustomType;
import tech.intellispaces.java.reflection.method.MethodStatement;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HttpPortAnnotationProcessor extends AnnotatedTypeProcessor {

  public HttpPortAnnotationProcessor() {
    super(HttpPort.class, Set.of(ElementKind.INTERFACE));
  }

  @Override
  public boolean isApplicable(CustomType entityType) {
    return AnnotationProcessorFunctions.isAutoGenerationEnabled(entityType);
  }

  @Override
  public AnnotatedTypeValidator getValidator() {
    return null;
  }

  @Override
  public List<Generator> makeGenerators(CustomType initiatorType, CustomType portDomain, RoundEnvironment roundEnv) {
    List<Generator> generators = new ArrayList<>();
    generators.add(new HttpPortHandleGenerator(initiatorType, portDomain));
    generators.add(new HttpPortProviderGenerator(initiatorType, portDomain));

    List<CustomType> ontologies = portDomain.selectAnnotation(HttpPort.class.getCanonicalName()).orElseThrow()
        .value().orElseThrow()
        .asArray().orElseThrow()
        .elements().stream()
        .map(e -> e.asClass().orElseThrow().type())
        .toList();
    for (CustomType ontology : ontologies) {
      generators.add(new HttpPortGuideGenerator(initiatorType, portDomain, ontology));
      addHttpPortExchangeChannels(initiatorType, generators, portDomain, ontology);
    }
    return generators;
  }

  private void addHttpPortExchangeChannels(
      CustomType initiatorType,
      List<Generator> generators,
      CustomType portDomain,
      CustomType ontology
  ) {
    ontology.actualMethods().forEach(m -> addHttpPortExchangeChannels(initiatorType, generators, portDomain, ontology, m));
  }

  private void addHttpPortExchangeChannels(
      CustomType initiatorType,
      List<Generator> generators,
      CustomType portDomain,
      CustomType ontology,
      MethodStatement channelMethod
  ) {
    generators.add(
      new HttpPortExchangeChannelGenerator(initiatorType, portDomain, ontology, channelMethod)
    );
  }
}
