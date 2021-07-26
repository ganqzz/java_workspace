package etc.networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class URLDemo {
    public static void main(String args[]) {
        try {
            // URLを取得する
            URL url = new URL("https://google.com/");

            // 各要素の取得と表示
            System.out.println("URL: " + url);
            System.out.println("プロトコル: " + url.getProtocol());
            System.out.println("ホスト: " + url.getHost());
            System.out.println("ポート: " + url.getPort());
            System.out.println("ファイル: " + url.getFile());
            System.out.println("------------\n");

            // ファイルアクセス
            // 入力ストリームを取得する
            InputStream is = url.openStream();

            // URLからデータを読み取り、表示する
            byte buffer[] = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                System.out.write(buffer, 0, len);
            }
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
