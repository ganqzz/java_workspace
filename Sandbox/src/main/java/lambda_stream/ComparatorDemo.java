package lambda_stream;

import lambda_stream.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ComparatorDemo {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Joe", 48));
        people.add(new Person("Mary", 30));
        people.add(new Person("Lynda", 25));
        people.add(new Person("あい", 15));
        people.add(new Person("Mike", 73));

        Comparator<Person> cmpAge = (p1, p2) -> p2.getAge() - p1.getAge();
        Comparator<Person> cmpName = (p1, p2) -> p1.getName().compareTo(p2.getName());

        // key extractor
        Function<org.paumard.stream.model.Person, Integer> f1 = p -> p.getAge();
        Function<org.paumard.stream.model.Person, String> f2 = p -> p.getName();

        // create Comparator from key extractor
        Comparator<Person> compareAge = Comparator.comparing(Person::getAge).reversed();
        Comparator<Person> comparator = Comparator.comparing(Person::getAge)
                                                  .thenComparing(Person::getName); // Chain

        people.stream().sorted(compareAge).forEach(System.out::println);

        Comparator<String> c1 = String::compareToIgnoreCase;
        Comparator<String> c2 = Comparator.nullsFirst(Comparator.naturalOrder());
    }
}
