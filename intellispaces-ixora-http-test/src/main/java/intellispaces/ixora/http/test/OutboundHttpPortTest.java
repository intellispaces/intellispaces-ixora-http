package intellispaces.ixora.http.test;

import com.sun.net.httpserver.HttpServer;
import intellispaces.ixora.http.HttpMethods;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpRequests;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.MovableOutboundHttpPort;
import intellispaces.ixora.http.OutboundHttpPort;
import org.assertj.core.api.Fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

/**
 * Tests for {@link OutboundHttpPort} implementations.
 */
public class OutboundHttpPortTest {
  private static final String HELLO_ENDPOINT = "/api/hello";
  private static final String HELLO_RESPONSE = "Hello!";

  public void testHello(MovableOutboundHttpPort port) {
    HttpServer httpServer = null;
    try {
      // Given
      httpServer = createHelloServer();
      httpServer.start();

      HttpRequest request = HttpRequests.get(HttpMethods.get(), HELLO_ENDPOINT);

      // When
      HttpResponse response = port.exchange(request);

      // Then
//    assertThat(httpServer).

    } catch (IOException e) {
      Fail.fail("Couldn't create test HTTP server", e);

    } finally {
      if (httpServer != null) {
        httpServer.stop(0);
      }
    }
  }

  private HttpServer createHelloServer() throws IOException {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
    httpServer.createContext(HELLO_ENDPOINT, exchange -> {
      byte[] response = HELLO_RESPONSE.getBytes();
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
      exchange.getResponseBody().write(response);
      exchange.close();
    });
    return httpServer;
  }
}
