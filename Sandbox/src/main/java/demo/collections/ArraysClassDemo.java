package demo.collections;

import java.util.Arrays;
import java.util.List;

public class ArraysClassDemo {

    public static void main(String[] args) {

        int[] anArray = {2, 4, 6};
        System.out.println(Arrays.toString(anArray));
        System.out.println(anArray.toString()); // 参照の値が表示される。

        // create an array of strings
        String[] fruits = new String[]{"Kiwi", "Banana", "Pear", "Apple"};
        List<String> fruitsList = Arrays.asList(fruits);
        //fruitsList.remove(0); // NG: not resizable List created by Arrays.asList
        System.out.println(fruitsList); // toString()は自動で呼ばれる。

        // to check for existence in a "set"
        if (fruitsList.contains("apple")) {
            System.out.println("Yes Contains Apple!");
        }

        // copy
        String[] copyTo = Arrays.copyOfRange(fruits, 0, 2);
        System.out.println(Arrays.toString(copyTo));

        // sort
        int[] array = new int[3];
        Arrays.fill(array, 10);
        System.out.println(Arrays.toString(array));

        System.out.println(Arrays.toString(fruits));
        Arrays.sort(fruits);
        System.out.println(Arrays.toString(fruits));

        String[] numbers = new String[]{"7", "5", "4", "2", "6", "3", "1"};
        System.out.println("*** Selective Sort Array ***");
        System.out.println(Arrays.toString(numbers));
        Arrays.sort(numbers, 1, 6); // fromIndex <= elems < toIndex
        System.out.println(Arrays.toString(numbers));

        // search
        int searchValue = Arrays.binarySearch(numbers, "4");
        System.out.println("The index of the element 4 is : " + searchValue);
    }
}
