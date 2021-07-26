package demo.generics;

import java.util.List;

public class Drawbacks<T> {

    public void print(String param) {}

    public void print(Integer param) {}

    public void print(List<String> param) {}

    //public void print(List<Integer> param) {}

    @Override
    public boolean equals(Object o) {
        // Banned
        if (o instanceof Drawbacks/*<T>*/) {
            return true;
        }

        return false;
    }
}
