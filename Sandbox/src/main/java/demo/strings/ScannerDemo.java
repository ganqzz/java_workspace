package demo.strings;

import java.util.Scanner;

public class ScannerDemo {

    public static void main(String[] args) {
        demo("fefe\nawaw\nhoge\nfuga\n");
        demoDelimiter("The,quick,red,fox,jumped,over,the,lazy,brown,dog.");
    }

    private static void demoSystemIn() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String str = sc.nextLine();
        System.out.println(str);
        //sc.close(); // System.inの場合は閉じてはいけない（再利用できなくなる）
    }

    private static void demo(String str) {
        Scanner sc = new Scanner(str);
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter a String: ");
            System.out.println(sc.nextLine());
        }
        sc.close();
    }

    private static void demoDelimiter(String str) {
        Scanner sc = new Scanner(str);
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
        sc.close();
    }
}
