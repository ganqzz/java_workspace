package lambda_stream.design_pattern.validator;

import lambda_stream.model.Person;

public class DemoMain {
    public static void main(String[] args) {
        Person sarah = new Person("Sarah", 29);
        Person james = new Person(null, 29);
        Person mary = new Person("Mary", -10);
        Person john = new Person("John", 1_000);
        Person linda = new Person(null, 1_000);

        sarah = Validator.validate(p -> p.getName() != null, "The name should not be null")
                         .thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
                         .thenValidate(p -> p.getAge() < 200, "The age should be lesser than 200")
                         .on(sarah)
                         .validate();
        System.out.println("Sarah: " + sarah);

        james = Validator.validate(p -> p.getName() != null, "The name should not be null")
                         .thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
                         .thenValidate(p -> p.getAge() < 200, "The age should be lesser than 200")
                         .on(james)
                         .validate();
        System.out.println("James: " + james);

        //mary = Validator.validate(p -> p.getName() != null, "The name should not be null")
        //                .thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
        //                .thenValidate(p -> p.getAge() < 200, "The age should be lesser than 200")
        //                .on(mary)
        //                .validate();
        //System.out.println("Mary: " + mary);

        //john = Validator.validate(p -> p.getName() != null, "The name should not be null")
        //                .thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
        //                .thenValidate(p -> p.getAge() < 200, "The age should be lesser than 200")
        //                .on(john)
        //                .validate();
        //System.out.println("John: " + john);

        linda = Validator.validate(p -> p.getName() != null, "The name should not be null")
                        .thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
                        .thenValidate(p -> p.getAge() < 200, "The age should be lesser than 200")
                        .on(linda)
                        .validate();
        System.out.println("Linda: " + linda);
    }
}
