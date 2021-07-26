package demo.generics;

import java.util.AbstractList;
import java.util.List;

public class CustomArrayList<T> extends AbstractList<T> {

    private T[] values;

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        values = (T[]) new Object[0];
    }

    @Override
    public T get(final int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return values[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(final T o) {
        T[] newValues = (T[]) new Object[size() + 1];
        System.arraycopy(values, 0, newValues, 0, size());
        newValues[size()] = o;
        values = newValues;
        return true;
    }

    @Override
    public int size() {
        return values.length;
    }

    public static void main(String[] args) {
        Object[] array = new String[3];
        Integer[] otherArray = (Integer[]) array;

        List<Integer> arrayList = new CustomArrayList<>();

        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
    }
}
