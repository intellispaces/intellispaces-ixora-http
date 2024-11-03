package intellispaces.ixora.http.test;

import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.HttpResponses;
import intellispaces.jaquarius.annotation.Guide;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponse exchange(TestPort port, HttpRequest request) {
    return HttpResponses.ok("Hello");
  }
}
