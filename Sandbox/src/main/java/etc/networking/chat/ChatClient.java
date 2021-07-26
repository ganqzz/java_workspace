package etc.networking.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        //Socket client = new Socket("127.0.0.1", 10007); // connect込み
        Socket client = new Socket();
        String host = "127.0.0.1";
        int port = 10007;
        InetSocketAddress address = new InetSocketAddress(host, port);
        client.connect(address);
        System.out.println("Connecting to [host=" + host + ", port=" + port + "]");

        BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter output = new PrintWriter(client.getOutputStream(), true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (true) {
            System.out.print("Client: ");
            line = clientInput.readLine();
            output.println(line);
            line = input.readLine();
            System.out.println("Server: " + line);
            if (line != null && line.equalsIgnoreCase("bye")) {
                System.out.println("Bye");
                break;
            }
        }

        client.close();
        input.close();
        clientInput.close();
        output.close();
    }
}
