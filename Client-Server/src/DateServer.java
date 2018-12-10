import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer
{
    public static void main(String args[]) throws IOException
    {
        final int PORT=8880;
        PrintWriter out;

        ServerSocket server= new ServerSocket(PORT);

        while(true)
        {
            Socket s= server.accept();

            out= new PrintWriter(s.getOutputStream());

            out.print(new Date().toString());
            out.flush();

            out.close();
            s.close();



        }

    }



}
