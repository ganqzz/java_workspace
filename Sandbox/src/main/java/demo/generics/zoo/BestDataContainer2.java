package demo.generics.zoo;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An Iterable of Comparable objects' BestDataContainer
 *
 * @param <E> the Type of data to store
 *            WARNING: THIS CODE IS NOT THREAD SAFE OR MULTIPLE USER CONCURRENT SAFE
 */
public class BestDataContainer2<E extends Comparable<? super E>> implements Iterable<E> {

    private E[] data; //data structure
    private int countItems = 0; //number of valid items

    /**
     * Default Constructor.
     */
    public BestDataContainer2() {
        this(10);
    }

    /**
     * Explicit Constructor. Create empty array
     *
     * @param capacity maximum size of data structure
     */
    public BestDataContainer2(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * Explicit Constructor.
     *
     * @param list create from List
     */
    public BestDataContainer2(List<E> list) {
        data = (E[]) list.toArray();
        countItems = data.length; // full
    }

    /**
     * Get the current size (not the length!)
     *
     * @return count of items in the Container
     */
    public int size() {
        return countItems;
    }

    /**
     * Add an item to the container.
     * Can't add null, can't add past capacity, can't add duplicates
     *
     * @param item to add
     * @return true if added, else false
     */
    public boolean add(E item) {
        //can't add if item is null
        if (item == null) {
            return false;
        }

        //can't add if at capacity
        if (countItems == data.length) {
            return false;
        }

        //don't add duplicate
        int itemIndex = findItem(item);
        if (itemIndex > 0) {
            return false;
        }

        //add to next item
        data[countItems++] = item;
        return true;
    }

    /**
     * Remove an item if it exists by index.
     *
     * @param index the index to remove from the container.
     * @return true if item is removed, else false.
     * @throws NoSuchElementException if index is not valid
     */
    public boolean remove(int index) {
        validateIndex(index);
        int i = index;
        int j = i + 1;
        //remove it:
        while (j < countItems) {
            data[j - 1] = data[j];
            j++;
        }
        countItems--;
        return true;
    }

    /**
     * Remove an item that is equal to the passed in item.
     * can't remove a null item, can't remove an item if it is not in the container.
     *
     * @param item the item to remove.
     * @return true if the item is removed, else false.
     */
    public boolean remove(E item) {
        if (item == null) return false;
        int index = findItem(item);
        if (index < 0) return false;
        return remove(index);
    }

    /**
     * Find an item by the item itself
     * relies on valid equality being implemented in type T
     *
     * @param item the item for which to find the index
     * @return the index of the item if found, -1 if not in the container.
     */
    public int findItem(E item) {
        for (int i = 0; i < countItems; i++) {
            if (data[i].equals(item)) return i;
        }
        return -1;
    }

    /**
     * Get an item at an index.
     *
     * @param index the index to get an item from.
     * @return the item at the given index.
     * @throws NoSuchElementException if the index is invalid.
     */
    public E get(int index) {
        validateIndex(index);
        return data[index];
    }

    /**
     * Validate the index.
     *
     * @param index the index to validate.
     * @throws NoSuchElementException if the index is invalid.
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= countItems) {
            throw new NoSuchElementException("Invalid Index");
        }
    }

    /**
     * Print out all the items in the container.
     * relies on toString() being implemented in the type T
     *
     * @return String representing all items in the container.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countItems; i++) {
            sb.append("Next Item: ").append(data[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Get the maximum item from the Container
     *
     * @return the maximum item in the Container.
     */
    public E getMax() {
        if (countItems > 0) {
            E max = data[0];
            for (int i = 1; i < countItems; i++) {
                max = BestDataContainer2.getMax(max, (E) data[i]);
            }
            return max;
        } else if (countItems == 0) {
            return data[0];
        }
        return null;
    }

    /**
     * Get the minimum item
     *
     * @return the item that is the minimum in the container
     */
    public E getMin() {
        if (countItems > 0) {
            E min = data[0];
            for (int i = 1; i < countItems; i++) {
                min = BestDataContainer2.getMin(min, (E) data[i]);
            }
            return min;
        } else if (countItems == 0) {
            return data[0];
        }
        return null;
    }

    /**
     * Get the maximum of two items that implement Comparable.
     *
     * @param o1 the first item
     * @param o2 the second item
     * @return the max of the two items
     */
    public static <T extends Comparable<? super T>> T getMax(T o1, T o2) {
        return (o1.compareTo(o2) > 0 ? o1 : o2);
    }

    /**
     * Get the minimum of two items that implement Comparable
     *
     * @param o1 the first item
     * @param o2 the second item
     * @return the min of the two items
     */
    public static <T extends Comparable<? super T>> T getMin(T o1, T o2) {
        return (o1.compareTo(o2) < 0 ? o1 : o2);
    }

    @Override
    public Iterator<E> iterator() {
        return new BestDataContainerIterator();
    }

    public Iterator<E> iterator(int index) {
        return new BestDataContainerIterator(index);
    }

    /**
     * Non static
     * Container側で指定した型パラメータを利用する
     */
    private class BestDataContainerIterator implements Iterator<E> {
        private int currentIndex;
        private int priorIndex;
        private final int BAD_INDEX = -1;

        /**
         * Create an iterator at the front of the container.
         */
        public BestDataContainerIterator() {
            this(0);
        }

        /**
         * Create an iterator from the index
         *
         * @param index the starting position of the iterator.
         */
        public BestDataContainerIterator(int index) {
            validateIndex(index);
            currentIndex = index;
            priorIndex = BAD_INDEX;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            priorIndex = currentIndex;
            return data[currentIndex++];
        }

        @Override
        public void remove() {
            checkValidRemoveState();

            //remove it
            BestDataContainer2.this.remove(priorIndex);

            //make another remove impossible.
            priorIndex = BAD_INDEX;
        }

        private void checkValidRemoveState() {
            if (priorIndex == BAD_INDEX)
                throw new IllegalStateException("Must move next before removing items");
        }
    }
}
