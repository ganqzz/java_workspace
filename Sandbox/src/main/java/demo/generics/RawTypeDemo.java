package demo.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RawTypeDemo {
    public static void main(String[] args) {
        List<Object> values = new ArrayList<>();
        values.add("abc");
        values.add(new Object());
        values.add(1);

        // raw Iterator
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println(element);
        }

        List rawtype = values;
        //List<String> strings = values; // NG
        List<String> strings = rawtype;

        //for (String element : strings) { // ClassCastException
        //    System.out.println(element);
        //}

        Iterator<String> iterator2 = strings.iterator();
        while (iterator2.hasNext()) {
            //System.out.println(iterator2.next()); // ClassCastException
            Object element = iterator2.next();
            System.out.println(element);
        }
    }
}
