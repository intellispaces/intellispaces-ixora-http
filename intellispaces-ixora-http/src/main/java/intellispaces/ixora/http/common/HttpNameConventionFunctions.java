package intellispaces.ixora.http.common;

import intellispaces.common.base.text.TextFunctions;
import intellispaces.common.base.type.TypeFunctions;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.common.javastatement.method.MethodParam;
import intellispaces.common.javastatement.method.MethodStatement;
import intellispaces.common.javastatement.reference.CustomTypeReferences;
import intellispaces.ixora.http.HttpRequestDomain;
import intellispaces.jaquarius.common.NameConventionFunctions;

import java.util.List;

public interface HttpNameConventionFunctions {

  static String getPortHandleCanonicalName(CustomType httpPortDomain) {
    return TextFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "Handle");
  }

  static String getPortHandleImplCanonicalName(CustomType httpPortDomain) {
    return getPortHandleCanonicalName(httpPortDomain) + "Impl";
  }

  static String getPortProviderCanonicalName(CustomType httpPortDomain) {
    return TextFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "s");
  }

  static String getPortGuideCanonicalName(CustomType ontology) {
    return TextFunctions.replaceTailOrElseThrow(ontology.canonicalName(), "Ontology", "Guide");
  }

  static String getPortExchangeChannelCanonicalName(CustomType httpPortDomain) {
    MethodStatement exchangeChannel = httpPortDomain
        .declaredMethod("exchange", List.of(CustomTypeReferences.get(HttpRequestDomain.class)))
        .orElseThrow();
    return NameConventionFunctions.getChannelClassCanonicalName(exchangeChannel);
  }

  static String getActualPortExchangeChannelCanonicalName(
      CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    var sb = new StringBuilder();

    String packageName = TypeFunctions.getPackageName(ontology.canonicalName());
    if (TextFunctions.isNotBlank(packageName)) {
      sb.append(packageName).append(".");
    }
    sb.append(TextFunctions.removeTailOrElseThrow(portDomain.simpleName(), "Domain"));
    sb.append(TextFunctions.capitalizeFirstLetter(channelMethod.name()));
    for (MethodParam param : channelMethod.params()) {
      sb.append(TextFunctions.capitalizeFirstLetter(param.name()));
    }
    sb.append("ExchangeChannel");
    return sb.toString();
  }

  static String getFormalPortExchangeChannelCanonicalName(
      CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    var sb = new StringBuilder();
    sb.append(TextFunctions.removeTailOrElseThrow(portDomain.canonicalName(), "Domain"));
    sb.append(TextFunctions.capitalizeFirstLetter(channelMethod.name()));
    for (MethodParam param : channelMethod.params()) {
      sb.append(TextFunctions.capitalizeFirstLetter(param.name()));
    }
    sb.append("ExchangeChannel");
    return sb.toString();
  }
}
