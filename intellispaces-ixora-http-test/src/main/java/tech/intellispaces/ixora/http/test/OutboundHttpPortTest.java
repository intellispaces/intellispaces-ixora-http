package tech.intellispaces.ixora.http.test;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Fail;

import tech.intellispaces.general.collection.ArraysFunctions;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.HttpStatusHandle;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.object.reference.ObjectHandleFunctions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link tech.intellispaces.ixora.http.OutboundHttpPortHandle} implementations.
 */
public abstract class OutboundHttpPortTest {
  private static final String TEST_ADDRESS = "http://localhost";
  private static final String HELLO_ENDPOINT = "/hello";
  private static final String HELLO_RESPONSE = "Hello!";

  protected abstract MovableOutboundHttpPortHandle getPort();

  public void testHello() {
    HttpServer server = null;
    HttpResponseHandle response = null;
    try {
      // Given
      server = getServer();
      server.start();

      HttpRequestHandle request = HttpRequests.get(HttpMethods.get(), TEST_ADDRESS + HELLO_ENDPOINT);

      MovableOutboundHttpPortHandle port = getPort();

      // When
      response = port.exchange(request);

      // Then
      HttpStatusHandle status = response.status();
      assertThat(status.isOk()).isTrue();

      byte[] body = ArraysFunctions.toByteArray(response.bodyStream().readAll().nativeList());
      assertThat(new String(body, StandardCharsets.UTF_8)).isEqualTo(HELLO_RESPONSE);
    } catch (IOException | HttpException e) {
      Fail.fail("Unexpected exception", e);
    } finally {
      ObjectHandleFunctions.releaseSilently(response);
      if (server != null) {
        server.stop(0);
      }
    }
  }

  private HttpServer getServer() throws IOException {
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
