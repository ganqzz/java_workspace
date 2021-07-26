package demo.generics;

import demo.generics.model.Person;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IntersectionTypeDemo {
    public static void main(String[] args) throws FileNotFoundException {
        IntersectionTypeDemo reader = new IntersectionTypeDemo();

        DataInputStream stream = new DataInputStream(
                new FileInputStream("src/main/resources/person"));
        Person person = reader.read(stream);
        System.out.println(person);

        RandomAccessFile randomAccess = new RandomAccessFile("src/main/resources/person", "rw");
        person = reader.read(randomAccess);
        System.out.println(person);
    }

    // intersection types
    public <T extends DataInput & Closeable> Person read(final T source) {
        try (T input = source) {
            return new Person(input.readUTF(), input.readInt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
