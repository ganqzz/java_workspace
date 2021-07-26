package basic;

import java.util.Random;

public class CompareString {

    public static void main(String[] args) {
        //
        String s1 = "Hello";
        String s2 = "Hello"; // s1と同じ場所を指すことになる。
        if (s1 == s2) System.out.println("Equal");
        else System.out.println("Not Equal");

        System.out.println(s1.equals(s2));
        System.out.println();

        //
        String s3 = "Hel";
        s3 += "lo";
        if (s1 == s3) System.out.println("Equal");
        else System.out.println("Not Equal");

        System.out.println(s1.equals(s3));
        System.out.println();

        //
        String s4 = new String("Hello"); // 必ず別オブジェクトになる。
        if (s1 == s4) System.out.println("Equal");
        else System.out.println("Not Equal");

        System.out.println(s1.equals(s4));
        System.out.println();

    }

    public static class BreakLabel {

        public static void main(String... args) throws InterruptedException {
            FOR:
            for (; ; ) {
                Random generator = new Random();
                int num = generator.nextInt(41);
                System.out.println(num);
                switch (num) {
                    case 1:
                        break;
                    case 0:
                        break FOR;
                }
                Thread.sleep(50);
            }
        }
    }
}
