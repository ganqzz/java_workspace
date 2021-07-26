package org.paumard.stream;

import lambda_stream.model.City;
import org.paumard.stream.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java8CollectionMethodsExample {

    public static void main(String[] args) {

        Person p1 = new Person("Alice", 23);
        Person p2 = new Person("Brian", 56);
        Person p3 = new Person("Chelsea", 46);
        Person p4 = new Person("David", 28);
        Person p5 = new Person("Erica", 37);
        Person p6 = new Person("Francisco", 18);

        City newYork = new City("New York");
        City shanghai = new City("Shanghai");
        City paris = new City("Paris");

        System.out.println("--- List ---");

        List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
        System.out.println(list);

        list.removeIf(person -> person.getAge() < 30);
        list.replaceAll(person -> new Person(person.getName().toUpperCase(), person.getAge()));
        list.sort(Comparator.comparing(Person::getAge).reversed());
        list.forEach(System.out::println);

        System.out.println("--- Map ---");

        Map<City, List<Person>> map = new HashMap<>();

        map.putIfAbsent(paris, new ArrayList<>());
        map.get(paris).add(p1);

        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p2);
        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p3);

        System.out.println(
                "People from Paris: " + map.getOrDefault(paris, Collections.EMPTY_LIST));
        System.out.println(
                "People from New York: " + map.getOrDefault(newYork, Collections.EMPTY_LIST));

        Map<City, List<Person>> map1 = new HashMap<>();
        map1.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p1);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p2);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p3);

        System.out.println("Map 1:");
        map1.forEach((city, people) -> System.out.println(city + " : " + people));

        Map<City, List<Person>> map2 = new HashMap<>();
        map2.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p4);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p5);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p6);

        System.out.println("Map 2:");
        map2.forEach((city, people) -> System.out.println(city + " : " + people));

        map2.forEach((city, people) -> {
                         map1.merge(city, people,
                                    (peopleFromMap1, peopleFromMap2) -> {
                                        peopleFromMap1.addAll(peopleFromMap2);
                                        return peopleFromMap1;
                                    });
                     }
        );

        System.out.println("Merged map1:");
        map1.forEach(
                (city, people) -> System.out.println(city + ": " + people)
        );
    }
}
