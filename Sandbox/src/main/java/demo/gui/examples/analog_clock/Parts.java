package demo.gui.examples.analog_clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

// 時計盤
class AnalogClockBoard {
    private final AnalogClockPanel panel;
    protected int cx, cy, r, w;

    public AnalogClockBoard(AnalogClockPanel panel) {
        this.panel = panel;
    }

    public void resize() {
        // 盤のスペック
        this.cx = panel.getCx();
        this.cy = panel.getCy();
        this.r = panel.getR();
        w = r + 3; // 盤の淵の幅
    }

    public void draw(Graphics2D g) {
        // 盤描画
        g.setColor(Color.BLACK);
        g.fillOval(cx - w, cy - w, 2 * w, 2 * w);
        g.setColor(new Color(0xF0F0F0));
        g.fillOval(cx - r, cy - r, 2 * r, 2 * r);

        // 目盛描画
        drawBoard(g);

        // 中心
        g.setColor(Color.BLACK);
        g.fillOval(cx - 4, cy - 4, 8, 8);
    }

    private void drawBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < 12; i++) {
            double degrees = i * 30; // 1時間毎
            double radians = Math.toRadians(degrees);
            int ax = cx + (int) (0.9 * r * Math.cos(radians));
            int ay = cy - (int) (0.9 * r * Math.sin(radians));
            int bx = cx + (int) (r * Math.cos(radians));
            int by = cy - (int) (r * Math.sin(radians));
            g.drawLine(ax, ay, bx, by);
        }
    }
}

// abstract 針
abstract class Hand {
    private final AnalogClockPanel panel;
    protected int cx, cy; // 中心
    protected int ex, ey; // 端点
    protected Color color;
    protected double factor;
    protected double len; // 針の長さ
    protected Stroke stroke;
    protected int tip1, tip2; // 針先の大きさ
    protected int second;
    protected int minute;
    protected int hour;

    Hand(AnalogClockPanel panel) {
        this.panel = panel;
        setTime();
    }

    // 角度計算（度）
    abstract protected double calcAngle();

    // リフレッシュ
    public void refresh() {
        setTime();
        calcPoint();
    }

    // 時間設定
    public void setTime() {
        this.second = panel.getSecond();
        this.minute = panel.getMinute();
        this.hour = panel.getHour();
    }

    // 端点の座標
    public void calcPoint() {
        double angle = Math.toRadians(calcAngle());
        ex = cx + (int) (len * Math.cos(angle));
        ey = cy - (int) (len * Math.sin(angle)); // 下方向がプラスなので、+-が逆向きになる。
    }

    public void resize() {
        this.cx = panel.getCx();
        this.cy = panel.getCx();
        this.len = panel.getR() * factor;
        calcPoint();
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(stroke);
        g.drawLine(cx, cy, ex, ey);
        g.fillOval(ex - tip2, ey - tip2, 2 * tip2, 2 * tip2);
        g.setColor(Color.WHITE);
        g.fillOval(ex - tip1, ey - tip1, 2 * tip1, 2 * tip1);
    }
}

// 秒針
class SecondHand extends Hand {
    SecondHand(AnalogClockPanel panel) {
        super(panel);
        color = Color.RED;
        factor = 0.8;
        tip1 = 2;
        tip2 = 4;
        stroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    protected double calcAngle() {
        return 90.0 - 6 * second;
    }
}

// 分針
class MinuteHand extends Hand {
    MinuteHand(AnalogClockPanel panel) {
        super(panel);
        color = Color.GREEN;
        factor = 0.7;
        tip1 = 3;
        tip2 = 5;
        stroke = new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    protected double calcAngle() {
        return 90.0 - 6 * minute;
    }
}

// 時針
class HourHand extends Hand {
    HourHand(AnalogClockPanel panel) {
        super(panel);
        color = Color.BLUE;
        factor = 0.6;
        tip1 = 4;
        tip2 = 6;
        stroke = new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    protected double calcAngle() {
        return 90.0 - 30 * hour - 0.5 * minute;
    }
}
