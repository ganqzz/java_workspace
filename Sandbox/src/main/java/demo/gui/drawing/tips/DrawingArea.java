package demo.gui.drawing.tips;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

abstract class DrawingArea extends JPanel {
    protected final static int AREA_SIZE = 400;
    protected Rectangle shape;

    public DrawingArea() {
        setBackground(Color.WHITE);

        MouseAdapter ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        setPreferredSize(new Dimension(AREA_SIZE, AREA_SIZE));
    }

    public abstract void addRectangle(Rectangle rectangle, Color color);

    public abstract void clear();

    private class MyMouseListener extends MouseAdapter {
        private Point startPoint;

        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
            shape = new Rectangle();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = Math.min(startPoint.x, e.getX());
            int y = Math.min(startPoint.y, e.getY());
            int width = Math.abs(startPoint.x - e.getX());
            int height = Math.abs(startPoint.y - e.getY());

            shape.setBounds(x, y, width, height);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (shape.width != 0 || shape.height != 0) {
                addRectangle(shape, e.getComponent().getForeground());
            }

            shape = null;
        }
    }

    public static void start(DrawingArea drawingArea, String title) {
        SwingUtilities.invokeLater(() -> {
            ButtonPanel buttonPanel = new ButtonPanel(drawingArea);

            //JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(buttonPanel);
            frame.getContentPane().add(drawingArea, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
