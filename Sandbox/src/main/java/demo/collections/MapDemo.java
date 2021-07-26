package demo.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Key: immutable(hashCode/equals)
 */
public class MapDemo {

    public static void main(String[] args) {
        // HashMap
        System.out.println("--- HashMap");
        Map<String, String> map = new HashMap<>();
        init(map);
        print(map);

        // LinkedHashMap
        System.out.println("--- LinkedHashMap");
        Map<String, String> lmap = new LinkedHashMap<>();
        init(lmap);
        print(lmap);

        // SortedMap (TreeMap)
        // Key: Comparable
        System.out.println("--- SortedMap (TreeMap)");
        SortedMap<String, String> smap = new TreeMap<>();
        init(smap);
        print(smap);

        // SortedMap#headMap()
        System.out.println("--- SortedMap.headMap()");
        print(smap.headMap("3333")); // excluded

        // SortedMap#tailMap()
        System.out.println("--- SortedMap.tailMap()");
        print(smap.tailMap("3333")); // included
    }

    private static void init(Map<String, String> map) {
        map.put("5555", "pqr");
        map.put("2222", "ghi");
        map.put("3333", "abc");
        map.put("6666", "xyz");
        map.put("1111", "def");
        map.put("4444", "mno");
    }

    private static void print(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }
    }
}
