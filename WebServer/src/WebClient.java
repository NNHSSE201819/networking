import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WebClient
{
    public static void main(String[] args) throws IOException
    {
        final int SERVER_PORT = 8080;

        try(Socket s = new Socket("localhost", SERVER_PORT))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            String command = "GET "

        }

    }
}
