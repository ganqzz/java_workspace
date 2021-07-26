package basic;

public class ArrayCovariant {
    public static void main(String[] args) {
        String[] strings = new String[2];
        Object[] objects = strings;
        objects[0] = 1; // ArrayStoreException
    }
}
