package basic;

public class Scope {
    public static void main(String[] args) {
        int i = 5;
        int j = 2;
        {
            System.out.println(i);
            System.out.println(j);
            i = 10;
            //int j = 3; // Cとは違ってNG
        }
        System.out.println("---");
        System.out.println(i);
        System.out.println(j);
    }
}
