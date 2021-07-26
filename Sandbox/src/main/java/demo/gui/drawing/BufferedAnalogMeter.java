package demo.gui.drawing;

import demo.gui.components.DemoBaseFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class BufferedAnalogMeter extends JPanel implements Runnable {

    private static final long FPS = 60;
    private static final long INTERVAL = 1000 / FPS;

    private double frequency; // 周波数
    private long time; // 経過時間
    private double angle; // 角度
    private Thread t;

    BufferedAnalogMeter(double frequency) {
        this.frequency = frequency;
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 図形や線のアンチエイリアシングの有効化
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // stroke
        g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // ウィンドウのサイズを調べる
        Dimension d = getSize();
        int h = d.height;
        int w = d.width;

        // 文字盤の半径を計算する
        int r1 = (int) (0.4 * w);
        int r2 = (int) (0.8 * h);
        int radius = Math.min(r1, r2);

        // 文字盤を表す半円形を描画する
        int cx = (int) (w * 0.5);
        int cy = (int) (h * 0.9);
        int xa = cx - radius;
        int ya = cy - radius;
        g.drawArc(xa, ya, 2 * radius, 2 * radius, 0, 180);

        // メーターの底部を表す線を描画する
        g.drawLine(cx - radius, cy, cx + radius, cy);

        // 針を表す線を描画する
        int x = (int) (cx - radius * 0.9 * Math.cos(angle));
        int y = (int) (cy - radius * 0.9 * Math.sin(angle));
        g.setColor(Color.RED);
        g.drawLine(cx, cy, x, y);
    }

    @Override
    public void run() {
        while (true) {
            // ラジアンを計算する
            double radians = 2 * Math.PI * frequency * time / 1000;

            // 現在の正弦波の値を計算する
            double value = Math.sin(radians);

            // 針の角度を計算する
            if (value > 1) {
                angle = Math.PI;
            } else if (value < -1) {
                angle = 0;
            } else {
                angle = Math.PI * (value + 1) / 2; // 180度で反転
            }

            // 時間を更新する
            time += INTERVAL;

            // 描画の更新を要求する
            repaint();

            // 次のサンプリングまで休止する
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // スレッドを起動する
        t = new Thread(this);
        t.start();
    }

    @Override
    public void removeNotify() {
        t.interrupt();
        super.removeNotify();
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new BufferedAnalogMeter(1.0));
    }
}
