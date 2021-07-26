package java8tips;

import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class StringJoinerDemo {

    public static void main(String[] args) {

        // Creating a StringJoiner
        StringJoiner sj = new StringJoiner(",", "{", "}");
        sj.setEmptyValue("No stooges yet");
        System.out.println(sj);

        sj.add("Moe").add("Larry").add("Curly");
        System.out.println(sj);

        // Merging StringJoiner instances
        StringJoiner sj2 = new StringJoiner(",");
        sj2.add("Shemp");

        sj.merge(sj2);
        System.out.println(sj);

        // Working with Collections
        Set<String> set = new TreeSet<>();
        set.add("California");
        set.add("Oregon");
        set.add("Washington");
        StringJoiner sj3 = new StringJoiner(" and ");
        set.forEach(sj3::add);

        // Tips
        StringJoiner sj4 = new StringJoiner("], [", "[", "]");
        System.out.println(sj4.add("hoge").add("fuga").add("piyo"));

        System.out.println(sj3);

        // String#join Demo
        String stooges = String.join(" and ", "Larry", "Curly", "Moe");
        System.out.println(stooges);

        String[] states = {"California", "Oregon", "Washington"};
        String statesList = String.join(",", states);
        System.out.println(statesList);
    }
}
