import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;

    public ChatService(Socket socket) throws IOException
    {
        this.s=socket;
    }


    public void run()
    {
        try
        {
            this.in= new Scanner(this.s.getInputStream());
            this.out= new PrintWriter(this.s.getOutputStream());
        }
        catch(IOException e)
        {
            System.out.println("Problem with socket");
        }
    }

    public void commands()
    {

    }

}
