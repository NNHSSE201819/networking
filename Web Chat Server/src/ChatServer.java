import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer
{
    public static void main(String args[]) throws IOException
    {
        final int PORT= 8080;

        ServerSocket server= new ServerSocket(PORT);

        while(true)
        {
            Socket s= server.accept();

            Thread t= new Thread(new ChatService(s));
            t.start();

        }
    }


}
