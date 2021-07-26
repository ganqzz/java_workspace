package demo.gui.events;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ActionEventModifiersDemo extends JFrame implements ActionListener {
    JLabel label;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new ActionEventModifiersDemo("ActionEvent"));
    }

    ActionEventModifiersDemo(String title) {
        setTitle(title);
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        JButton btn = new JButton("Push!");
        btn.addActionListener(this);

        JPanel p = new JPanel();
        p.add(btn);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(label, BorderLayout.PAGE_END);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int modifiers = e.getModifiers();

        StringBuffer sb = new StringBuffer();
        sb.append("ボタンクリック ");
        if ((modifiers & ActionEvent.ALT_MASK) == ActionEvent.ALT_MASK) {
            sb.append("ALT ");
        }

        if ((modifiers & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
            sb.append("CTRL ");
        }

        if ((modifiers & ActionEvent.META_MASK) == ActionEvent.META_MASK) {
            sb.append("META ");
        }

        if ((modifiers & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
            sb.append("SHIFT");
        }

        label.setText(new String(sb));
    }
}
