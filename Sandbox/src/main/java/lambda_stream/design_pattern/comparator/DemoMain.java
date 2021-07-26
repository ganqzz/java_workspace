package lambda_stream.design_pattern.comparator;

import lambda_stream.model.Person;

import java.util.function.Function;

/**
 * scratched Comparator demo
 * using chaining and composition
 */
public class DemoMain {
    public static void main(String[] args) {
        Person mary = new Person("Mary", 28);
        Person john = new Person("John", 22);
        Person linda = new Person("Linda", 26);
        Person james = new Person("James", 32);
        Person jamesBis = new Person("James", 26);

        Function<Person, String> getName = Person::getName; // key extractor
        Function<Person, Integer> getAge = Person::getAge;

        Comparator<Person> cmpName = Comparator.comparing(getName);
        Comparator<Person> cmpAge = Comparator.comparing(getAge);

        Comparator<Person> cmpNameReversed = cmpName.reversed();

        Comparator<Person> cmp = cmpName.thenComparing(cmpAge);

        // more readable
        Comparator<Person> cmp2 = Comparator.comparing(Person::getName)
                                            .thenComparing(Person::getAge);

        System.out.println("Mary > John : " + (cmpName.compare(mary, john) > 0));
        System.out.println("John > James : " + (cmpName.compare(john, james) > 0));
        System.out.println("Linda > John : " + (cmpName.compare(linda, john) > 0));

        System.out.println("Mary > John : " + (cmpNameReversed.compare(mary, john) > 0));
        System.out.println("John > James : " + (cmpNameReversed.compare(john, james) > 0));
        System.out.println("Linda > John : " + (cmpNameReversed.compare(linda, john) > 0));

        System.out.println("Mary > John : " + (cmp.compare(mary, john) > 0));
        System.out.println("John > James : " + (cmp.compare(john, james) > 0));
        System.out.println("Linda > John : " + (cmp.compare(linda, john) > 0));
        System.out.println("James > James Bis : " + (cmp.compare(james, jamesBis) > 0));

        System.out.println("Mary > John : " + (cmp2.compare(mary, john) > 0));
        System.out.println("John > James : " + (cmp2.compare(john, james) > 0));
        System.out.println("Linda > John : " + (cmp2.compare(linda, john) > 0));
        System.out.println("James > James Bis : " + (cmp2.compare(james, jamesBis) > 0));
    }
}
