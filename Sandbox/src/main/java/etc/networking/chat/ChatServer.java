package etc.networking.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) throws IOException {
        int port = 10007;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket server = serverSocket.accept();
        System.out.println("Listening at port=" + port);

        BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter output = new PrintWriter(server.getOutputStream(), true);
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (true) {
            line = input.readLine();
            if (line != null && line.equalsIgnoreCase("bye")) {
                System.out.println("Bye");
                break;
            }
            System.out.println("Client: " + line);
            System.out.print("Server: ");
            line = serverInput.readLine();
            output.println(line);
        }

        serverSocket.close();
        server.close();
        input.close();
        output.close();
        serverInput.close();
    }
}
