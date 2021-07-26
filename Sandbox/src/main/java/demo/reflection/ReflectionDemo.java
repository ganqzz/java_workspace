package demo.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectionDemo {

    private static class Vehicle implements Runnable {
        @Override
        public void run() {}

        protected void hoge() {}
    }

    private static class Car extends Vehicle {
        int speed;
        char driveType;
        String[] people;

        public Car() {
            this(4);
        }

        public Car(int doors) {
            people = new String[4];
        }

        public int drive(char driveType, int desiredSpeed) {
            selectDrive(driveType);

            while (speed != desiredSpeed) {
                accelerate();
            }

            return speed;
        }

        private int accelerate() {
            return ++speed;
        }

        private void selectDrive(char driveType) {
            this.driveType = driveType;
        }

        public static void print() {
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) throws Exception {
        Class<?> scls = Vehicle.class;
        Class<?> cls = Car.class;
        Car car = (Car) cls.newInstance();

        superClasses(cls);
        System.out.println("---");

        showInterfaces(scls);
        System.out.println("---");

        showInterfaces(cls); // not show indirectly implemented interfaces
        System.out.println("---");

        typeModifiers(cls);
        System.out.println("---");

        showMethods(cls);
        System.out.println("---");

        showDeclaredMethods(cls);
        System.out.println("---");

        showMethodsExcludesObject(cls);
        System.out.println("---");

        methodParameters(cls);
        System.out.println("---");

        listConstructorsParameters(cls);
        System.out.println("---");

        invokingMethods(cls);
        System.out.println("---");

        showFields(cls);
        System.out.println("---");

        lookingAtArrays(cls);
        System.out.println("---");

        manipulateFields(cls, car);
        System.out.println("---");

        manipulateArrayFields(cls, car);
        System.out.println("---");
    }

    private static void superClasses(Class<?> cls) {
        Class<?> superClass = cls.getSuperclass();
        while (superClass != null) {
            System.out.println(superClass.getName());
            superClass = superClass.getSuperclass();
        }
    }

    private static void showInterfaces(Class<?> cls) {
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> i : interfaces)
            System.out.println(i.getName());
    }

    private static void typeModifiers(Object obj) {
        Class<?> theClass = obj.getClass();
        int modifiers = theClass.getModifiers();
        System.out.println(Modifier.toString(modifiers));

        if ((modifiers & Modifier.FINAL) > 0) {
            System.out.println("bitwise check - final");
        }

        if (Modifier.isFinal(modifiers)) {
            System.out.println("method check  final");
        }

        if (Modifier.isPrivate(modifiers)) {
            System.out.println("method check  private");
        } else if (Modifier.isProtected(modifiers)) {
            System.out.println("method check  protected");
        } else if (Modifier.isPublic(modifiers)) {
            System.out.println("method check  public");
        }
    }

    private static void showMethods(Class<?> cls) { // public only, inherited/declared
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.printf("%s", method.getName());
            System.out.printf(": %s", method.getReturnType().getName());
            System.out.printf(" (%d) ", method.getParameterCount());
            System.out.println();
        }
    }

    private static void showDeclaredMethods(Class<?> cls) { // public/protected/private, declared only
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.printf("%s", method.getName());
            System.out.printf(": %s", method.getReturnType().getName());
            System.out.printf(" (%d) ", method.getParameterCount());
            System.out.println();
        }
    }

    private static void showMethodsExcludesObject(Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getDeclaringClass() != Object.class) {
                System.out.println(method.getName());
            }
        }
    }

    private static void methodParameters(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.printf("%s", method.getName());
            System.out.printf(" (%d) ", method.getParameterCount());
            Parameter[] parameters = method.getParameters();
            for (Parameter p : parameters) {
                System.out.printf("%s: %s, ", p.getName(), p.getType());
            }
            System.out.println();
        }
    }

    private static void listConstructorsParameters(Class<?> cls) {
        Constructor<?>[] ctors = cls.getDeclaredConstructors();
        for (Constructor<?> c : ctors) {
            System.out.printf("%s", c.getName());
            System.out.printf(" (%d) ", c.getParameterCount());
            Parameter[] parameters = c.getParameters();
            for (Parameter p : parameters) {
                System.out.printf("%s: %s,", p.getName(), p.getType());
            }
            System.out.println();
        }
    }

    private static void invokingMethods(Class<?> cls) throws InstantiationException,
                                                             IllegalAccessException,
                                                             InvocationTargetException,
                                                             NoSuchMethodException {
        Constructor<?>[] ctors = cls.getDeclaredConstructors();
        Car car = (Car) ctors[1].newInstance(4); //
        Method method = cls.getDeclaredMethod("drive", char.class, int.class);
        method.invoke(car, 'D', 6);

        method = cls.getDeclaredMethod("print");
        method.invoke(null); // static method

        method = cls.getDeclaredMethod("accelerate");
        // accessible private method (may be prevented by security manager)
        method.setAccessible(true);
        int s = (int) method.invoke(car); // result
        System.out.println("speed: " + s);
    }

    private static void showFields(Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.printf("%s (%s)\r\n", field.getName(), field.getType());
        }
    }

    private static void lookingAtArrays(Class<?> cls) throws NoSuchFieldException {
        Field field = cls.getDeclaredField("people");
        Class<?> t = field.getType();
        System.out.println(t.getTypeName());
        System.out.println(t.isArray());
    }

    private static void manipulateFields(Class<?> cls, Car car)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = cls.getDeclaredField("speed");
        Object obj = f.get(car);
        System.out.println(obj);

        car.drive('D', 33);

        obj = f.get(car);
        System.out.println(obj);

        f.set(car, 44);
        obj = f.get(car);
        System.out.println(obj);
    }

    private static void manipulateArrayFields(Class<?> cls, Car car)
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field field = cls.getDeclaredField("people");
        Array.set(field.get(car), 1, "Kevin");
        Object obj = Array.get(field.get(car), 1);
        System.out.println(obj);
    }
}
