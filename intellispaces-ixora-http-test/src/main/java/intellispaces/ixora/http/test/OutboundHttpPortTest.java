package intellispaces.ixora.http.test;

import com.sun.net.httpserver.HttpServer;
import intellispaces.common.base.collection.ArraysFunctions;
import intellispaces.jaquarius.object.ObjectFunctions;
import intellispaces.ixora.http.HttpMethods;
import intellispaces.ixora.http.HttpRequest;
import intellispaces.ixora.http.HttpRequests;
import intellispaces.ixora.http.HttpResponse;
import intellispaces.ixora.http.HttpStatus;
import intellispaces.ixora.http.MovableOutboundHttpPort;
import intellispaces.ixora.http.OutboundHttpPort;
import intellispaces.ixora.http.exception.HttpException;
import org.assertj.core.api.Fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link OutboundHttpPort} implementations.
 */
public class OutboundHttpPortTest {
  private static final String TEST_ADDRESS = "http://localhost";
  private static final String HELLO_ENDPOINT = "/hello";
  private static final String HELLO_RESPONSE = "Hello!";

  public void testHello(MovableOutboundHttpPort port) {
    HttpServer httpServer = null;
    HttpResponse response = null;
    try {
      // Given
      httpServer = createServer();
      httpServer.start();

      HttpRequest request = HttpRequests.get(HttpMethods.get(), TEST_ADDRESS + HELLO_ENDPOINT);

      // When
      response = port.exchange(request);

      // Then
      HttpStatus status = response.status();
      assertThat(status.isOk()).isTrue();

      byte[] body = ArraysFunctions.toByteArray(response.bodyStream().readAll().nativeList());
      assertThat(new String(body, StandardCharsets.UTF_8)).isEqualTo(HELLO_RESPONSE);
    } catch (IOException | HttpException e) {
      Fail.fail("Unexpected exception", e);
    } finally {
      ObjectFunctions.releaseSilently(response);
      if (httpServer != null) {
        httpServer.stop(0);
      }
    }
  }

  private HttpServer createServer() throws IOException {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(80), 0);
    httpServer.createContext(HELLO_ENDPOINT, exchange -> {
      byte[] res = HELLO_RESPONSE.getBytes();
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, res.length);
      exchange.getResponseBody().write(res);
      exchange.close();
    });
    return httpServer;
  }
}
