package demo.properties;

public class SystemProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);

        System.out.println("---");

        System.setProperty("hoge.hoge", "HOGE HOGE");
        System.out.println("hoge.hoge=" + System.getProperty("hoge.hoge"));

        System.out.println("---");

        // line separator
        //System.out.println(printByteString(System.getProperty("line.separator")));
        System.out.println(printByteString(System.lineSeparator())); // Java7

        System.out.println("---");

        // Environment Variables
        System.out.println(System.getenv());
    }

    private static String printByteString(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            res += String.format("%02X", (int) str.charAt(i));
        }
        return res;
    }
}
