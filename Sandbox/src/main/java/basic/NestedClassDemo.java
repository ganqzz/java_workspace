package basic;

import java.util.ArrayList;
import java.util.List;

public class NestedClassDemo {
    public static void main(String[] args) throws Exception {
        Enclosing app = new Enclosing();
        app.execute();
    }
}

class Base {
    protected int count = 777;
}

class Enclosing extends Base {

    // static member enum
    private enum Switch {ON, OFF}

    // static member interface
    private interface Observer {
        void update();
    }

    // static member class
    private static class Observable {
        private List<Observer> observers = new ArrayList<>();

        void addObserver(Observer o) {
            observers.add(o);
        }

        int size() {
            return observers.size();
        }

        void notifyObservers() {
            observers.forEach(Observer::update);
        }
    }

    // member class
    private class ObserverMember implements Observer {
        private int count = 3;

        @Override
        public void update() {
            System.out.println("--- member class ---");
            System.out.println("this.count: " + this.count);
            System.out.println("Enclosing.this.count: " + Enclosing.this.count);
            System.out.println("Enclosing.super.count: " + Enclosing.super.count);
        }
    }

    private final Observable observable = new Observable();
    private int count = 99;

    Enclosing() {
        final int count = -10;

        Observer observer = new ObserverMember();
        observable.addObserver(observer);

        // anonymous class
        observable.addObserver(new Observer() {
            private int count = 5;

            @Override
            public void update() {
                System.out.println("--- anonymous class ---");
                System.out.println("count: " + count);
                System.out.println("this.count: " + this.count);
                System.out.println("Enclosing.this.count: " + Enclosing.this.count);
                System.out.println("Enclosing.super.count: " + Enclosing.super.count);
            }
        });

        // lambda
        observable.addObserver(() -> {
            System.out.println("--- lambda ---");
            System.out.println("count: " + count);
            System.out.println("this.count: " + this.count);
            System.out.println("Enclosing.this.count: " + Enclosing.this.count);
            System.out.println("Enclosing.super.count: " + Enclosing.super.count);
        });

        // local class
        class ObserverLocal implements Observer {
            private int count;

            @Override
            public void update() {
                System.out.println("--- local class ---");
                System.out.println("count: " + count);
                System.out.println("this.count: " + this.count);
                System.out.println("Enclosing.this.count: " + Enclosing.this.count);
                System.out.println("Enclosing.super.count: " + Enclosing.super.count);
            }
        }

        ObserverLocal observerLocal = new ObserverLocal();
        observerLocal.count = 7; // can access
        observable.addObserver(observerLocal);

        System.out.println("Total Observers = " + observable.size() + '\n');
    }

    void execute() throws Exception {
        observable.notifyObservers();
        count++;
        System.out.println("\n*** Enclosing.count++\n");
        observable.notifyObservers();
    }
}
