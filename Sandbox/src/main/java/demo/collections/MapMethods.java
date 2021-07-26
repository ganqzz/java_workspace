package demo.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapMethods {

    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(2, "hoge");
        map.put(3, "fugafuga");
        map.put(5, "fefe");
        map.put(4, null);
        map.put(1, "awawa");
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();

        // Java8
        map.putIfAbsent(10, "New!");
        map.replace(3, "FugaFuga");
        map.replace(40, "FugaFuga"); // not UPSERT
        map.compute(20, (k, v) -> "Compute!");
        map.computeIfPresent(5, (k, v) -> v.toUpperCase());
        map.computeIfAbsent(30, k -> k + "New!");
        map.merge(1, "AwaWa", String::concat);
        map.merge(2, "PiYo", (k, v) -> v.toUpperCase());
        map.merge(4, "NonNull!", String::concat);
        map.merge(50, "NonUndefined!", String::concat);
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println(map.getOrDefault(100, "Default!"));
    }
}
