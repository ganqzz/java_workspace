package org.paumard.stream;

import org.paumard.stream.model.PeopleGenerator;
import org.paumard.stream.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class ReductionOperators {

    public static void main(String[] args) {

        List<Person> people = PeopleGenerator.getPeople();

        int maxOfAges =
                people.stream()
                      .map(Person::getAge)
                      .reduce(0, Integer::max); // identical value

        Optional<Integer> maxOfAgesOpt =
                people.stream()
                      .map(Person::getAge)
                      .reduce(Integer::max);

        OptionalInt maxOfAgesOptInt
                = people.stream()
                        .mapToInt(Person::getAge)
                        .max();

        List<Integer> ages =
                people.stream()
                      .reduce(
                              new ArrayList<Integer>(),
                              (list, p) -> {
                                  list.add(p.getAge());
                                  return list;
                              },
                              (list1, list2) -> {
                                  list1.addAll(list2);
                                  return list1;
                              }
                      );
    }
}
