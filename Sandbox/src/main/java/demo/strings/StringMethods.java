package demo.strings;

public class StringMethods {

    public static void main(String[] args) {
        System.out.println("abcd".toUpperCase());
        System.out.println("ａｂｃｄ".toUpperCase());

        System.out.println("/".split("/", -1).length); // 2
        System.out.println("/".split("/").length); // 0
        System.out.println("".split("/", -1).length); // 1
        System.out.println("".split("/").length); // 1
    }
}
