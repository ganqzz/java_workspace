package demo.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsClassDemo {

    public static void main(String[] args) {
        String[] s = {"mat", "hat", "cat"};

        List<String> myList = Arrays.asList(s);

        Collections.sort(myList);
        System.out.println("sorted: " + myList);

        Collections.reverse(myList);
        System.out.println("reversed: " + myList);

        Collections.shuffle(myList);
        System.out.println("shuffle: " + myList);

        String[] copy = new String[3];
        List<String> listCopy = Arrays.asList(copy);
        Collections.copy(listCopy, myList);
        System.out.println("copy: " + listCopy);

        Collections.fill(myList, "vtc");
        System.out.println("fill: " + myList);

        List<Integer> list1 = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        System.out.println("position of 21: " + Collections.binarySearch(list1, 21));
    }

}
