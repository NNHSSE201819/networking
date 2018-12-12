import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WebService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;

    public WebService(Socket aSocket)
    {
        s = aSocket;
    }

    public void run()
    {

        try
        {
            in = new Scanner(s.getInputStream());
            out = new PrintWriter((s.getOutputStream()));
            doService();
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void doService() throws IOException
    {

        while(true)
        {
            if(!in.hasNext())
            {
                return;
            }
            String command = in.next();
            if(command.equals("QUIT"))
            {
                return;
            }
            else{
                executeCommand(command);
            }
        }
    }

    public void executeCommand(String command)
    {

        if(command.equals("GET"))
        {
            System.out.println("DONE");
            System.out.println("HTTP/1.1 200 OK");
            out.print("HTTP/1.1 200 OK");
            out.flush();
        }

    }

}