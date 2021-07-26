package demo.gui.examples.table;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DemoTable {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runWindow();
            }
        });
    }

    private static void runWindow() {
        JFrame mainWindow = new JFrame("Table Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.05;

        JLabel label = new JLabel(" ");
        mainWindow.add(label, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        JTable table = new JTable(new AirLineTableData());
        JScrollPane scrollpane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table.setShowHorizontalLines(false);
        //table.setShowVerticalLines(false);
        //table.setRowSelectionAllowed(false);
        mainWindow.add(scrollpane, constraints);

        constraints.weightx = 0.05;
        constraints.weighty = 0.0;
        label = new JLabel(" ");
        mainWindow.add(label, constraints);

        constraints.weighty = 0.2;
        constraints.gridy = 2;
        label = new JLabel(" ");
        mainWindow.add(label, constraints);

        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
