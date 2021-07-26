package basic;

public class AnonymousClass {
    public static void main(String[] args) {
        final String s = "hoge"; // final である必要がある。
        new Object() {
            void hoge() {
                System.out.println(s);
            }
        }.hoge();

        new Runnable() {
            @Override
            public void run() {
                System.out.println("run!" + s);
            }
        }.run();
    }
}
