package demo.gui.drawing;

import demo.gui.components.DemoBaseFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Counter extends JPanel {

    private int counter = 0;
    private boolean active = false;
    private final Timer timer;

    Counter() {
        setBackground(Color.WHITE);

        // タイマーを作成する
        timer = new Timer(1000, e -> {
            ++counter;
            repaint(); // 再描画を要求する
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                active = !active;
                if (active) timer.start();
                else timer.stop();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // アンチエイリアシングの設定
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // フォントを設定する
        g.setFont(new Font("Serif", Font.BOLD, 96));

        // フォントメトリックスを取得する
        FontMetrics fm = g.getFontMetrics();

        // カウンタを表示する
        String str = "" + counter;
        Dimension d = getSize();
        int x = (d.width - fm.stringWidth(str)) / 2;
        g.drawString(str, x, d.height / 2);
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new Counter(), "Counter");
    }
}
