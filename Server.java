import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Server {
    static int x=0;
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
            long days = CalculateDuration();
            String response = days+" Days Left for Exam \n Site visited "+x+" times";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static long CalculateDuration(){
        x++;
        LocalDate dateBefore = LocalDate.now();
        LocalDate dateAfter = LocalDate.of(2026,Month.JUNE,15);
        long daysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        System.out.println(daysBetween);

        return daysBetween;
    }
}
