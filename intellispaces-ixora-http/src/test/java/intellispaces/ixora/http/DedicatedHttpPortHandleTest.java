package intellispaces.ixora.http;

import intellispaces.jaquarius.annotation.Preprocessing;
import intellispaces.jaquarius.system.Modules;
import intellispaces.ixora.internet.JoinUrlGuideImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DedicatedHttpPortHandle} class.
 */
@Preprocessing(JoinUrlGuideImpl.class)
public class DedicatedHttpPortHandleTest {

  @BeforeEach
  public void init() {
    Modules.get(JoinUrlGuideImpl.class).start();
  }

  @AfterEach
  public void deinit() {
    Modules.current().stop();
  }

  @Test
  public void test() {
    // Given
    HttpMethod httpGetMethod = HttpMethods.get();
    HttpMethod httpPostMethod = HttpMethods.post();

    MovableHttpPort underlyingPort = mock(MovableHttpPort.class);

    HttpResponse response1 = mock(HttpResponse.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpGetMethod.name())
        && req.requestURI().equals("http:localhost:8080/api/test")))
    ).thenReturn(response1);

    HttpResponse response2 = mock(HttpResponse.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpPostMethod.name())
        && req.requestURI().equals("http:localhost:8080/api/test")))
    ).thenReturn(response2);

    String baseUrl = "http:localhost:8080/api";
    var dedicatedHttpPort = new DedicatedHttpPortHandleImpl(baseUrl, underlyingPort);

    // When
    HttpResponse actualResponse1 = dedicatedHttpPort.exchange("/test", httpGetMethod);
    HttpResponse actualResponse2 = dedicatedHttpPort.exchange("test", httpPostMethod);

    // Then
    assertThat(actualResponse1).isSameAs(response1);
    assertThat(actualResponse2).isSameAs(response2);
  }
}
