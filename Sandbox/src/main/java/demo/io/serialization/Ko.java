package demo.io.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Ko extends Oya implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    // コンストラクタ
    public Ko(int i, int j, String str) {
        this.name = str;
        super.setX(i);
        super.setY(j);
    }

    // name のゲッター
    public String getName() {
        return name;
    }

    // 直列化のカスタマイズ
    private void writeObject(ObjectOutputStream out) throws IOException {

        // デフォルトの直列化プロセス
        out.defaultWriteObject();

        // 追加の直列化処理
        out.writeInt(super.getX());
        out.writeInt(super.getY());
    }

    // 直列化復元のカスタマイズ
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        // デフォルトの直列化復元のプロセス
        in.defaultReadObject();

        // 追加の復元処理
        int x = in.readInt();
        int y = in.readInt();
        super.setX(x);
        super.setY(y);
    }
}
