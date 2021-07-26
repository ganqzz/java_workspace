package org.paumard.stream;

import org.paumard.stream.model.PeopleGenerator;
import org.paumard.stream.model.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MergingMaps {

    public static void main(String... args) {
        List<Person> people = PeopleGenerator.getPeople();
        System.out.println(people);

        System.out.println("---");

        List<Person> list1 = people.subList(1, 10);
        List<Person> list2 = people.subList(10, people.size());

        Map<Integer, List<Person>> map1 = mapByAge(list1);
        System.out.println("Map1");
        map1.forEach((age, list) -> System.out.println(age + " => " + list));

        Map<Integer, List<Person>> map2 = mapByAge(list2);
        System.out.println("Map2");
        map2.forEach((age, list) -> System.out.println(age + " => " + list));

        map2.entrySet().forEach(
                entry -> map1.merge(entry.getKey(), entry.getValue(),
                                    (l1, l2) -> {
                                        l1.addAll(l2);
                                        return l1;
                                    }));
        System.out.println("Map1 merged");
        map1.forEach((age, list) -> System.out.println(age + " => " + list));
    }

    private static Map<Integer, List<Person>> mapByAge(List<Person> list) {
        Map<Integer, List<Person>> map = list.stream()
                                             .collect(Collectors.groupingBy(Person::getAge));
        return map;
    }
}
