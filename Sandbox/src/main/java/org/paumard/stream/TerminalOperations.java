package org.paumard.stream;

import org.paumard.stream.model.PeopleGenerator;
import org.paumard.stream.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TerminalOperations {

    public static void main(String[] args) {

        List<Person> people = PeopleGenerator.getPeople();

        Predicate<Person> over20 = person -> person.getAge() > 20;

        System.out.println("--- anyMatch, allMatch, noneMatch, count ---");
        System.out.println(people.stream()
                                 .anyMatch(over20));
        System.out.println(people.stream()
                                 .allMatch(over20));
        System.out.println(people.stream()
                                 .noneMatch(over20));
        System.out.println(people.stream()
                                 .filter(over20)
                                 .count());

        System.out.println("--- findFirst ---");
        people.stream()
              .filter(over20)
              .findFirst()
              .ifPresent(System.out::println);
        System.out.println("--- findAny ---");
        people.stream()
              .filter(over20)
              .findAny()
              .ifPresent(System.out::println);

        System.out.println("--- findFirst by parallel ---");
        people.stream()
              .parallel()
              .filter(over20)
              .findFirst()
              .ifPresent(System.out::println);
        System.out.println("--- findAny by parallel ---");
        people.stream()
              .parallel()
              .filter(over20)
              .findAny()
              .ifPresent(System.out::println);

        System.out.println("--- max ---");
        people.stream()
              //.max(Comparator.comparing(Person::getAge))
              .max(Comparator.comparingInt(Person::getAge))
              .ifPresent(System.out::println);
    }
}
