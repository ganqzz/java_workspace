package demo.datatypes;

import java.util.ArrayList;
import java.util.List;

public class AutoBoxingDemo {

    public static void main(String[] args) {
        // Autoboxing Examples
        List<Integer> listIntegers = new ArrayList<>();
        for (int i = 1; i < 10; i += 2) {
            listIntegers.add(i);
        }

        Integer i2 = Integer.valueOf(5);
        System.out.println(i2 == 5);

        // Unboxing through assignment
        int firstInt = listIntegers.get(0);
        System.out.println("first Integer in List = " + firstInt);

        // Integer ++
        Integer x = 10;
        x++;
        System.out.println(x);

        System.out.println("---");
        Integer a = new Integer(10000);
        Integer b = new Integer(10000);
        Integer c = 10000;
        Integer d = Integer.valueOf(10000);
        Integer e = 10000;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(c == d);
        System.out.println(c == e);
        System.out.println(c == 10000);
    }

    public static int sumEven(List<Integer> listIntegers) {
        int sum = 0;
        for (Integer i : listIntegers) {
            if (i % 2 == 0) sum += i.intValue();
        }
        return sum;
    }
}
