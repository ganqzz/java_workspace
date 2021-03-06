package demo.generics.why;

/**
 * Queueとして利用可能
 *
 * @param <T>
 */
public class GenericCircularBuffer<T> {
    private final T[] buffer;
    private int readCursor = 0; // front
    private int writeCursor = 0; // end

    @SuppressWarnings("unchecked")
    public GenericCircularBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    public boolean offer(T value) {
        if (buffer[writeCursor] != null) {
            return false;
        }

        buffer[writeCursor] = value;
        writeCursor = next(writeCursor);
        return true;
    }

    public T poll() {
        T value = buffer[readCursor];
        if (value != null) {
            buffer[readCursor] = null;
            readCursor = next(readCursor);
        }
        return value;
    }

    private int next(int index) {
        return (index + 1) % buffer.length;
    }
}
