package demo.base64;

import java.util.Base64;

/**
 * Java8~
 */
public class Base64Demo {

    public static void main(String[] args) {
        String[] testValues = {
                "hoge:hoge",
                "あいうえおかきくけこ",
                "okoglngl:pwoetjpwetwenknlknldnlgjbo@wegtnwegnqihoqegnelknlqebflqwebgegbleetg4j9034t58knkrlgn",
                "ABCDEFG",
        };

        for (String value : testValues) {
            System.out.println(value);
            // String => bytes => (base64 encoded bytes) => String(ISO_8859_1)
            String encodedValue = Base64.getEncoder().encodeToString(value.getBytes());
            System.out.println(encodedValue);

            // String(ISO_8859_1) => (base64 encoded bytes) => bytes => String
            String decodedValue = new String(Base64.getDecoder().decode(encodedValue));
            System.out.println(decodedValue);
            System.out.println("---");
        }
    }
}
