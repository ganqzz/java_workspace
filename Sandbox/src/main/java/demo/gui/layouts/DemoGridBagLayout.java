package demo.gui.layouts;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DemoGridBagLayout {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runWindow1();
                runWindow2();
            }
        });
    }

    private static void runWindow1() {
        JFrame gridWindow = new JFrame("Grid Bag Layout - Defaults");
        gridWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gridWindow.setLayout(new GridBagLayout());
        Container pane = gridWindow.getContentPane();

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.ipadx = 5;
        constraints.ipady = 5;
        constraints.insets = new Insets(5, 3, 5, 3);

        JLabel label = new JLabel(" ");
        pane.add(label, constraints);

        label = new JLabel(" ");
        pane.add(label, constraints);

        label = new JLabel("First Name");
        pane.add(label, constraints);

        label = new JLabel(" ");
        pane.add(label, constraints);

        JTextField firstName = new JTextField(15);
        pane.add(firstName, constraints);

        label = new JLabel(" ");
        constraints.gridy = 1;
        pane.add(label, constraints);

        label = new JLabel(" ");
        constraints.gridy = 2;
        pane.add(label, constraints);

        label = new JLabel(" ");
        pane.add(label, constraints);

        label = new JLabel("Last Name");
        pane.add(label, constraints);

        label = new JLabel(" ");
        pane.add(label, constraints);

        JTextField lastName = new JTextField(15);
        constraints.gridx = 1;
        pane.add(lastName, constraints);

        label = new JLabel(" ");
        pane.add(label, constraints);

        label = new JLabel(" ");
        constraints.gridy = 3;
        pane.add(label, constraints);

        gridWindow.pack();
        gridWindow.setVisible(true);
    }

    private static void runWindow2() {
        JFrame gridWindow = new JFrame("Grid Bag Layout - Custom");
        gridWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gridWindow.setLayout(new GridBagLayout());
        Container pane = gridWindow.getContentPane();

        GridBagConstraints emptyLineConstraints = new GridBagConstraints();
        GridBagConstraints basicConstraint = new GridBagConstraints();

        emptyLineConstraints.fill = GridBagConstraints.BOTH; // default NONE
        emptyLineConstraints.anchor = GridBagConstraints.LINE_START; // default CENTER
        emptyLineConstraints.gridheight = 1; // default = 1
        emptyLineConstraints.gridwidth = GridBagConstraints.REMAINDER; // default = 1
        emptyLineConstraints.weightx = 1.0; // default 0
        emptyLineConstraints.weighty = 1.0; // default 0
        emptyLineConstraints.gridx = GridBagConstraints.RELATIVE; // default RELATIVE
        emptyLineConstraints.gridy = GridBagConstraints.RELATIVE; // default RELATIVE

        JLabel label = new JLabel(" ");
        emptyLineConstraints.gridy = 0;
        emptyLineConstraints.fill = GridBagConstraints.HORIZONTAL;
        emptyLineConstraints.weighty = 0.0;
        pane.add(label, emptyLineConstraints);

        basicConstraint.fill = GridBagConstraints.NONE;
        basicConstraint.anchor = GridBagConstraints.LINE_START;
        basicConstraint.gridheight = 1;
        basicConstraint.gridwidth = GridBagConstraints.RELATIVE;
        basicConstraint.weightx = 0.0;
        basicConstraint.weighty = 0.0;
        basicConstraint.gridx = 2;
        basicConstraint.gridy = 2;
        basicConstraint.ipadx = 0;
        basicConstraint.ipady = 0;
        basicConstraint.insets = new Insets(0, 0, 0, 0);

        label = new JLabel(" ");
        basicConstraint.gridx = 1;
        basicConstraint.gridwidth = 1;
        basicConstraint.weightx = 0.5;
        pane.add(label, basicConstraint);

        label = new JLabel("First Name");
        basicConstraint.gridx = 2;
        basicConstraint.weightx = 0.0;
        pane.add(label, basicConstraint);

        label = new JLabel(" ");
        basicConstraint.gridx = 3;
        basicConstraint.weightx = 0.0;
        pane.add(label, basicConstraint);

        JTextField firstName = new JTextField(15);
        basicConstraint.gridx = 4;
        basicConstraint.weightx = 0.0;
        basicConstraint.fill = GridBagConstraints.HORIZONTAL;
        pane.add(firstName, basicConstraint);

        label = new JLabel(" ");
        basicConstraint.gridx = 5;
        basicConstraint.weightx = 0.5;
        pane.add(label, basicConstraint);

        label = new JLabel(" ");
        emptyLineConstraints.weighty = 0.0;
        emptyLineConstraints.gridy = 3;
        pane.add(label, emptyLineConstraints);

        label = new JLabel(" ");
        basicConstraint.fill = GridBagConstraints.NONE;
        basicConstraint.gridx = 1;
        basicConstraint.gridy = 4;
        basicConstraint.weightx = 0.0;
        pane.add(label, basicConstraint);

        label = new JLabel("Last Name");
        basicConstraint.gridx = 2;
        pane.add(label, basicConstraint);

        label = new JLabel(" ");
        basicConstraint.gridx = 3;
        basicConstraint.weightx = 0.0;
        pane.add(label, basicConstraint);

        JTextField lastName = new JTextField(15);
        basicConstraint.gridx = 4;
        basicConstraint.weightx = 1.0;
        basicConstraint.fill = GridBagConstraints.NONE;
        pane.add(lastName, basicConstraint);

        label = new JLabel(" ");
        basicConstraint.gridx = 5;
        basicConstraint.weightx = 0.5;
        pane.add(label, basicConstraint);

        label = new JLabel(" ");
        emptyLineConstraints.weighty = 1.0;
        emptyLineConstraints.gridy = 5;
        pane.add(label, emptyLineConstraints);

        gridWindow.pack();
        gridWindow.setVisible(true);
    }
}
