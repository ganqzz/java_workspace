package demo.io.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person3 implements Externalizable {

    private static final long serialVersionUID = 5990851521782183681L;

    private String name;
    private int age;
    private transient Address address; // Transient

    public Person3() {
    }

    public Person3(String name, int age) {
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
        return "Person3{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", address=" + address +
               '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String pk = name + "::" + age;
        out.write(pk.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024];
        int read = in.read(buffer);
        String content = new String(buffer, 0, read);
        String[] strings = content.split("::");
        name = strings[0];
        age = Integer.parseInt(strings[1]);
    }
}
