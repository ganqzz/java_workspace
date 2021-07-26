package demo.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundedDemo {

    public static void main(String[] args) {
        // upper bounded
        List<Integer> li = Arrays.asList(1, 5, 9);
        System.out.println("sum = " + add(li));

        List<Double> ld = Arrays.asList(5.2, 5.3, 4.5);
        System.out.println("sum = " + add(ld));

        printOnlyIntegersOrSuperClasses(new ArrayList<Integer>());
        printOnlyIntegersOrSuperClasses(new ArrayList<Number>());
//		printOnlyIntegersOrSuperClasses(new ArrayList<Double>()); // invalid
    }

    public static double add(List<? extends Number> list) {
        double s = 0.0;
        for (Number n : list) {
            s += n.doubleValue();
        }
        return s;
    }

    public static void printOnlyIntegersOrSuperClasses(List<? super Integer> list) {
        System.out.println(list);
    }
}
