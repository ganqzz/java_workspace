package demo.gui.layouts;

import demo.gui.components.DemoBaseFrame;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SpringLayoutDemo extends JPanel {

    SpringLayoutDemo() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JButton button1 = new JButton("Apple");
        JButton button2 = new JButton("Orange");
        button2.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        JButton button3 = new JButton("Strawberry");

        layout.putConstraint(SpringLayout.NORTH, button1, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, button1, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, button2, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, button2, 10, SpringLayout.EAST, button1);

        layout.putConstraint(SpringLayout.NORTH, button3, 10, SpringLayout.SOUTH, button2);
        layout.putConstraint(SpringLayout.WEST, button3, -20, SpringLayout.WEST, button2);

        add(button1);
        add(button2);
        add(button3);
    }

    public static void main(String[] args) {
        DemoBaseFrame.start(new SpringLayoutDemo());
    }
}
