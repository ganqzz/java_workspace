package demo.io.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person2 implements Serializable {

    private static final long serialVersionUID = -3785675544900507343L;

    private String name;
    private int age;
    private transient Address address; // Transient

    public Person2() {
    }

    public Person2(String name, int age) {
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
        return "Person2{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", address=" + address +
               '}';
    }

    // Custom reader: must be exactly the same
    private void writeObject(ObjectOutputStream oos) throws Exception {
        DataOutputStream dos = new DataOutputStream(oos);
        dos.writeUTF(name + "::" + age);
    }

    // Custom writer: must be exactly the same
    private void readObject(ObjectInputStream ois) throws Exception {
        DataInputStream dis = new DataInputStream(ois);
        String content = dis.readUTF();
        String[] strings = content.split("::");
        name = strings[0];
        age = Integer.parseInt(strings[1]);
    }
}
