package demo.io.old;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class FileOutputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileOutputStreamオブジェクトを作成する
            FileOutputStream fos = new FileOutputStream(args[0]);

            // ファイルに12バイトを書き込む
            for (int i = 0; i < 12; i++) {
                fos.write(i);
            }

            // FileOutputStreamオブジェクトをクローズする
            fos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class FileInputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileInputStreamオブジェクトを作成する
            FileInputStream fis = new FileInputStream(args[0]);

            // データを読み取って、表示する
            int i;
            while ((i = fis.read()) != -1) {
                System.out.println(i);
            }

            // FileInputStreamオブジェクトをクローズする
            fis.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class BufferedOutputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileOutputStreamオブジェクトを作成する
            FileOutputStream fos = new FileOutputStream(args[0]);

            // BufferedOutputStreamオブジェクトを作成する
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // ファイルに12バイトを書き込む
            for (int i = 0; i < 12; i++) {
                bos.write(i);
            }

            // BufferedOutputStreamオブジェクトをクローズする
            bos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class BufferedInputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileInputStreamを作成する
            FileInputStream fis = new FileInputStream(args[0]);

            // BufferedInputStreamオブジェクトを作成する
            BufferedInputStream bis = new BufferedInputStream(fis);

            // データを読み取って、表示する
            int i;
            while ((i = bis.read()) != -1) {
                System.out.println(i);
            }

            // BufferedInputStreamオブジェクトをクローズする
            bis.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class DataOutputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileOutputStreamオブジェクトを作成する
            FileOutputStream fos = new FileOutputStream(args[0]);

            // DataOutputStreamオブジェクトを作成する
            DataOutputStream dos = new DataOutputStream(fos);

            // 各種のデータ型を書き込む
            dos.writeBoolean(false);
            dos.writeByte(Byte.MAX_VALUE);
            dos.writeChar('A');
            dos.writeDouble(Double.MAX_VALUE);
            dos.writeFloat(Float.MAX_VALUE);
            dos.writeInt(Integer.MAX_VALUE);
            dos.writeLong(Long.MAX_VALUE);
            dos.writeShort(Short.MAX_VALUE);

            // DataOutputStreamオブジェクトをクローズする
            dos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class DataInputStreamDemo {
    public static void main(String args[]) {
        try {
            // FileInputStreamオブジェクトを作成する
            FileInputStream fis = new FileInputStream(args[0]);

            // DataInputStreamオブジェクトを作成する
            DataInputStream dis = new DataInputStream(fis);

            // データを読み取り、表示する
            System.out.println(dis.readBoolean());
            System.out.println(dis.readByte());
            System.out.println(dis.readChar());
            System.out.println(dis.readDouble());
            System.out.println(dis.readFloat());
            System.out.println(dis.readInt());
            System.out.println(dis.readLong());
            System.out.println(dis.readShort());

            // DataInputStreamオブジェクトをクローズする
            dis.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
