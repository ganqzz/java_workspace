package demo.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = Hoge.class;
        process(clazz);
    }

    public static boolean process(Class<?> clazz) {
        String classname = clazz.getName();
        System.out.println("--- " + classname);

        if (clazz.isAnnotationPresent(Doc.class)) {
            for (Method method : clazz.getDeclaredMethods()) {
                int modifiers = method.getModifiers();
                if (!Modifier.isPrivate(modifiers)) {
                    if (method.isAnnotationPresent(Doc.class)) {
                        System.out.println("\t--- " + method.getName() + "()");
                        Doc doc = method.getAnnotation(Doc.class);
                        System.out.println("\t" + doc.desc());
                        System.out.println("\t" + doc.returnVal());

                        // annotationを使って何かする(verify, modify,...)
                    }
                }
            }
        }

        return true;
    }
}
