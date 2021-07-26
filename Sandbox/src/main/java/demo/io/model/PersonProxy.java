package demo.io.model;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class PersonProxy implements Serializable {

    private String name;

    public PersonProxy() {
    }

    public PersonProxy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonProxy{" +
               "name='" + name + '\'' +
               '}';
    }

    private Object readResolve() throws ObjectStreamException {
        String[] strings = this.name.split("::");
        String name = strings[0];
        int age = Integer.parseInt(strings[1]);
        return new Person4(name, age);
    }
}
