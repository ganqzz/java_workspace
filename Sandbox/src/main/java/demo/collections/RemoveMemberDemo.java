package demo.collections;

import java.util.ArrayList;
import java.util.List;

public class RemoveMemberDemo {

    public static void main(String[] args) {
        List<MyClass> sa = new ArrayList<>();
        MyClass e = new MyClass(1, "abc");
        sa.add(e);
        MyClass e2 = new MyClass(2, "abc");
        sa.add(e2);
        MyClass e3 = new MyClass(3, "abc");
        sa.add(e3);

        System.out.println(sa);

        sa.remove(e3); // equals()で比較される

        System.out.println(sa);
    }

    private static class MyClass {
        private int id;
        private String value;

        public MyClass(int id, String value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(((MyClass) obj).value);
        }

        @Override
        public String toString() {
            return String.format("(%d: %s)", id, value);
        }
    }
}
