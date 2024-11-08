package intellispaces.ixora.http.port;

import intellispaces.common.base.type.ClassFunctions;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.common.javastatement.method.MethodStatement;
import intellispaces.ixora.http.common.HttpNameConventionFunctions;
import intellispaces.jaquarius.channel.Channel1;

import java.util.Optional;

import intellispaces.common.base.exception.UnexpectedExceptions;

public interface PortFunctions {

  @SuppressWarnings("unchecked")
  static Class<? extends Channel1> getChannelClass(
      CustomType portDomain, CustomType ontologyType, MethodStatement channelMethod
  ) {
    String channelClassCanonicalName = HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(
        portDomain, ontologyType, channelMethod
    );
    Optional<Class<?>> channelClass = ClassFunctions.getClass(channelClassCanonicalName);
    if (channelClass.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Could not find channel class by name {0}",
          channelClassCanonicalName);
    }
    return (Class<? extends Channel1>) channelClass.get();
  }
}
