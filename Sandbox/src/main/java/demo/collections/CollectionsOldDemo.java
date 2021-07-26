package demo.collections;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

// Vector
class VectorDemo {
    public static void main(String args[]) {
        // ベクトルとその要素を作成する
        Vector<Object> vector = new Vector<Object>();
        vector.add(new Integer(5));
        vector.add(new Float(-14.14f));
        vector.add(new String("Hello"));
        vector.add(new Long(120000000));
        vector.add(new Double(-23.45e-11));

        // ベクトルの要素を表示する
        System.out.println(vector);

        // ベクトルに要素を挿入する
        String s = new String("String to be inserted");
        vector.insertElementAt(s, 1);
        System.out.println(vector);

        // ベクトルから要素を削除する
        Object removedObj = vector.remove(3);
        System.out.println(vector);
    }
}

class NonGenericsDemo {
    public static void main(String args[]) {
        Vector v = new Vector();
        v.add(1);
        v.add(2);
        v.add(3);
        // 次の行の行頭のコメントを外すと、実行時にエラーが発生する
        // v.add("4");

        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            sum += (Integer) v.get(i); // ClassCastException
        }
        System.out.println("Total: " + sum);
    }
}

class GenericsDemo2 {
    public static void main(String args[]) {
        Vector<Integer> v = new Vector<Integer>();
        v.add(1);
        v.add(2);
        v.add(3);
        // 次の行の行頭のコメントを外すと、コンパイル時にエラーが発生する
        // v.add("4");

        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            sum += v.get(i); // Unboxing
        }
        System.out.println("Total: " + sum);
    }
}

class EnumerationDemo {
    public static void main(String args[]) {
        // ベクトルとその要素を作成する
        Vector<Object> vector = new Vector<Object>();
        vector.add(new Integer(5));
        vector.add(new Float(-14.14f));
        vector.add(new String("Hello"));
        vector.add(new Long(120000000));
        vector.add(new Double(-23.45e-11));

        // ベクトルの要素を表示する
        Enumeration<Object> e = vector.elements();
        while (e.hasMoreElements()) {
            Object obj = e.nextElement();
            System.out.println(obj);
        }
    }
}

class IteratorDemo {
    public static void main(String args[]) {
        // ベクトルとその要素を作成する
        Vector<Object> vector = new Vector<Object>();
        vector.add(new Integer(5));
        vector.add(new Float(-14.14f));
        vector.add(new String("Hello"));
        vector.add(new Long(120000000));
        vector.add(new Double(-23.45e-11));

        // ベクトルの要素を表示する
        for (Object obj : vector)
            System.out.println(obj);
    }
}

// Stack
class PushPop {
    public static void main(String args[]) {
        // スタックを作成する
        Stack<Integer> stack = new Stack<Integer>();

        // 要素をスタックにプッシュする
        for (int i = 0; i < args.length; i++)
            stack.push(new Integer(args[i]));

        // スタックから要素をポップして表示する
        while (!stack.empty()) {
            Integer obj = stack.pop();
            System.out.println(obj);
        }
    }
}

// HashTable
class HashtableDemo {
    public static void main(String args[]) {
        // ハッシュ表を作成し、情報を設定する
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("apple", "red");
        hashtable.put("strawberry", "red");
        hashtable.put("lime", "green");
        hashtable.put("banana", "yellow");
        hashtable.put("orange", "orange");

        // ハッシュ表の要素を表示する
        Enumeration<String> e = hashtable.keys();
        while (e.hasMoreElements()) {
            String k = e.nextElement();
            String v = hashtable.get(k);
            System.out.println("key = " + k + "; value = " + v);
        }

        // appleの値を表示する
        System.out.print("\nThe color of an apple is: ");
        String v = hashtable.get("apple");
        System.out.println(v);
    }
}

// StringTokenizer
class StringTokenizerDemo {
    public static void main(String args[]) {
        String str = "123/45.6/-11.2/41/-90.1/100/99.99/-50/-20";
        StringTokenizer st = new StringTokenizer(str, "/");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            System.out.println(s);
        }
    }
}
