package demo.generics.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectingGenericsInfoExamples {

    // sub class of a generic class with a specific type
    public static class StringList extends ArrayList<String> {
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        Class<?> arrayListClass = strings.getClass();
        System.out.println(arrayListClass);

        TypeVariable<? extends Class<?>>[] typeParameters = arrayListClass.getTypeParameters();
        System.out.println(Arrays.toString(typeParameters));

        System.out.println(arrayListClass.toGenericString()); // Java8

        printParameterizedType(strings.getClass());
        printParameterizedType(StringList.class); //
    }

    public static void printParameterizedType(Class<?> aClass) {
        ParameterizedType superclass =
                (ParameterizedType) aClass.getGenericSuperclass();
        Type typeVariable = superclass.getActualTypeArguments()[0];
        System.out.println(typeVariable);
    }
}
