package intellispaces.ixora.http.test;

import intellispaces.framework.core.annotation.Channel;
import intellispaces.ixora.http.HttpPortExchangeChannel;
import intellispaces.ixora.http.HttpRequestDomain;
import intellispaces.ixora.http.HttpResponseDomain;
import intellispaces.ixora.http.exception.HttpException;

@Channel("279f7e16-e59b-474d-96d0-8e53cbca9478")
public interface TestPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(TestPortDomain source, HttpRequestDomain request) throws HttpException;
}
