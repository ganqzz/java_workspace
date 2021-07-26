package demo.gui.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Dots extends JPanel implements Runnable {
    private static final Color[] COLORS = {
            Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray,
            Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink,
            Color.red, Color.white, Color.yellow};

    Dots() {
        setBackground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            // スレッドを開始する
            new Thread(this).start();
        }
    }

    // UIスレッドは1個だけなので、この様に処理が再描画依頼だけの場合は、マルチスレッドである必要はない
    @Override
    public void run() {
        try {
            while (true) {
                // 次の点を表示する前に休止する
                Thread.sleep(1);
                // 再描画を要求する
                repaint();
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 塗り直しをしない様にコメントアウト（安直なやり方）
        //super.paintComponent(g);

        // ウィンドウ内の無作為な場所を選択する
        Dimension d = getSize();
        int x = (int) (Math.random() * d.width);
        int y = (int) (Math.random() * d.height);
        int c = (int) (Math.random() * COLORS.length);

        // そこに点を描画する
        g.setColor(COLORS[c]);
        g.fillRect(x, y, 8, 8);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dots Dots Dots");
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(Color.WHITE);
            frame.getContentPane().add(new Dots());
            frame.setVisible(true);
        });
    }
}
