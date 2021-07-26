package demo.io.serialization;

class Oya {
    private int x, y;

    // 引数のないコンストラクタ
    public Oya() {
        x = 0;
        y = 0;
    }

    // 引数のあるコンストラクタ
    public Oya(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // x のセッター
    public void setX(int x) {
        this.x = x;
    }

    // y のセッター
    public void setY(int y) {
        this.y = y;
    }

    // x のゲッター
    public int getX() {
        return x;
    }

    // y のゲッター
    public int getY() {
        return y;
    }
}
