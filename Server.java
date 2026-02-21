import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args)
    {
        try{
            int port = System.getenv("PORT")!=null? Integer.parseInt(System.getenv("PORT")):8080;
            HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
            server.createContext("/",new MyHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("Server is running on port: 8000");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    static class MyHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String response = "Hello, this is an example for my new webserver";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
