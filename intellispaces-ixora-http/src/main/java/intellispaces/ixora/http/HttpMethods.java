package intellispaces.ixora.http;

public interface HttpMethods {

  HttpMethod GET = new HttpMethodHandleImpl("GET");

  HttpMethod POST = new HttpMethodHandleImpl("POST");

  HttpMethod PUT = new HttpMethodHandleImpl("PUT");

  HttpMethod DELETE = new HttpMethodHandleImpl("DELETE");
}
