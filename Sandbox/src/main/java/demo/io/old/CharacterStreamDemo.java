package demo.io.old;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class FileWriterDemo {
    public static void main(String[] args) {
        try {
            // FileWriterオブジェクトを作成する
            FileWriter fw = new FileWriter(args[0]);

            // ファイルに文字列を書き込む
            for (int i = 0; i < 12; i++) {
                fw.write("Line " + i + "あ\n"); // 改行コード
            }

            // FileWriterオブジェクトをクローズする
            fw.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class FileReaderDemo {
    public static void main(String[] args) {
        try {
            // FileReaderオブジェクトを作成する
            FileReader fr = new FileReader(args[0]);

            // 文字を読み取って、表示する
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i); // キャスト
            }

            // FileReaderオブジェクトをクローズする
            fr.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class BufferedWriterDemo {
    public static void main(String[] args) {
        try {
            // FileWriterオブジェクトを作成する
            FileWriter fw = new FileWriter(args[0]);

            // BufferedWriterオブジェクトを作成する
            BufferedWriter bw = new BufferedWriter(fw);

            // ファイルに文字列を書き込む
            for (int i = 0; i < 12; i++) {
                bw.write("Line " + i + "\n");
            }

            // BufferedWriterオブジェクトをクローズする
            bw.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class BufferedReaderDemo {
    public static void main(String[] args) {
        try {
            // FileReaderオブジェクトを作成する
            FileReader fr = new FileReader(args[0]);

            // BufferedReaderオブジェクトを作成する
            BufferedReader br = new BufferedReader(fr);

            // ファイルから行を読み取って、表示する
            String s;
            while ((s = br.readLine()) != null)
                System.out.println(s);

            // BufferedReaderオブジェクトをクローズする
            br.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class ReadConsole {
    public static void main(String[] args) {
        try {
            // InputStreamReaderオブジェクトを作成する
            InputStreamReader isr = new InputStreamReader(System.in);

            // BufferedReaderオブジェクトを作成する
            BufferedReader br = new BufferedReader(isr);

            // コンソールから行を読み取って、処理する
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s.length());
            }

            // BufferedReaderオブジェクトをクローズする
            br.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class PrintWriterDemo {
    public static void main(String[] args) {
        try {
            // PrintWriterオブジェクトを作成する
            PrintWriter pw = new PrintWriter(System.out);

            // いくつかのメソッドを実行する
            pw.println(true);
            pw.println('A');
            pw.println(500);
            pw.println(40000L);
            pw.println(45.67f);
            pw.println(45.67);
            pw.println("Hello");
            pw.println(Integer.valueOf("99"));

            // PrintWriterオブジェクトをクローズする
            pw.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
