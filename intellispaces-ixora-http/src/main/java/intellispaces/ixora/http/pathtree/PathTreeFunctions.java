package intellispaces.ixora.http.pathtree;

import intellispaces.common.javastatement.customtype.ClassType;
import intellispaces.common.javastatement.customtype.Classes;
import intellispaces.common.javastatement.customtype.CustomType;
import intellispaces.common.javastatement.customtype.CustomTypes;
import intellispaces.common.javastatement.method.MethodStatement;
import intellispaces.ixora.http.HttpMethod;
import intellispaces.ixora.http.HttpMethods;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.annotation.Get;
import intellispaces.ixora.http.annotation.HttpOntology;
import intellispaces.ixora.http.annotation.HttpPort;
import intellispaces.ixora.internet.JoinUrlAutoGuide;
import intellispaces.ixora.internet.JoinUrlGuide;
import intellispaces.ixora.internet.SplitUriPathAutoGuide;
import intellispaces.ixora.internet.SplitUriPathGuide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PathTreeFunctions {
  private static final JoinUrlGuide JOIN_URL_GUIDE = new JoinUrlAutoGuide();
  private static final SplitUriPathGuide SPLIT_URI_PATH_GUIDE = new SplitUriPathAutoGuide();

  public static List<PathSegment> readPathTree(
      Object port, Class<?> portDomain
  ) {
    List<PathSegment> rootSegments = new ArrayList<>();

    CustomType portType = CustomTypes.of(portDomain);
    HttpPort httpPort = Classes.of(portDomain).selectAnnotation(HttpPort.class).orElseThrow();
    for (Class<?> ontologyClass : httpPort.value()) {
      ClassType ontologyType = Classes.of(ontologyClass);
      HttpOntology httpOntology = ontologyType.selectAnnotation(HttpOntology.class).orElseThrow();
      for (MethodStatement channelMethod : ontologyType.actualMethods()) {
        String path;
        HttpMethod httpMethod;
        if (channelMethod.hasAnnotation(Get.class)) {
          httpMethod = HttpMethods.get();
          path = channelMethod.selectAnnotation(Get.class).orElseThrow().value();
        } else {
          continue;
        }
        path = JOIN_URL_GUIDE.map(httpOntology.path(), path);
        addSegments(port, portType, ontologyType, rootSegments, path, httpMethod, channelMethod);
      }
    }
    return rootSegments;
  }

  private static void addSegments(
      Object port,
      CustomType portDomain,
      CustomType ontologyType,
      Collection<PathSegment> rootSegments,
      String path,
      HttpMethod httpMethod,
      MethodStatement channelMethod
  ) {
    List<String> pathSegments = SPLIT_URI_PATH_GUIDE.map(path).nativeList();
    addSegments(port, portDomain, ontologyType, rootSegments, pathSegments, httpMethod, channelMethod);
  }

  private static void addSegments(
      Object port,
      CustomType portDomain,
      CustomType ontologyType,
      Collection<PathSegment> rootSegments,
      List<String> pathSegments,
      HttpMethod httpMethod,
      MethodStatement channelMethod
  ) {
    PathSegment finishSegment = null;
    Collection<PathSegment> curSegments = rootSegments;
    int index = 0;
    for (String pathSegment : pathSegments) {
      boolean match = false;
      for (PathSegment curSegment : curSegments) {
        if (pathSegment.equals(curSegment.value())) {
          match = true;
          curSegments = curSegment.next();
          finishSegment = curSegment;
          break;
        }
      }
      if (!match) {
        PathSegment newSegment = makeSegment(index, pathSegment);
        curSegments.add(newSegment);
        curSegments = newSegment.next();
        finishSegment = newSegment;
      }
      index++;
    }

    FinalExecutor executor = new FinalExecutor(
        port, intellispaces.ixora.http.port.PortFunctions.getChannelClass(portDomain, ontologyType, channelMethod), channelMethod.signature()
    );
    finishSegment.executorGet(executor);
  }

  private static PathSegment makeSegment(int index, String value) {

    return new StrictPathSegment(index, value);

  }

  public static FinalExecutor findExecution(
      HttpRequest request, java.util.List<PathSegment> rootSegments
  ) {
    String path = request.requestURI().path();
    List<String> pathSegments = SPLIT_URI_PATH_GUIDE.map(path).nativeList();

    PathSegment finishSegment = null;
    Collection<PathSegment> curSegments = rootSegments;
    for (String pathSegment : pathSegments) {
      finishSegment = null;
      for (PathSegment curSegment : curSegments) {
        if (curSegment.conform(pathSegment)) {
          curSegments = curSegment.next();
          finishSegment = curSegment;
          break;
        }
      }
      if (finishSegment == null) {
        return null;
      }
    }
    return finishSegment != null ? finishSegment.executorGet() : null;
  }

  private PathTreeFunctions() {}
}