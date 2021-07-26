package basic;

public class Unary {

    public static void main(String[] args) {
        int i = 10;
        System.out.println(++i % 10); // i += 1; i % 10
        i = 10;
        System.out.println(i++ % 10); // i % 10; i += 1

        System.out.println("---");
        String[] sa = {"hoge", "fuga", "fefe"};

        int x = 0;
        System.out.println(sa[++x]);
        System.out.println(x);

        int y = 0;
        System.out.println(sa[y++]);
        System.out.println(y);
    }
}
