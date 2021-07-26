package basic;

public class LocalScope {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String s = "hoge";
            System.out.println(s);
        }
    }

}
