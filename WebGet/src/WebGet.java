import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class WebGet
{
    public static void main(String[] args) throws IOException
    {
        String host;
        String resource;

        if(args.length==2)
        {
            host=args[0];
            resource=args[1];
        }
        else
        {
            System.out.println("Getting / from horstmann.com");
            host= "horstmann.com";
            resource="/";
        }

        //Open socket

        final int HTTP_PORT =80;

        try(Socket s= new Socket(host, HTTP_PORT))
        {
            //Get Streams

            InputStream instream = s.getInputStream();
            OutputStream outstream= s.getOutputStream();

            //turn streams in scanners and writers

            Scanner in= new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);
            //send command

            String command= "GET"+resource+" HTTP/1.1\n"+"Host: "+host+"\n\n";
            out.print(command);
            out.flush();

            //read server response


            while(in.hasNextLine())
            {
                String input= in.nextLine();


                if(input.contains("Content-Type"))
                {
                    System.out.println(input);
                    break;
                }

                System.out.println(input);

            }
        }

    }

}
