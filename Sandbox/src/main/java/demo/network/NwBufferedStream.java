package demo.network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class NwBufferedStream {

    public static final String URL_STRING = "http://localhost/home/sandbox_php/explore_california/main.php?model=packages&format=json";

    public static void main(String[] args) {
        BufferedInputStream buf = null;
        try {
            URL url = new URL(URL_STRING);
            buf = new BufferedInputStream(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (true) {
                int data = buf.read();
                if (data == -1) break;
                sb.append((char) data);
            }
            System.out.println(sb);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // 404などもここに。
            e.printStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
