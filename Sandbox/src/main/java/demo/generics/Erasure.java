package demo.generics;

/*
  Erasure demo
  javap -s -cp build/classes/.../Erasure
  javap -s -c -cp build/classes/.../Erasure
 */

import java.util.List;

public class Erasure<T, B extends Comparable<B>> {
    public void unbounded(T param) {
    }

    public void lists(List<String> param, List<List<T>> param2) {
        String s = param.get(0);
    }

    public void bounded(B param) {
    }
}
