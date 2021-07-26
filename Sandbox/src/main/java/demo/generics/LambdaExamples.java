package demo.generics;

import demo.generics.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class LambdaExamples {
    public static void main(String[] args) {
        final Person donDraper = new Person("Don Draper", 89);
        final Person peggyOlson = new Person("Peggy Olson", 75);
        final Person bertCooper = new Person("Bert Cooper", 100);

        Predicate<Person> isOld = person -> person.getAge() > 80;
        System.out.println(isOld.test(donDraper));
        System.out.println(isOld.test(peggyOlson));

        List<Person> people = new ArrayList<>();
        people.add(donDraper);
        people.add(peggyOlson);
        people.add(bertCooper);

        Map<Boolean, List<Person>> result = people
                .stream()
                .collect(partitioningBy(isOld));
        System.out.println(result);

        Map<Boolean, Long> result2 = people
                .stream()
                .collect(partitioningBy(isOld, counting()));
        System.out.println(result2);
    }
}
