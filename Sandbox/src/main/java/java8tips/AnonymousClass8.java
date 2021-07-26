package java8tips;

public class AnonymousClass8 {
    public static void main(String[] args) {
        String s = "hoge"; // finalと見なされる。
        new Object() {
            void hoge() {
                System.out.println(s);
            }
        }.hoge();

        // lambda
        new Thread(() -> System.out.println(s)).start();

        //s = "fuga"; // なので、これがエラーとなる。
    }
}
