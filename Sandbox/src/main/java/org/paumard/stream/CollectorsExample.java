package org.paumard.stream;

import org.paumard.stream.model.PeopleGenerator;
import org.paumard.stream.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String... args) {
        List<Person> people = PeopleGenerator.getPeople();
        System.out.println(people);

        System.out.println("---");

        Optional<Person> opt = people.stream()
                                     .filter(p -> p.getAge() >= 20)
                                     .min(Comparator.comparing(Person::getAge));
        System.out.println(opt);
        System.out.println();

        Optional<Person> opt2 = people.stream().max(Comparator.comparing(Person::getAge));
        System.out.println(opt2);
        System.out.println();

        Map<Integer, String> map =
                people.stream()
                      .collect(Collectors.groupingBy(
                              Person::getAge,
                              Collectors.mapping(Person::getName, Collectors.joining(", "))));
        System.out.println(map);

        Map<Integer, Set<String>> map2 =
                people.stream()
                      .collect(Collectors.groupingBy(
                              Person::getAge,
                              Collectors.mapping(
                                      Person::getName,
                                      Collectors.toCollection(TreeSet::new)))); // Alphabet
        System.out.println(map2);

        Map<Integer, List<String>> map3 =
                people.stream()
                      .collect(Collectors.groupingBy(
                              Person::getAge,
                              Collectors.mapping(Person::getName, Collectors.toList()))
                      );
        System.out.println(map3);
        System.out.println(map3.get(18) instanceof ArrayList<?>);
    }
}
