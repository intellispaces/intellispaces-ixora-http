package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.HttpResponses;
import tech.intellispaces.jaquarius.annotation.Guide;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponse exchange(TestPort port, HttpRequest request) {
    return HttpResponses.ok("Hello");
  }
}
