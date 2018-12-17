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
        this.clientName="anonymous";
    }


    public void run()
    {
        try
        {
            this.in= new Scanner(this.s.getInputStream());
            this.out= new PrintWriter(this.s.getOutputStream());

            System.out.println("Run works");
            this.out.println("Run works");
            this.chatService();

        }
        catch(IOException e)
        {
            System.out.println("Socket Error");
            this.out.println("Socket Error");
            this.out.flush();
        }
    }

    public void chatService()
    {
        while(true)
        {
            String command= in.next().toUpperCase();

            System.out.println("Command received by service: "+command);
            this.out.print("command recieved by service: "+command);
            this.out.flush();

            if(command.equals("LOGOUT"))
            {
                return;
            }

            this.doCommand(command);
        }

    }

    public void doCommand(String command)
    {
        if(command.equals("CHAT"))
        {
            out.print("Enter your message: ");
            out.flush();

            if(this.in.hasNext())
            {
                this.chat();
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
        System.out.println("Username: "+ this.clientName);

        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chat()
    {
        System.out.println("in chat method");



        PrintWriter currentOut;
        String message="";

        message=this.in.nextLine();

        System.out.println(message);

        for(int i=0; i<this.connections.size(); i++)
        {
            if(!this.connections.get(i).isClosed())
            {
                try
                {
                    currentOut= new PrintWriter(this.connections.get(i).getOutputStream());
                    System.out.println(clientName+": "+message);
                    currentOut.print(clientName+": "+message);
                    currentOut.flush();
                }
                catch( IOException e)
                {
                    this.out.print("Problem sending message to another user");
                    this.out.flush();
                }

            }
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
