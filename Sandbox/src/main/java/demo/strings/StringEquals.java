package demo.strings;

class StringEquals {
    public static void main(String[] args) {
        String s1 = "hoge";
        String s2 = "hoge";
        String s3 = new String("hoge");

        // == は、参照の比較になることに注意
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));

        s1 += "fuga"; // new object id
    }
}
