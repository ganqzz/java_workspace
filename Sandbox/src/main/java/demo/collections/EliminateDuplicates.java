package demo.collections;

import java.util.ArrayList;

public class EliminateDuplicates {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("red");
        list.add("green");
        list.add("blue");
        list.add("red");
        list.add("green");
        list.add("yellow");
        list.add("orange");
        list.add("blue");
        ArrayList<String> nodups = removeDups(list);
        System.out.println(list);
        System.out.println(nodups);
    }

    public static <E extends Comparable<E>> ArrayList<E> removeDups(ArrayList<E> list) {
        if (list.size() == 0) return list;

        ArrayList<E> newList = new ArrayList<>();
        newList.add(list.get(0));
        boolean found;
        for (E e : list) {
            found = false;
            for (E ne : newList) {
                if (e.compareTo(ne) == 0) {
                    found = true;
                    break;
                }
            }

            if (!found) newList.add(e);
        }

        return newList;
    }
}
