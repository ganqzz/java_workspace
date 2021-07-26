package demo.generics.zoo;

public class Animal implements Comparable<Animal> {

    private String name;

    public Animal() {
        name = "Animal";
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s{name=%s}", getClass().getSimpleName(), name);
    }

    @Override
    public boolean equals(Object o1) {
        if (o1 == null) return false;
        if (!(o1 instanceof Animal)) return false;
        return name.equals(((Animal) o1).getName());
    }

    @Override
    public int compareTo(Animal o) {
        if (o == null) return 1;

        int ctVal = name.compareTo(o.getName());
        if (ctVal < 0) return -1;
        if (ctVal > 0) return 1;
        return ctVal;
    }
}
