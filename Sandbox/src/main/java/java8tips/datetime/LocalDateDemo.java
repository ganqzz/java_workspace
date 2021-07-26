package java8tips.datetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalDateDemo {

    private static class Person {

        private String name;
        private LocalDate dateOfBirth;

        public Person(String name, LocalDate dateOfBirth) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
        }

        public String getName() {
            return name;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        @Override
        public String toString() {
            return "Person{" + "name=" + name + ", dateOfBirth=" + dateOfBirth + '}';
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                LocalDateDemo.class.getResourceAsStream("/people_birth.txt")));
             Stream<String> stream = reader.lines();) {

            stream.map(
                    line -> {
                        String[] s = line.split(" ");
                        String name = s[0].trim();
                        int year = Integer.parseInt(s[1]);
                        Month month = Month.of(Integer.parseInt(s[2]));
                        int day = Integer.parseInt(s[3]);
                        Person p = new Person(name, LocalDate.of(year, month, day));
                        persons.add(p); // side effect
                        return p;
                    }).forEach(System.out::println);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        LocalDate now = LocalDate.of(2014, Month.MARCH, 12);

        persons.forEach(
                p -> {
                    Period period = Period.between(p.getDateOfBirth(), now);
                    System.out.println(p.getName() + " was born " +
                                       //period.get(ChronoUnit.YEARS) + " years and " +
                                       period.getYears() + " years and " +
                                       //period.get(ChronoUnit.MONTHS) + " months " +
                                       period.getMonths() + " months " +
                                       "[" + p.getDateOfBirth().until(now, ChronoUnit.MONTHS) +
                                       " months]");
                });
    }
}
