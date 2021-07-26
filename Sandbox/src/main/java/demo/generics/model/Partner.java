package demo.generics.model;

public class Partner extends Person {
    public Partner(final String name, final int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Partner{" +
               "name='" + getName() + '\'' +
               ", age=" + getAge() +
               '}';
    }
}
