package demo.gui.examples.analog_clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// AnalogClock
public class AnalogClock extends JFrame {

    AnalogClock() {
        final AnalogClockPanel acp = new AnalogClockPanel();

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウ×ボタン
        setResizable(false);
        setLocationRelativeTo(null); // center in the screen
        setUndecorated(true); // no Title bar
        setOpacity(0.9f); // foreground(child)を半透明化、 undecoratedとセット
        setBackground(new Color(0, 0, 0, 0));
        getContentPane().add(acp);
        //pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        // EventQueue.invokeLater()
        SwingUtilities.invokeLater(AnalogClock::new);
    }
}

class AnalogClockPanel extends JPanel implements ActionListener {

    public static final int SIZE = 400;

    private final AnalogClockBoard board;
    private final HourHand hourHand;
    private final MinuteHand minuteHand;
    private final SecondHand secondHand;
    private final Timer timer = new Timer(1000, this);
    private int second, minute, hour;
    private int cx, cy, r;

    AnalogClockPanel() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setSize(new Dimension(SIZE, SIZE)); // 暫定
        setMinimumSize(new Dimension(200, 200));
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false); // transparent

        // 最初の時刻取得（ex, eyのため）
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);

        board = new AnalogClockBoard(this);
        hourHand = new HourHand(this);
        minuteHand = new MinuteHand(this);
        secondHand = new SecondHand(this);
        System.out.println(getSize());
        resize();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
                repaint();
            }
        });
    }

    public void resize() {
        Dimension d = getSize();
        cx = d.width / 2;
        cy = d.height / 2;
        r = (int) (Math.min(d.width, d.height) * 0.45);

        board.resize();
        hourHand.resize();
        minuteHand.resize();
        secondHand.resize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 先にBackground色でクリアする
        Graphics2D g2d = (Graphics2D) g;
        // 図形や線のアンチエイリアシングの有効化
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        board.draw(g2d);
        hourHand.draw(g2d);
        minuteHand.draw(g2d);
        secondHand.draw(g2d);
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public int getR() {
        return r;
    }

    /**
     * parentにaddされるときの処理
     */
    @Override
    public void addNotify() {
        super.addNotify();
        System.out.println(getSize() + " in addNotify"); // ここでもadd後のsizeは決まらない
        // タイマー開始
        timer.start();
    }

    /**
     * parentにremoveされるときの処理
     */
    @Override
    public void removeNotify() {
        // タイマー終了
        timer.stop();
        super.removeNotify();
    }

    // タイマースレッドに載せる処理（UIスレッドではない）
    @Override
    public void actionPerformed(ActionEvent e) {
        // 時刻更新
        Calendar cal = Calendar.getInstance();
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);

        hourHand.refresh();
        minuteHand.refresh();
        secondHand.refresh();

        repaint();
    }
}
