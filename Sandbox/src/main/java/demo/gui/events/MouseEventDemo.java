package demo.gui.events;

import demo.gui.components.DemoBaseFrame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

public class MouseEventDemo extends JPanel
        implements MouseListener, MouseMotionListener, MouseWheelListener {

    MouseEventDemo() {
        // リスナーの登録
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    // 押した時と放した時のPointが違う場合（=Drag）は発生しない
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked: " + e);
        setBackground(Color.blue);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.green);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.red);
        repaint();
    }

    // mouseClickedとのバッティングに注意
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed: " + e.getPoint());
        setBackground(Color.white);
        repaint();
    }

    // mouseClickedとのバッティングに注意
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released: " + e.getPoint());
        setBackground(Color.yellow);
        repaint();
    }


    // MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged: " + e.getPoint());
    }

    // MouseMotionListener
    // Draggedとは被らない
    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Moved: " + e.getPoint());
    }

    // MouseWheelListener
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("WheelMoved: " + e);
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new MouseEventDemo());
    }
}
