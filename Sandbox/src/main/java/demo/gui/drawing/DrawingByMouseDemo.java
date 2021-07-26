package demo.gui.drawing;

import demo.gui.components.DemoBaseFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class DrawingByMouseDemo extends JPanel implements MouseInputListener {
    // MouseInputListenerは、MouseListener, MouseMotionListenerを継承している。

    private Point p;
    private Color c = Color.BLACK;
    private Point center = new Point(); // 中心

    DrawingByMouseDemo() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // unused
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: // left
                c = Color.RED;
                break;
            case MouseEvent.BUTTON2: // middle
                c = Color.GREEN;
                break;
            case MouseEvent.BUTTON3: // right
                c = Color.BLUE;
                break;
        }
        p = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        p = null;
    }

    // MouseMotion
    @Override
    public void mouseDragged(MouseEvent e) {
        p = e.getPoint();
        repaint();
    }

    // MouseMotion
    @Override
    public void mouseMoved(MouseEvent e) {
        // unused
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 図形や線のアンチエイリアシングの有効化
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // stroke
        g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Dimension d = getSize();
        center.move(d.width / 2, d.height / 2); // resizeイベントだと最初のsizeが0x0になってしまうため、ここで毎回取得

        if (p != null) {
            g.setColor(Color.BLACK);
            g.drawLine(center.x, center.y, p.x, p.y);

            int r = (int) center.distance(p); // 半径
            int r2 = r * 2;
            g.setColor(c);
            g.drawOval(center.x - r, center.y - r, r2, r2);
        }
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new DrawingByMouseDemo());
    }
}
