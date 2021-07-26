package demo.generics.classes_and_interfaces;

import java.util.Comparator;

/**
 * built-in: Comparator.reverseOrder()
 *
 * @param <T>
 */
public class Reverser<T> implements Comparator<T> {
    private final Comparator<T> comparator;

    public Reverser(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    // Decorator pattern
    @Override
    public int compare(T left, T right) {
        return comparator.compare(right, left);
    }
}
