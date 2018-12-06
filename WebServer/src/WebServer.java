
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class WebServer
{
    public static void main(String[] args) throws IOException
    {
        final int SERVER_PORT = 8080;

        ServerSocket server = new ServerSocket(SERVER_PORT);
        System.out.println("Waiting for clients to connect...");

        while(true)
        {
            try(Socket s = server.accept())
            {
                System.out.println("Client connected.");
                WebService service = new WebService(s);
                Thread t = new Thread(service);
                t.start();

            }
        }

    }
}