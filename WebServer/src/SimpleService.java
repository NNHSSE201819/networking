import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleService implements Runnable
{
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public SimpleService(Socket s)
    {
        this.socket=s;

    }

    public void run()
    {
        try
        {
            this.in= new Scanner(this.socket.getInputStream());
            this.out= new PrintWriter(this.socket.getOutputStream());
            this.doService();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void doService() throws IOException
    {
        if(!this.in.hasNext())
        {
            return;
        }

        String command= in.next(); //need to do something with command to open file
        if (command.toUpperCase().contains("GET"))
        {
            String input;
            while(in.hasNext())
            {

            }
        }
        else
        {
            System.out.println("Invalid Command");
        }




    }



}
