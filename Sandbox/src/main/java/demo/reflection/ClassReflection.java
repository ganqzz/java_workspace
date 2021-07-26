package demo.reflection;

public class ClassReflection {

    public static void main(String[] args) throws Exception {
        showClassInfo(int.class);
        showClassInfo(int[].class);
        showClassInfo(int[][].class);
        showClassInfo(byte.class);
        showClassInfo(byte[].class);
        showClassInfo(char.class);
        showClassInfo(char[].class);
        showClassInfo(short.class);
        showClassInfo(short[].class);
        showClassInfo(long.class);
        showClassInfo(long[].class);
        showClassInfo(float.class);
        showClassInfo(float[].class);
        showClassInfo(double.class);
        showClassInfo(double[].class);
        showClassInfo(boolean.class);
        showClassInfo(boolean[].class);

        showClassInfo(Integer.class);
        showClassInfo(Integer[].class);
        showClassInfo(String[].class);

        showClassInfo(Class.forName("[I"));
        showClassInfo(Class.forName("[Ljava.lang.String;"));
    }

    private static void showClassInfo(Class<?> clazz) {
        System.out.println(clazz.getName());
    }
}
