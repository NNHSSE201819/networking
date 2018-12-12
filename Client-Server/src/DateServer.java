import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Date;

public class DateServer
{
    public static void main(String[] args) throws IOException
    {
        final int SERVER_PORT = 8080;

        ServerSocket server = new ServerSocket(SERVER_PORT);
        System.out.println("Waiting for clients to connect...");

        while(true)
        {
            Socket s = server.accept();

            System.out.println(new Date().toString());
            server.close();
        }

    }
}