package tech.intellispaces.ixora.http.test;

import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.Channel;

@Channel("279f7e16-e59b-474d-96d0-8e53cbca9478")
public interface TestPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(TestPortDomain source, HttpRequestDomain request) throws HttpException;
}
