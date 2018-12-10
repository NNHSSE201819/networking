import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DateClient
{
    public static void main(String arg[]) throws IOException
    {
        final int PORT=8880;
        Scanner in;

        Socket s= new Socket("localhost", PORT);

        in= new Scanner(s.getInputStream());

        while(in.hasNext())
        {
            System.out.print(" "+in.next());
        }

        in.close();
        s.close();

    }

}
