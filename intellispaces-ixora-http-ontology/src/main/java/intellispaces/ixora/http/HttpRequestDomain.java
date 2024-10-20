package intellispaces.ixora.http;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.framework.core.annotation.Domain;

@Domain("7424475c-e034-47f7-9627-5ca8845867c9")
public interface HttpRequestDomain extends HttpMessageDomain {

  @Channel("5d96b126-71a7-4583-ad75-9636b977fd15")
  HttpMessageDomain asHttpMessage();

  @Channel("35fd9d51-2aca-4ddd-aee3-559f513fa82c")
  String requestURI();

  @Channel("76494aaf-99bd-42df-8bf5-3a1a28b137d7")
  HttpMethodDomain method();
}
