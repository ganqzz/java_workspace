package basic;

public class InitializersDemo {
    private static String fefe;
    public static int count;

    private String awawa;

    static {
        fefe = "fefe";
        fuga();
        System.out.println("static init");
    }

    {
        count++;
        awawa = "awawa";
        System.out.println("{...}");
    }

    {
        System.out.println("{...} 2");
        awawa = "awawa2";
    }

    public InitializersDemo() {
        System.out.println("Constructor()");
    }

    public InitializersDemo(int i) {
        System.out.println("Constructor(int i)");
    }

    private static void fuga() {
        System.out.println(fefe);
    }

    public static void main(String[] args) {
        System.out.println("main(): executed");
        System.out.println("");
        InitializersDemo a = new InitializersDemo();
        System.out.println(InitializersDemo.count);
        System.out.println("");
        InitializersDemo b = new InitializersDemo(5);
        System.out.println(InitializersDemo.count);
    }
}
