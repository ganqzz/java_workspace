package demo.io.old;

import java.io.EOFException;
import java.io.RandomAccessFile;

// Tail
class RandomAccessFileDemo {
    public static void main(String args[]) {
        try {
            // RandomAccessFileオブジェクトを作成する
            RandomAccessFile raf = new RandomAccessFile(args[0], "r");

            // ファイルの終わりに表示する
            // バイト数を決める
            long count = Long.valueOf(args[1]).longValue();

            // ファイルの長さを決める
            long position = raf.length();

            // 現在の位置にシークする
            position -= count;
            if (position < 0) position = 0;
            raf.seek(position);

            // バイトを読み取って、表示する
            while (true) {
                // バイトを読み取る
                try {
                    byte b = raf.readByte();

                    // 文字として表示する
                    System.out.print((char) b);
                } catch (EOFException eofe) {
                    break;
                }
            }
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
