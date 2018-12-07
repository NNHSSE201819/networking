import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer
{
    public static void main(String[] args) throws IOException
    {
        final int PORT=8080;

        ServerSocket server= new ServerSocket(PORT);
        System.out.println("Server socket created");

        while(true)
        {
            Socket s= server.accept();
            System.out.println("accepted connection");
            SimpleService service= new SimpleService(s);
            Thread t= new Thread(service);
            t.start();

        }


    }

}
