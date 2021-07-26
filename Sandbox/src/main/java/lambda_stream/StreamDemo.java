package lambda_stream;

import lambda_stream.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String args[]) {
        Stream.Builder<String> builder = Stream.builder();
        builder.add("one");
        builder.add("two");
        builder.add("three");
        Stream<String> stream1 = builder.build();
        stream1.forEach(System.out::println);

        System.out.println("---");

        // create from Array
        Person[] peopleArray = {
                new Person("Joe", 48),
                new Person("Mary", 30),
                new Person("Mike", 73)};

        Stream<Person> stream2 = Stream.of(peopleArray); // varargs
        Stream<Person> stream3 = Arrays.stream(peopleArray);

        stream3.forEach(System.out::println);

        System.out.println("---");

        // create CSV format
        String csv = Stream.of("hoge", "fuga", "piyo", "fefe\nhoy\"oyo", "awawa")
                              .map(s -> s.replace("\"", "\"\""))
                              .collect(Collectors.joining("\",\"", "\"", "\""));
        System.out.println(csv);

        System.out.println("---");

        // map doubles to ints
        Stream.of(1.5, 2.3, 3.7)
              .mapToInt(Double::intValue) // Double => int (not Integer)
              .forEach(System.out::println); // IntConsumer

        System.out.println("---");

        List<Person> people = new ArrayList<>();
        people.add(new Person("Joe", 48));
        people.add(new Person("Mary", 30));
        people.add(new Person("Lynda", 25));
        people.add(new Person("あい", 15));
        people.add(new Person("Mike", 73));

        Predicate<Person> pred = p -> p.getAge() > 30;

        // Sequential
        List<String> result = people.stream()
                                    //.peek(System.out::println)
                                    .filter(pred)
                                    .map(Person::getName)
                                    .collect(Collectors.toList());
        System.out.println("Selected: " + result);

        // Parallel
        List<String> resultP = people.stream()
                                     .parallel()
                                     .filter(pred)
                                     .map(Person::getName)
                                     .collect(Collectors.toList());
        System.out.println("Selected: " + resultP);
    }
}
