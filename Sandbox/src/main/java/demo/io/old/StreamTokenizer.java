package demo.io.old;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StreamTokenizer;

class StreamTokenizerDemo {
    public static void main(String args[]) {
        try {
            // FileReaderオブジェクトを作成する
            FileReader fr = new FileReader(args[0]);

            // BufferedReaderオブジェクトを作成する
            BufferedReader br = new BufferedReader(fr);

            // StreamTokenizerオブジェクトを作成する
            StreamTokenizer st = new StreamTokenizer(br);

            // ピリオドを通常文字として定義する
            st.ordinaryChar('.');

            // アポストロフィを単語文字として定義する
            st.wordChars('\'', '\'');

            // トークンを処理する
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_WORD:
                        System.out.format("%02d) %s\n", st.lineno(), st.sval);
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        System.out.format("%02d) %s\n", st.lineno(), st.nval);
                        break;
                    default:
                        System.out.format("%02d) %c\n", st.lineno(), (char) st.ttype);
                }
            }

            // BufferedReaderオブジェクトをクローズする
            br.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class StreamTokenizerDemo2 {
    public static void main(String args[]) {
        try {
            // FileReaderオブジェクトを作成する
            FileReader fr = new FileReader(args[0]);

            // BufferedReaderオブジェクトを作成する
            BufferedReader br = new BufferedReader(fr);

            // StreamTokenizerオブジェクトを作成する
            StreamTokenizer st = new StreamTokenizer(br);

            // コンマを空白と見なす
            st.whitespaceChars(',', ',');

            // トークンを処理する
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_WORD:
                        System.out.format("%02d) %s\n", st.lineno(), st.sval);
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        System.out.format("%02d) %s\n", st.lineno(), st.nval);
                        break;
                    default:
                        System.out.format("%02d) %c\n", st.lineno(), (char) st.ttype);
                }
            }

            // BufferedReaderオブジェクトをクローズする
            br.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
