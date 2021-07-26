package demo.generics.zoo;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>(10);
        animals.add(new Animal("Chipmunk"));
        animals.add(new Animal("Aardvark"));
        animals.add(new Animal("Hedgehog"));
        animals.add(new Animal("Dog"));
        animals.add(new Animal("Ibex"));
        animals.add(new Animal("Frog"));
        animals.add(new Animal("Giraffe"));
        animals.add(new Animal("Eagle"));
        animals.add(new Animal("Jaguar"));
        animals.add(new Animal("Bear"));

        BestDataContainer<Animal> bdc0 = new BestDataContainer<>(animals);
        System.out.println(bdc0);

        System.out.println("*********************************");

        // Iterable
        BestDataContainer2<Animal> bdc1 = new BestDataContainer2<>(animals);

        for (Animal a : bdc1) {
            System.out.println(a);
        }

        // Java8 Stream
        //bdc1.forEach(System.out::println);

        System.out.println();
        System.out.println("*********************************");

        // Comparable
        System.out.println("MAX: " + bdc1.getMax());
        System.out.println("MIN: " + bdc1.getMin());
    }
}
