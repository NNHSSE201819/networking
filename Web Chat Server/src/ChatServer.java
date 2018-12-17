import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer
{
    public static void main(String args[]) throws IOException
    {
        final int PORT= 8080;

        ArrayList<Socket> connections= new ArrayList<Socket>();

        ServerSocket server= new ServerSocket(PORT);

        while(true)
        {
            Socket s= server.accept();
            connections.add(s);

            Thread t= new Thread(new ChatService(s, connections));
            t.start();

        }
    }


}
