package demo.gui.components;

import demo.gui.Constants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JScrollPaneDemo extends JFrame implements ChangeListener {

    private JViewport viewport;
    private JLabel positionLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JScrollPaneDemo::new);
    }

    JScrollPaneDemo() {
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JScrollPane");

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 200));

        viewport = scrollPane.getViewport();

        ImageIcon icon = new ImageIcon(Constants.RESOURCE_DIR + "bg.png");
        JLabel label = new JLabel(icon);
        viewport.setView(label);

        viewport.addChangeListener(this);

        positionLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.add(scrollPane);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(positionLabel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Point point = viewport.getViewPosition();
        positionLabel.setText("x: " + point.x + ", y: " + point.y);
    }
}
