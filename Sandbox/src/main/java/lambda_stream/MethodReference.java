package lambda_stream;

import lambda_stream.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MethodReference {

    public static void main(String args[]) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Joe", 48));
        people.add(new Person("Mary", 30));
        people.add(new Person("Mike", 73));

        people.sort(Person::compareByAges); // Static method reference

        people.sort((p1, p2) -> Person.compareByAges(p1, p2)); // Lambda

        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                Integer age1 = p1.getAge();
                return age1.compareTo(p2.getAge());
            }
        }); // anonymous class

        people.forEach(System.out::println);

        MethodReference mainClass = new MethodReference();
        mainClass.sortData(people);
    }

    public void sortData(List<Person> people) {
        // disruptive
        people.sort(this::compareAges); // Instance method reference
        people.forEach(System.out::println);
    }

    // Comparatorとして使うためのメソッド
    public int compareAges(Person p1, Person p2) {
        Integer age1 = p1.getAge();
        return age1.compareTo(p2.getAge());
    }
}
