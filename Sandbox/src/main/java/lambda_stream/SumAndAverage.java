package lambda_stream;

import lambda_stream.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

public class SumAndAverage {

    public static void main(String... args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Joe", 48));
        people.add(new Person("Mary", 30));
        people.add(new Person("Mike", 73));

        int sum = people.stream()
                        .mapToInt(Person::getAge)
                        .sum();
        System.out.println("Total of ages: " + sum);

        people = Collections.emptyList();
        OptionalDouble avg = people.stream()
                                   .mapToInt(Person::getAge)
                                   .average(); // 0個の時に、0/0になるため、Optionalを使う。

        // isPresent() + get()
        avg.ifPresent(d -> System.out.println("Average: " + d));

        if (avg.isPresent()) {
            System.out.println("Average: " + avg.getAsDouble());
        } else {
            System.out.println("Average not calculated");
        }
    }
}
