import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;

public class BankClient
{
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8888;
        try (Socket s = new Socket("localhost", SBAP_PORT))
        {
            System.out.println("Connected");

            InputStream instream = s.getInputStream();
            OutputStream  outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            String command = "Deposit 3 1000\n";
            System.out.print("Sending" + command);
            out.print(command);
            out.flush();
            String response = in.nextLine();
            System.out.println("Reciving: " + response);

            command = "Withdraw  3 500\n";
            System.out.println("Sending: " + command);
            out.print(command);
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "QUIT\n";
            System.out.print("Sending: " + command);
            out.print(command);
            out.flush();
        }
    }
}
