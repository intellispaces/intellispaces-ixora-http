package intellispaces.ixora.http.test;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;
import intellispaces.framework.core.traverse.TraverseTypes;
import intellispaces.ixora.http.InboundHttpPortDomain;

@Domain("d0d4f50d-9042-4970-a523-7af9d6a1a8ff")
public interface TestPortDomain extends InboundHttpPortDomain {

  @Channel("03db10b5-a7fe-401c-bf5b-23a490319191")
  InboundHttpPortDomain asInboundHttpPort();

  @Channel(value = "2aaed635-8887-4f09-924f-7762e674d49d", allowedTraverse = TraverseTypes.Moving)
  TestPortDomain open();

  @Channel(value = "af7ad7ba-d029-4ff2-8b0e-cd34155d046a", allowedTraverse = TraverseTypes.Moving)
  TestPortDomain close();
}