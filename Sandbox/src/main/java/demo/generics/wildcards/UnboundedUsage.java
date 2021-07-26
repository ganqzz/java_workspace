package demo.generics.wildcards;

import demo.generics.model.Person;

import java.util.ArrayList;
import java.util.List;

public class UnboundedUsage {
    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.add(new Person("Don Draper", 89));
        objects.add(null);
        System.out.println(objects);

        List<? super Object> derivatives = new ArrayList<>();
        derivatives.add(new Object());
        derivatives.add(new Person("Don Draper", 89));
        derivatives.add(null);
        System.out.println(derivatives);

        // Unbounded
        // It's only safe to add null to a List<?>
        //List<? extends Object> wildcards = new ArrayList<>();
        List<?> wildcards = new ArrayList<>();
        //wildcards.add(new Object()); // NG
        //wildcards.add(new Person("Don Draper", 89)); //NG
        wildcards.add(null);
        System.out.println(wildcards);
    }
}
