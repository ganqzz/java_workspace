package demo.generics.wildcards;

import demo.generics.model.Person;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class PersonSaver implements Closeable {
    private final RandomAccessFile file;

    public PersonSaver(final File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "rw");
    }

    public void save(Person person) throws IOException {
        file.writeUTF(person.getClass().getName());
        file.writeUTF(person.getName());
        file.writeInt(person.getAge());
    }

    // Upper bounded: covariance
    public void saveAll(final List<? extends Person> people) throws IOException {
        for (Person person : people) { // producer "get from"
            save(person);
        }
    }

    public void saveAll(final Person... people) throws IOException {
        for (Person person : people) {
            save(person);
        }
    }

    @Override
    public void close() throws IOException {
        file.close();
    }
}
