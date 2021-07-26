package demo.reflection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Reflection {
    public static void main(String[] args) {
        String s = null;
        try {
            System.out.print("Enter a Class Name to search for: ");
            // Consoleが使えない場合（IDEの中など）は、nullが返ってくる
            s = System.console() == null
                ? new BufferedReader(new InputStreamReader(System.in)).readLine()
                : System.console().readLine();
            Class<?> c = Class.forName(s);
            System.out.println(c.getName());
            System.out.println(c.getSuperclass().getName());
            System.out.println();
            for (Method m : c.getMethods()) {
                System.out.println(m);
            }
            for (Field f : c.getFields()) {
                System.out.println(f);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The class \"" + s + "\" is not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
