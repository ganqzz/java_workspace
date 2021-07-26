package org.paumard.stream;

import org.paumard.stream.model.PeopleGenerator;
import org.paumard.stream.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildingBiMap {

    public static void main(String... args) {
        List<Person> people = PeopleGenerator.getPeople();
        System.out.println(people);

        System.out.println("---");

        Map<Integer, List<Person>> map = people.stream()
                                               .collect(Collectors.groupingBy(Person::getAge));
        map.forEach((age, list) -> System.out.println(age + " => " + list));

        Map<Integer, Map<String, List<Person>>> bimap = new HashMap<>();

        people.forEach(
                person -> bimap
                        .computeIfAbsent(person.getAge(), HashMap::new)
                        .merge(person.getGender(), new ArrayList<>(Arrays.asList(person)),
                               (l1, l2) -> { // 既にListがある場合は、追加する。
                                   l1.addAll(l2);
                                   return l1;
                               })
        );
        System.out.println("BiMap");
        bimap.forEach((age, m) -> System.out.println(age + " => " + m));
    }
}
