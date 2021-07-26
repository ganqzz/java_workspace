package demo.enums;

import java.util.EnumSet;

public class DemoEnums {

    private enum Car {
        Acura("Legend", "2013"),
        Honda("Accord", "2013"),
        Toyota("Camry", "2013"),
        Ford("Taurus", "2013"),
        Nissan("Maxima", "2013");

        Car(String model, String year) {
            this.model = model;
            this.year = year;
        }

        private final String model;
        private final String year;

        public String getModel() {
            return model;
        }

        public String getYear() {
            return year;
        }
    }

    public static void main(String[] args) {
        System.out.println("All Cars:\n");
        // print all cars in Enum Car
        for (Car car : Car.values()) {
            System.out.printf("%-10s%-45s%s\n", car, car.getModel(), car.getYear());
        }

        System.out.println("\nDisplay a range of enum constants Cars:\n");
        // print first 3 cars
        for (Car car : EnumSet.range(Car.Acura, Car.Toyota)) {
            System.out.printf("%-10s%-45s%s\n", car, car.getModel(), car.getYear());
        }
    }
}
