package demo.gui.events;

import demo.gui.components.DemoBaseFrame;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JTextComponentEventDemo extends JPanel {

    private JLabel label;

    JTextComponentEventDemo() {
        setLayout(new FlowLayout());
        label = new JLabel("Event type");
        JTextArea ta = new JTextArea(10, 20);
        ta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                label.setText("changedUpdate");
                System.out.println(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                label.setText("insertUpdate");
                System.out.println(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                label.setText("removeUpdate");
                System.out.println(e);
            }
        });
        add(label);
        add(ta);
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new JTextComponentEventDemo());
    }
}
