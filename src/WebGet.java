import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class WebGet
{
    public static void main(String[] args) throws IOException
    {
        // get command line arguments

        String host;
        String resource;

        if (args.length == 2)
        {
            host = args[0];
            resource = args[1];
        }
        else
        {
            System.out.println("Getting / from horstmann.com'");
            host = "horstmann.com";
            resource = "/";
        }
        //open socket

        final int HTTP_PORT = 80;
        try (Socket s = new Socket(host, HTTP_PORT))
        {
            // get streams

            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();

            // turn streams into scanners and writerss

            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            // send command

            String command = "GET" + resource + "HTTP/1.1\n" +
                    "Host: " + host + "\n\n";
            out.print(command);
            out.flush();

            // read server response

            while (in.hasNextLine())
            {
                String input = in.nextLine();
                if(input.equals(""))
                {
                    break;
                }
                System.out.println(input);
            }
        }
    }
}
