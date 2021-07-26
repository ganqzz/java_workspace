package etc.networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost());
            System.out.println(InetAddress.getByName("google.com"));

            InetAddress addresses[] = InetAddress.getAllByName("www.yahoo.co.jp");
            for (InetAddress address : addresses) {
                System.out.println(address.getHostName());
                System.out.println(address.getHostAddress());
                byte bytes[] = address.getAddress();
                for (int j = 0; j < bytes.length; j++) {
                    if (j > 0) {
                        System.out.print(".");
                    }
                    if (bytes[j] >= 0) {
                        System.out.print(bytes[j]);
                    } else {
                        System.out.print(bytes[j] + 256); // -128~127で保持されているため、256を足す。
                    }
                }
                System.out.println();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
