package demo.io.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class SerializeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Serialization

        Ko obj = new Ko(10, 20, "子供");
        // 直列化するオブジェクトのチェック
        System.out.println("----直列化！----");
        System.out.println(obj.getName());
        System.out.println(obj.getX());
        System.out.println(obj.getY());

        // オブジェクトをファイルに書き込み
        FileOutputStream outFile = new FileOutputStream("temp/obj.bin");
        ObjectOutput out = new ObjectOutputStream(outFile);

        out.writeObject(obj);
        out.flush();

        out.close();
        outFile.close();

        // Deserialization

        // ファイルからオブジェクトを読み込んで復元
        FileInputStream inFile = new FileInputStream("temp/obj.bin");
        ObjectInputStream in = new ObjectInputStream(inFile);

        Ko restore = (Ko) in.readObject();

        in.close();
        inFile.close();

        // 復元されたオブジェクトのチェック
        System.out.println("-----復元！-----");
        System.out.println(restore.getName());
        System.out.println(restore.getX());
        System.out.println(restore.getY());
    }
}
