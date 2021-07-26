package demo.io.model;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person4 implements Serializable {

    private String name;
    private int age;
    private transient Address address; // Transient

    public Person4() {
    }

    public Person4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person4{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", address=" + address +
               '}';
    }

    private Object writeReplace() throws ObjectStreamException {
        return new PersonProxy(name + "::" + age);
    }
}
