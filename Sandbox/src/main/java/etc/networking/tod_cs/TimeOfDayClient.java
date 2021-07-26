package etc.networking.tod_cs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class TimeOfDayClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 55555;
        if (args.length > 1) port = Integer.parseInt(args[1]);

        DatagramSocket socket = new DatagramSocket();

        socket.setSoTimeout(1000);

        byte[] buffer = new byte[512];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                                                   new InetSocketAddress(host, port));

        for (int i = 0; i < 3; i++) {
            try {
                packet.setLength(0);
                socket.send(packet);

                packet.setLength(buffer.length);
                socket.receive(packet);

                //System.out.print(new String(buffer, 0, packet.getLength(), "US-ASCII"));
                System.out.print(new String(buffer, 0, packet.getLength(),
                                            StandardCharsets.US_ASCII));
                break;
            } catch (SocketTimeoutException e) {
                System.out.println("No response");
            }
        }
        socket.close();
    }

}
