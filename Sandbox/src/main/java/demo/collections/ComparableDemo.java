package demo.collections;

public class ComparableDemo {

    public static void main(String[] args) {
        System.out.println("Hoge".compareTo("fugarherhe"));
        System.out.println("A".compareTo("a"));
        System.out.println("a".compareTo("b"));
        System.out.println("a".compareTo("a"));
        System.out.println(Integer.valueOf(1).compareTo(Integer.valueOf(20)));
        System.out.println(Float.valueOf(0.5f).compareTo(Float.valueOf(10.5f)));
    }
}
