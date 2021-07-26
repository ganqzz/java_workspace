package demo.gui.events;

import demo.gui.components.DemoBaseFrame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * MouseAdapter: MouseListener, MouseMotionListener, MouseWheelListener
 * MouseMotionAdapter: MouseMotionListener
 * 他にも各種~Listenerに対応する~Adapterクラスが存在する
 */
public class MouseAdapterDemo {

    private static JPanel createAdapterDemoPanel() {
        final JPanel panel = new JPanel();
        panel.setBackground(Color.green);

        MouseAdapter adapter = new MouseAdapter() {
            // 使うものだけOverrideすればよい

            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(Color.red);
                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panel.setBackground(Color.green);
                panel.repaint();
            }

            // MouseWheelEvent
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                System.out.println(e);
            }
        };
        panel.addMouseListener(adapter);
        panel.addMouseMotionListener(adapter);
        panel.addMouseWheelListener(adapter);

        return panel;
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(createAdapterDemoPanel());
    }
}
