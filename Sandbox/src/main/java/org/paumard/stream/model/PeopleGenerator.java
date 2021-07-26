package org.paumard.stream.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PeopleGenerator {

    public static List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(PeopleGenerator.class.getResource("/people.txt").toURI()));
             Stream<String> stream = reader.lines()) {

            stream.map(line -> {
                String[] s = line.split(" ");
                return new Person(s[0].trim(), Integer.parseInt(s[1]), s[2].trim());
            }).forEach(people::add);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return people;
    }

    // check
    public static void main(String... args) {
        getPeople().forEach(System.out::println);
    }
}
