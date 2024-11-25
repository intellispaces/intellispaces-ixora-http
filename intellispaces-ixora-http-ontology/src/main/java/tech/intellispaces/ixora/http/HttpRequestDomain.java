package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.UriDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;

/**
 * Abstract HTTP request.
 */
@Domain("7424475c-e034-47f7-9627-5ca8845867c9")
public interface HttpRequestDomain extends HttpMessageDomain {

  @Channel("5d96b126-71a7-4583-ad75-9636b977fd15")
  HttpMessageDomain asHttpMessage();

  @Channel("35fd9d51-2aca-4ddd-aee3-559f513fa82c")
  UriDomain requestURI();

  @Channel("76494aaf-99bd-42df-8bf5-3a1a28b137d7")
  HttpMethodDomain method();
}
