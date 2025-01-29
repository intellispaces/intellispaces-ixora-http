package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.HttpResponses;
import tech.intellispaces.jaquarius.annotation.Guide;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponseHandle exchange(TestPortHandle port, HttpRequestHandle request) {
    return HttpResponses.ok("Hello");
  }
}
