package demo.io;

import demo.io.model.Person;
import demo.io.model.Person2;
import demo.io.model.Person3;
import demo.io.model.Person4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ObjectInputStream / ObjectOutputStream
 * Overriding Serialization
 */
public class SerializationRW {

    public static void main(String[] args) {
        autoSerialize();
        customSerialize();
        byExternalizable();
        byProxy();
    }

    private static void autoSerialize() {
        Path path = Paths.get("temp/person.bin");

        Person p1 = new Person("Linda", 22);
        Person p2 = new Person("David", 35);

        writeObjects(path, p1, p2);

        readObjects(path, 2);
    }

    private static void customSerialize() {
        Path path = Paths.get("temp/person2.bin");

        Person2 p1 = new Person2("Linda", 22);
        Person2 p2 = new Person2("David", 35);

        writeObjects(path, p1, p2);

        readObjects(path, 2);
    }

    private static void byExternalizable() {
        Path path = Paths.get("temp/person3.bin");

        Person3 p1 = new Person3("Linda", 22);
        Person3 p2 = new Person3("David", 35);

        writeObjects(path, p1, p2);

        readObjects(path, 2);
    }

    private static void byProxy() {
        Path path = Paths.get("temp/person4.bin");

        Person4 p1 = new Person4("Linda", 22);
        Person4 p2 = new Person4("David", 35);

        writeObjects(path, p1, p2);

        readObjects(path, 2);
    }

    private static void writeObjects(Path path, Object... objects) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));) {
            for (Object o : objects) {
                out.writeObject(o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObjects(Path path, int count) {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));) {
            for (int i = 0; i < count; i++) {
                System.out.println(in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
