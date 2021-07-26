package demo.gui.components;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * デモ用Window
 */
public class DemoBaseFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void start(final Component component) {
        start(component, "Demo");
    }

    public static void start(final Component component, final String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setBounds(100, 100, 400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(component);
            //frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    public static void start2(final Component top, final Component bottom, final String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setBounds(100, 100, 400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(top, BorderLayout.PAGE_START);
            frame.getContentPane().add(bottom, BorderLayout.PAGE_END);
            //frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    public static void start3(final Component top, final Component bottom, final Component center,
                              final String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setBounds(100, 100, 400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(top, BorderLayout.PAGE_START);
            frame.getContentPane().add(bottom, BorderLayout.PAGE_END);
            frame.getContentPane().add(center, BorderLayout.CENTER);
            //frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    // テスト
    public static void main(String[] args) {
        start(new JButton("DemoBaseWindow Test"));
    }
}
