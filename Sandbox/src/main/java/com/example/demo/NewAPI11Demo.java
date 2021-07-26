package com.example.demo;

public class NewAPI11Demo {
    public static void main(String[] args) {
        System.out.println("Hoge".repeat(5));
        System.out.println(" \n\t 　 \u2005".isBlank());
        System.out.println("'" + " \n\t  Hoge  \u2005　".trim() + "'");
        System.out.println("'" + " \n\t  Hoge  \u2005　".strip() + "'");
        System.out.println("'" + " \n\t  Hoge  \u2005　".stripLeading() + "'");
        System.out.println("'" + " \n\t  Hoge  \u2005　".stripTrailing() + "'");
        System.out.println(Character.isWhitespace('\u2005'));

        var s = "a\nb\nc\n".lines();
        s.forEach(System.out::println);
        System.out.println();

        // another
        // Files#readString/writeString
        // Optional#isEmpty
        // Predicate.not
    }
}
