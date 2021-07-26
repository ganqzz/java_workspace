package com.masayukikaburagi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * ユーティリティ
 * static methodの集合
 *
 * @author 鏑木雅之
 */
public class Util {
    /**
     * Integer wrapper
     *
     * @param i integer String
     * @return An Integer; null, if invalid.
     */
    public static Integer toInteger(String i) {
        try {
            return Integer.valueOf(i.trim());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Encode from String to Base64 String
     *
     * @param value
     * @return A Base64 String
     */
    public static String encodeBase64FromString(String value) {
        return Base64.encodeToString(value.getBytes(), Base64.NO_WRAP);
    }

    /**
     * Decode from Base64 String to String
     *
     * @param value Base64 String
     * @return A decoded String; null, if failed.
     */
    public static String decodeBase64ToString(String value) {
        byte[] bytes = Base64.decode(value, Base64.NO_WRAP);
        return bytes.length > 0 ? new String(bytes) : null;
    }

    /**
     * パスワード暗号化（一方向）
     *
     * @param password
     * @return An encrypted String (40 chars)
     */
    public static String encryptPassword(String password) {
        // とりあえずの実装。Productionとして使うには、もっと安全な方法を実装すること。
        String salt = "awawa"; // 固定値かつハードコード
        String algo = "SHA1"; // これも時代遅れ

        String salted = salt + password;
        String encrypted = "";
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            md.update(salted.getBytes());
            encrypted = byteArrayToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encrypted; // "hoge" => f071bdd387ad1d25e53ede54b4f1885f416ea5a9
    }

    /**
     * バイト配列を16進数文字列に変換する
     *
     * @param bytes byte[]
     * @return A Hex String
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = String.format("%02x", b); // イディオム
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Direct convert from InputStream to String
     *
     * @param is InputStream
     * @return Converted String
     * @throws IOException
     */
    public static String convertInputStreamToString(InputStream is) throws IOException {
        final int size = 1024;
        byte[] buffer = new byte[size];
        ByteArrayOutputStream out = new ByteArrayOutputStream(size); // no need to close
        int length;
        while ((length = is.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        return out.toString("UTF-8");
    }

    public String convert(InputStream inputStream) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
            return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
        }
    }

    /**
     * Object dumper
     *
     * @param o An Object to be dumped.
     * @return Dumped String
     */
    public static String dumpObject(Object o) {
        StringBuilder sb = new StringBuilder();

        sb.append("---").append("\n");

        try {
            Class klass = o.getClass();
            sb.append(klass).append("\n\n");

            // all
            sb.append("---").append("\n");
            for (Field f : klass.getDeclaredFields()) {
                sb.append(f.getName()).append(": ");
                try {
                    f.setAccessible(true);
                    Object fval = f.get(o);
                    sb.append(fval.toString()).append("\n");
                } catch (Exception e) {
                    sb.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sb.append("---").append("\n");

        return sb.toString();
    }

    private Util() {} // no instantiation
}
