package demo.gui.events;

import demo.gui.components.DemoBaseFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActionEventDemo extends JPanel implements ActionListener {
    private JLabel label;

    ActionEventDemo() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton b1 = new JButton("Apple");
        b1.setActionCommand("** Apple **"); // default equals to text
        b1.addActionListener(this);
        add(b1);
        JButton b2 = new JButton("Banana");
        b2.addActionListener(this);
        add(b2);
        JButton b3 = new JButton("Orange");
        b3.addActionListener(this);
        add(b3);

        label = new JLabel();
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText(e.getActionCommand());

        DateFormat df =
                DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        System.out.println("when: " + df.format(new Date(e.getWhen())));
        System.out.println("source: " + e.getSource());
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new ActionEventDemo());
    }
}
