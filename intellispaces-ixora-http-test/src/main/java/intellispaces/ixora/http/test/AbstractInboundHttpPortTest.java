package intellispaces.ixora.http.test;

import intellispaces.ixora.http.HttpPortExchangeChannel;
import intellispaces.ixora.http.MovableInboundHttpPort;
import intellispaces.jaquarius.system.Modules;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInboundHttpPortTest {

  private static final int PORT_NUMBER = 8080;

  public abstract MovableInboundHttpPort getOperativePort(
      int portNumber, Class<? extends HttpPortExchangeChannel> exchangeChannel
  );

  public void init() {
    Modules.get(TestPortExchangeGuideImpl.class).start();
  }

  public void deinit() {
    Modules.current().stop();
  }

  public void testHello() throws Exception {
    MovableInboundHttpPort operativePort = getOperativePort(PORT_NUMBER, TestPortExchangeChannel.class);
    MovableTestPort logicalPort = TestPorts.get(operativePort);
    operativePort.addProjection(TestPortDomain.class, logicalPort);

    operativePort.open();

    HttpResponse<String> res = callServer();
    String message = res.body();

    operativePort.close();

    assertThat(message).isEqualTo("Hello");
  }

  private HttpResponse<String> callServer() throws Exception {
    var req = java.net.http.HttpRequest.newBuilder()
        .uri(new URI("http://localhost:" + PORT_NUMBER))
        .GET()
        .build();
    return HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
  }
}
