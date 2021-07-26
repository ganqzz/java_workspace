package demo.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * DataInputStream / DataOutputStream
 */
public class PrimitiveRW {

    public static final int SIZE = 100;

    public static void main(String[] args) {
        byte[] data = new byte[SIZE];

        try (ByteArrayOutputStream bao = new ByteArrayOutputStream(SIZE);
             DataOutputStream out = new DataOutputStream(bao);) {
            out.writeInt(11);
            out.writeUTF("Hoge");
            data = bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));) {
            System.out.println(in.readInt());
            System.out.println(in.readUTF());
        } catch (EOFException e) {
            // EOFExceptionの場合は、ファイル破損、書き込みと読み込みが対応していないなど。
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
