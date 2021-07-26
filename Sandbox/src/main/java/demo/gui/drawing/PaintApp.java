package demo.gui.drawing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.MouseInputListener;

/**
 * Canvas demo
 * TODO: suppress flashing
 */
public class PaintApp {
    private static final int W_WIDTH = 800, W_HEIGHT = 600;
    private static final Map<String, Color> COLOR_MAP = new LinkedHashMap<>(); // 挿入順

    static {
        COLOR_MAP.put("BLACK", Color.BLACK);
        COLOR_MAP.put("RED", Color.RED);
        COLOR_MAP.put("BLUE", Color.BLUE);
        COLOR_MAP.put("GREEN", Color.GREEN);
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        // JFrameのインスタンスを生成
        JFrame frame = new JFrame("お絵かきアプリ");
        // ウィンドウを閉じたらプログラムを終了する
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウのサイズ・初期位置
        frame.setSize(W_WIDTH, W_HEIGHT);
        frame.setLocationRelativeTo(null);

        // PaintCanvas
        final PaintCanvas canvas = new PaintCanvas();
        canvas.setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        // コントロールパネル
        frame.getContentPane().add(createControlPanel(canvas), BorderLayout.NORTH);

        // ウィンドウを表示
        frame.setVisible(true);
    }

    private static JPanel createControlPanel(PaintCanvas canvas) {
        JPanel cp = new JPanel();

        // 全消去
        JButton clear = new JButton("CLEAR");
        clear.addActionListener(e -> canvas.clear());
        cp.add(clear);

        // 線の太さ調節
        JSlider slider = new JSlider(1, 50, 1); // 最小値、最大値、初期値
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int fps = source.getValue();
            canvas.setStroke(fps);
        });
        cp.add(slider);

        // 線の色変更
        JComboBox<String> combo = new JComboBox<>(new Vector<>(COLOR_MAP.keySet()));
        combo.addActionListener(e -> {
            JComboBox source = (JComboBox) e.getSource();
            String color = (String) source.getSelectedItem();
            canvas.setColorCombo(color);
        });
        cp.add(combo);

        return cp;
    }

    // キャンバスクラス
    static class PaintCanvas extends Canvas implements MouseInputListener {

        // 描画内容を保持するBufferedImage
        private BufferedImage cImage = null;
        // cImageに描画するためのインスタンス
        private Graphics2D g2d;

        // 線の開始座標・終了座標
        private int x, y, xx, yy;
        // 描画モード・消しゴムモード
        private int type;
        // 線の太さ
        public int width = 1;
        // 線の色
        public Color color = Color.black;

        public PaintCanvas() {
            // 座標を初期化
            x = -1;
            y = -1;
            xx = -1;
            yy = -1;
            type = 0;

            // MouseListener・MouseMotionListenerを設定
            addMouseListener(this);
            addMouseMotionListener(this);

            // キャンバスの背景を白に設定
            setBackground(Color.white);
            // 描画内容を保持するBufferedImageを生成
            cImage = new BufferedImage(W_WIDTH, W_HEIGHT, BufferedImage.TYPE_INT_RGB);
            g2d = (Graphics2D) cImage.getGraphics();
            // 図形や線のアンチエイリアシングの有効化
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            // BufferedImageの背景も白にする
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, W_WIDTH, W_HEIGHT);

            // 描画
            repaint();
        }

        // キャンバスをクリア
        public void clear() {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, W_WIDTH, W_HEIGHT);
            repaint();
        }

        // 線の太さ変更
        public void setStroke(int n) {
            width = n;
        }

        // 線の色変更
        public void setColorCombo(String color) {
            this.color = COLOR_MAP.getOrDefault(color, this.color);
        }

        @Override
        public void paint(Graphics g) {
            // BufferedImageをキャンバスに反映
            g.drawImage(cImage, 0, 0, null);
        }

        private void refreshImage() {
            // 描画
            if (x >= 0 && y >= 0 && xx >= 0 && yy >= 0) {
                int width = this.width;
                Color c = this.color;
                if (type == 1) { // 通常モード
                    //
                } else if (type == 2) { // 消しゴムモード
                    width = 50;
                    c = Color.WHITE;
                }

                BasicStroke stroke = new BasicStroke(width,
                                                     BasicStroke.CAP_ROUND,
                                                     BasicStroke.JOIN_ROUND);
                g2d.setStroke(stroke);
                g2d.setColor(c);
                g2d.drawLine(xx, yy, x, y);
            }

            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 過去の座標を開始座標に設定
            xx = x;
            yy = y;

            // 新しい座標を終了座標に設定
            Point point = e.getPoint();
            x = point.x;
            y = point.y;

            // 再描画（BufferedImage更新）
            refreshImage();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // 押されているボタンを検知
            switch (e.getButton()) {
                case MouseEvent.BUTTON1: // left
                    type = 1;
                    break;
                case MouseEvent.BUTTON3: // right
                    type = 2;
                    break;
            }

            Point point = e.getPoint();
            x = point.x;
            y = point.y;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // ドラッグが終了したら座標を初期化
            x = -1;
            y = -1;
            xx = -1;
            yy = -1;
            type = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
