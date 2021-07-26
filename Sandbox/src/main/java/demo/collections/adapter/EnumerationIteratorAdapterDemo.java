package demo.collections.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class EnumerationIteratorAdapterDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hoge", "fuga", "fefe", "awawa");

        ArrayList<String> l = new ArrayList<>(strings);
        Enumeration<?> enumeration = new IteratorEnumeration(l.iterator());
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        Vector<String> v = new Vector<>(strings);
        Iterator<?> iterator = new EnumerationIterator(v.elements());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
