package demo.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {

    private static class A {
    }

    public static void main(String args[]) {

        System.out.println("ArrayListの使い方");
        System.out.println("要素の追加");
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(100);
        arr.add(200);
        arr.add(300);
        arr.add(400);
        System.out.println("arr : " + arr);

        System.out.println("要素の挿入");
        arr.add(2, 250); // index
        System.out.println("arr : " + arr);

        System.out.println("要素の削除");
        arr.remove(3); // index
        System.out.println("arr : " + arr);

        System.out.println("要素の参照");
        Integer num = arr.get(0);
        System.out.println("arrの0番目の要素 : " + num);

        System.out.println("要素の更新");
        arr.set(0, 50);
        System.out.println("arr : " + arr);

        System.out.println("イテレータの使い方1");
        for (Integer obj : arr) {
            System.out.println(obj);
        }

        // ジェネリクスでIntegerを指定しているので、
        // Integerに関係ないオブジェクトをnewすることは出来ない
        // arr.add("abcde");
        // arr.add('a'); // charは整数型ではあるが、直接IntegerへBoxingすることはできない。

        System.out.println("\nArrayListには色々なデータを持たせることが可能");
        ArrayList<Object> arr2 = new ArrayList<Object>();
        arr2.add(100);
        arr2.add("こんにちわ");
        arr2.add(3.14);
        A aobj = new A();
        arr2.add(aobj);
        arr2.add(new int[5]);
        System.out.println("arr2 : " + arr2);

        // ジェネリクスなしでもArrayListは使えるが、
        // 警告が出るため、ジェネリクスなしでは使わないこと。
        ArrayList arr3 = new ArrayList();

        // ここで警告が出る
        // arr3.add(100);

        // iteratorメソッドは、ArrayListのインスタンスを
        // Iterator I/F型に入れ直して出力する。
        // ArrayListはIteratorを実装しているので、Iterator I/F型に
        // インスタンスを代入することは可能
        System.out.println("イテレータの使い方2");
        Iterator<Integer> ite = arr.iterator();
        while (ite.hasNext()) {
            Integer iObj = ite.next();
            System.out.println(iObj);
        }
    }
}
