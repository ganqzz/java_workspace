package demo.reflection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        System.out.println(resToPathStr("../../hoge/fuga/hoge.txt"));
        System.out.println(resToPathStr("/hoge/fuga/hoge.txt"));
        System.out.println(resToPathStr("notfound.txt"));

        BufferedReader in = new BufferedReader(new InputStreamReader(
                ResourceDemo.class.getResourceAsStream("/hoge/fuga/hoge.txt")));

        int c;
        while ((c = in.read()) >= 0) {
            System.out.print((char) c);
        }

        in.close();
    }

    public static String resToPathStr(String res) {
        URL url = ResourceDemo.class.getResource(res);
        if (url == null) {
            System.err.println("Couldn't find file: " + res);
            return null;
        }
        return url.getPath();
    }
}
