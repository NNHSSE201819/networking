import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private ArrayList<Socket> connections;
    private String clientName;

    public ChatService(Socket socket, ArrayList<Socket> connections) throws IOException
    {
        this.s=socket;
        this.connections=connections;
    }


    public void run()
    {
        try
        {
            this.in= new Scanner(this.s.getInputStream());
            this.out= new PrintWriter(this.s.getOutputStream());

            this.chatService();
        }
        catch(IOException e)
        {
            //System.out.println("Socket Error");
            this.out.println("Socket Error");
            this.out.flush();
        }
    }

    public void chatService()
    {
        while(true)
        {
            if(!in.hasNext())
            {
                return;
            }

            String command= in.next().toUpperCase();

            if(command.equals("LOGOUT"))
            {
                this.connections.remove(this.s);
                return;
            }

            this.doCommand(command);
        }

    }

    public void doCommand(String command)
    {
        if(command.equals("CHAT"))
        {
            try
            {
                this.chat();
            }
            catch(IOException e)
            {
                this.out.print("Error sending message to other clients");
                this.out.flush();
            }
        }
        else if(command.equals("LOGIN"))
        {
            this.login();
        }
        else
        {
            this.out.println("Invalid Command");
            this.out.flush();
            return;
        }
    }

    public void login()
    {
        if(this.in.hasNext())
        {
            this.clientName=this.in.next();
        }
    }

    public void chat() throws IOException
    {
        PrintWriter currentOut;
        String message="";

        while(this.in.hasNextLine())
        {
            message+=in.nextLine();
        }

        for(int i=0; i<this.connections.size(); i++)
        {
            if(this.connections.get(i).isClosed())
            {
                this.connections.remove(i);
            }
            else
            {
                currentOut= new PrintWriter(this.connections.get(i).getOutputStream());
                currentOut.print(clientName+": "+message);
                currentOut.flush();
            }
        }

    }
}
