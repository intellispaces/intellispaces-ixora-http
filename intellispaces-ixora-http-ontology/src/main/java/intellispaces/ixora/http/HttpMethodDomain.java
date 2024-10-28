package intellispaces.ixora.http;

import intellispaces.jaquarius.annotation.Channel;
import intellispaces.jaquarius.annotation.Domain;

@Domain("a9b7f614-28cb-414b-8fb6-eb54fce9eea6")
public interface HttpMethodDomain {

  @Channel("4335c27b-035e-41a8-abbc-535fb9b403a7")
  String name();

  @Channel("1b73f498-f9e2-4fcf-93d6-b7d08e42dea1")
  Boolean isGet();

  @Channel("a824c452-21bc-4abe-a3df-9eba533e1074")
  Boolean isHead();

  @Channel("30be3265-926a-4f5f-94d0-7813f84f20c3")
  Boolean isPost();

  @Channel("57561089-d33d-41ea-b1a5-c7e79877c50f")
  Boolean isPut();

  @Channel("94c17678-1550-4434-b980-0505d098f1ae")
  Boolean isPatch();

  @Channel("a068223e-11a5-403d-9bbe-5e065fea53ba")
  Boolean isDelete();

  @Channel("94765e29-6256-45c3-80dd-90bb3de79f43")
  Boolean isOptions();

  @Channel("71afbc7e-1a14-4fdf-a9c7-330ffc520094")
  Boolean isTrace();
}
