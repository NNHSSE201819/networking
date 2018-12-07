import java.io.File;
import java.io.FileNotFoundException;
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
        System.out.println("constructor");
        this.socket=s;

    }

    public void run()
    {
        try
        {
            System.out.println("run");
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
        System.out.println("doservice called");
        if(!this.in.hasNext())
        {
            return;
        }

        String command= in.next();
        System.out.println(command);

        if (command.equals("GET"))
        {
            if(!this.in.hasNext())
            {
                return;
            }

            command= in.next();
            command= command.substring(command.indexOf("/")+1);
            System.out.println(command);
            try(Scanner s= new Scanner(new File(command)))
            {
                out.print("HTTP/1.1 200 OK\n");
                out.flush();

                while(s.hasNextLine())
                {
                    out.print(s.nextLine());
                    out.flush();
                }

            }
            catch(FileNotFoundException e)
            {
                out.print("404 Not Found");
                out.flush();
            }
        }
        else
        {
            out.print("Invalid Command");
            out.flush();
        }


    }



}
