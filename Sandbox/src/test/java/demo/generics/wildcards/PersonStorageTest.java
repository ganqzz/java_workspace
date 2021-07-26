package demo.generics.wildcards;

import demo.generics.model.Employee;
import demo.generics.model.Partner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class PersonStorageTest {
    private Partner donDraper = new Partner("Don Draper", 89);
    private Partner bertCooper = new Partner("Bert Cooper", 100);
    private Employee peggyOlson = new Employee("Peggy Olson", 65);

    private File file;
    private PersonSaver saver;
    private PersonLoader loader;

    @Before
    public void setUp() throws Exception {
        file = File.createTempFile("temp", "people");
        file.deleteOnExit(); // Windowsはオープンファイルを削除できない
        saver = new PersonSaver(file);
        loader = new PersonLoader(file);
    }

    @After
    public void tearDown() throws IOException {
        saver.close();
        loader.close();
        file.delete();
    }

    @Test
    public void cannotLoadFromEmptyFile() throws Exception {
        assertNull(loader.load());
    }

    @Test
    public void savesAndLoadsPerson() throws Exception {
        saver.save(donDraper);

        assertEquals(donDraper, loader.load());
    }

    @Test
    public void savesAndLoadsTwoPeople() throws Exception {
        saver.save(donDraper);
        saver.save(peggyOlson);

        assertEquals(donDraper, loader.load());
        assertEquals(peggyOlson, loader.load());
    }

    @Test
    public void savesArraysOfPeople() throws Exception {
        /*Employee[] employees = new Employee[2];
        Person[] people = employees;*/
        Partner[] people = new Partner[2];
        people[0] = donDraper;
        people[1] = bertCooper;

        saver.saveAll(people);

        assertEquals(donDraper, loader.load());
        assertEquals(bertCooper, loader.load());
    }

    // upper bounds (extends) test
    @Test
    public void savesListsOfPeople() throws Exception {
        List<Partner> people = new ArrayList<>();
        people.add(donDraper);
        people.add(bertCooper);

        saver.saveAll(people);

        assertEquals(donDraper, loader.load());
        assertEquals(bertCooper, loader.load());
    }

    // lower bounds (super) test
    @Test
    public void loadsListsOfPeople() throws Exception {
        saver.save(donDraper);
        saver.save(bertCooper);

        List<Object> people = new ArrayList<>();
        loader.loadAll(people);

        assertEquals(asList(donDraper, bertCooper), people);
    }
}
