package demo.generics.wildcards;

import demo.generics.model.Person;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PersonLoader implements Closeable {
    private final RandomAccessFile file;

    public PersonLoader(final File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "rw");
    }

    public Person load() throws ClassNotFoundException {
        try {
            final String className = file.readUTF();
            final String personName = file.readUTF();
            final int age = file.readInt();

            // Unbounded wildcard
            final Class<?> personClass = Class.forName(className);
            final Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
            return (Person) constructor.newInstance(personName, age);
        } catch (IOException e) {
            return null;
        } catch (NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lower bounded: contravariance
    public void loadAll(final List<? super Person> people) throws ClassNotFoundException {
        Person person;

        while ((person = load()) != null) {
            people.add(person); // consumer "put into"
        }
    }

    public void close() throws IOException {
        file.close();
    }
}
