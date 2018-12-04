import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer
{
    public static void main(String[] args) throws IOException
    {
        final int PORT=8080;

        ServerSocket server= new ServerSocket(PORT);

        try(Socket s= server.accept())
        {
            while(true)
            {
                SimpleService service= new SimpleService(s);
                Thread t= new Thread(service);
                t.start();
            }
        }


    }

}
