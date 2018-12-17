import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ChatClient
{
    public static void main(String[] args) throws IOException
    {
        final int ChatServer = 8080;
        try(Socket s = new Socket("localhost", ChatServer))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);
            String command;
            String login;
            String chat="";

            Scanner scn = new Scanner(System.in);


            while(true)
            {
                if(scn.hasNext())
                {
                    command = scn.next();
                    System.out.println("command in client "+command);

                    command+=" "+scn.next()+"\n";
                    out.print(command);
                    out.flush();
                    System.out.println("flushed");
                }

                if(in.hasNext())
                {
                    String message= in.nextLine();
                    System.out.println(message);
                    System.out.println(in.nextLine());
                }


            }

            



        }
    }
}
