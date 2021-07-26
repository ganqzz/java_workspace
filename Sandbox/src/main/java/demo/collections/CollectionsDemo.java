package demo.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {

    public static void main(String[] args) {
        // ArrayList
        List<String> stra = new ArrayList<>();
        stra.add("a");
        stra.add("b");
        System.out.println(stra);

        ListIterator<String> i = stra.listIterator();
        while (i.hasNext())
            System.out.println(i.next());

        for (ListIterator<String> j = stra.listIterator(); j.hasNext(); )
            System.out.println(j.next());

        for (String s : stra)
            System.out.println(s);

        System.out.println("---");

        // HashSet
        Set<Integer> mySet = new HashSet<>();
        mySet.add(4);
        mySet.add(2);
        mySet.add(4); // 上書き
        System.out.println(mySet + ": " + mySet.size());

        // example of using Set to eliminate dups and sort automatically
        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 3, 3, 2, 1, 1, 1));
        System.out.println(numbers);

        System.out.println("---");

        // HashMap
        Map<String, String> map = new HashMap<>();
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        System.out.println(map);

        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ":" + map.get(key));
        }

        for (String key : keys) {
            System.out.println(key + ":" + map.get(key));
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
