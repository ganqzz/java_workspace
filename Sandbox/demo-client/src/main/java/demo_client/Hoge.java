package demo_client;

import com.example.lib.OpenApi;

public class Hoge {
    public static void main(String[] args) {
        OpenApi.Factory factory = new OpenApi.Factory();
        var hoge = factory.create();
        System.out.println(hoge);
        //hoge.hoge(); // compile error
    }
}
