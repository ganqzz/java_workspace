package basic;

public class Loops {
    public static void main(String[] args) {
        int count = 0;
        while (++count < 10) { // 1..9
            System.out.println(count);
        }

        System.out.println("---");

        count = 0;
        while (count++ < 10) { // 1..10
            System.out.println(count);
        }
    }
}
