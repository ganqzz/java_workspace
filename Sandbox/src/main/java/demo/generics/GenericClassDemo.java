package demo.generics;

public class GenericClassDemo {

    public interface Pair<K, V> {
        K getKey();

        V getValue();
    }

    public static class OrderedPair<K, V> implements Pair<K, V> {
        private final K key;
        private final V value;

        public OrderedPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "OrderedPair{" +
                   "key=" + key +
                   ", value=" + value +
                   '}';
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new OrderedPair<>(1, "apple");
        Pair<String, Integer> p2 = new OrderedPair<>("banana", 2);
        print(p1);
        print(p2);
    }

    private static <K, V> void print(Pair<K, V> pair) {
        System.out.println(pair);
    }
}
