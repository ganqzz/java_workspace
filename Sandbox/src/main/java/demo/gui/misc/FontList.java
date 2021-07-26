package demo.gui.misc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class FontList extends JPanel {

    FontList() {
        setPreferredSize(null);
        setLayout(new GridLayout(1, 2));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
        JList<String> fontFamilyList = new JList<>(fontFamilyNames);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.getViewport().setView(fontFamilyList);
        scrollPane1.setPreferredSize(new Dimension(300, 300));

        JPanel p1 = new JPanel();
        p1.add(scrollPane1);

        // style
        Font[] fonts = ge.getAllFonts();
        Vector<String> fontNames = new Vector<>();

        for (int i = 0; i < fonts.length; i++) {
            fontNames.addElement(fonts[i].getName());
        }
        JList<String> fontList = new JList<>(fontNames);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.getViewport().setView(fontList);
        scrollPane2.setPreferredSize(new Dimension(300, 300));

        JPanel p2 = new JPanel();
        p2.add(scrollPane2);

        add(p1);
        add(p2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("System Font List");
            frame.setLocation(100, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new FontList());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
