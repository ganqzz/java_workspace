package lambda_stream;

import lambda_stream.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectingMap {

    public static void main(String args[]) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Joe", 48));
        people.add(new Person("Mary", 30));
        people.add(new Person("Lynda", 25));
        people.add(new Person("あい", 25));
        people.add(new Person("Mike", 73));

        Map<Integer, List<Person>> map = people.stream()
                                               .filter(p -> p.getAge() > 20)
                                               .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map);

        // SELECT age, count(*) FROM people where age > 20 GROUP BY age
        Map<Integer, Long> map2 = people.stream()
                                        .filter(p -> p.getAge() > 20)
                                        .collect(Collectors.groupingBy(Person::getAge,
                                                                       Collectors.counting()));
        System.out.println(map2);
    }
}
