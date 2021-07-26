package demo.generics.classes_and_interfaces;

import demo.generics.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingExamples {
    public static void main(String[] args) {
        Person donDraper = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);
        Person bertCooper = new Person("Bert Cooper", 100);

        List<Person> madMen = new ArrayList<>();
        madMen.add(donDraper);
        madMen.add(peggyOlson);
        madMen.add(bertCooper);

        AgeComparator ageComparator = new AgeComparator();

        //Collections.sort(madMen, ageComparator);
        madMen.sort(ageComparator);
        System.out.println(madMen);

        //Collections.sort(madMen, new Reverser<>(ageComparator));
        madMen.sort(new Reverser<>(ageComparator));
        System.out.println(madMen);

        // Generic method demo
        final Person youngestCastMember = min(madMen, ageComparator);
        System.out.println(youngestCastMember);

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        System.out.println(min(numbers, Integer::compare));
    }

    // not Type-safe
    //private static Object min(List values, Comparator comparator) {
    //    if (values.isEmpty()) throw new IllegalArgumentException();
    //
    //    Object lowestElement = values.get(0);
    //    for (int i = 1; i < values.size(); i++) {
    //        final Object element = values.get(i);
    //        if (comparator.compare(element, lowestElement) < 0) {
    //            lowestElement = element;
    //        }
    //    }
    //    return lowestElement;
    //}

    // Type-safe
    private static <T> T min(List<T> values, Comparator<T> comparator) {
        if (values.isEmpty()) throw new IllegalArgumentException();

        T lowestElement = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            final T element = values.get(i);
            if (comparator.compare(element, lowestElement) < 0) {
                lowestElement = element;
            }
        }
        return lowestElement;
    }
}
