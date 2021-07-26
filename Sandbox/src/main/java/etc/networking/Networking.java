package etc.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

class ServerSocketDemo {
    public static void main(String args[]) {
        try {
            // ポートを取得する
            int port = Integer.parseInt(args[0]);

            // 乱数ジェネレータを作成する
            Random random = new Random();

            // サーバーソケットを作成する
            ServerSocket ss = new ServerSocket(port);

            // 無限ループを作成する
            while (true) {
                // クライアントからの要求を受け取る
                Socket s = ss.accept();

                // 結果をクライアントに書き込む
                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(random.nextInt());

                // ストリームをクローズする
                dos.close();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}

class SocketDemo {
    public static void main(String args[]) {
        try {
            // サーバーとポートを取得する
            String server = args[0];
            int port = Integer.parseInt(args[1]);

            // ソケットを作成する
            Socket s = new Socket(server, port);

            // サーバーから乱数を読み取る
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            int i = dis.readInt();

            // 結果を表示する
            System.out.println(i);

            // ストリームをクローズする
            dis.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}

class DatagramReceiver {
    private final static int BUFSIZE = 20;

    public static void main(String args[]) {
        try {
            // ポートを取得する
            int port = Integer.parseInt(args[0]);

            // ポートのDatagramSocketオブジェクトを作成する
            DatagramSocket ds = new DatagramSocket(port);

            // 着信したデータを保持するバッファを作成する
            byte buffer[] = new byte[BUFSIZE];

            // 無限ループを作成する
            while (true) {
                // データグラムパケットを作成する
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

                // データを受け取る
                ds.receive(dp);

                // データグラムパケットからデータを得る
                String str = new String(dp.getData());

                // データを表示する
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DatagramSender {
    public static void main(String args[]) {
        try {
            // 宛先のインターネットアドレスを作成する
            InetAddress ia = InetAddress.getByName(args[0]);

            // 宛先ポートを取得する
            int port = Integer.parseInt(args[1]);

            // データグラムソケットを作成する
            DatagramSocket ds = new DatagramSocket();

            // データグラムパケットを作成する
            byte buffer[] = args[2].getBytes();
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, port);

            // データグラムパケットを送信する
            ds.send(dp);
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
