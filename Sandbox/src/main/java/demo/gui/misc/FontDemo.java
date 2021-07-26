package demo.gui.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

public class FontDemo extends JFrame implements ActionListener {
    private int size = 18;

    FontDemo() {
        setTitle("Font Demo");
        setLocation(100, 100);
        //setBounds(100, 100, 300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        createMenu();
        createFontLabels();

        pack();
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Font Size");
        menubar.add(menu);

        JRadioButtonMenuItem small = new JRadioButtonMenuItem("Small");
        small.setActionCommand("12");
        small.addActionListener(this);
        menu.add(small);

        JRadioButtonMenuItem normal = new JRadioButtonMenuItem("Normal");
        normal.setActionCommand("18");
        normal.addActionListener(this);
        normal.setSelected(true);
        menu.add(normal);

        JRadioButtonMenuItem large = new JRadioButtonMenuItem("Large");
        large.setActionCommand("24");
        large.addActionListener(this);
        menu.add(large);

        JRadioButtonMenuItem huge = new JRadioButtonMenuItem("Huge");
        huge.setActionCommand("32");
        huge.addActionListener(this);
        menu.add(huge);

        ButtonGroup group = new ButtonGroup();
        group.add(small);
        group.add(normal);
        group.add(large);
        group.add(huge);

        setJMenuBar(menubar);
    }

    private void createFontLabels() {
        // Logical Fonts
        createLabel("あいうえお", new Font(Font.SERIF, Font.PLAIN, size));
        createLabel("あいうえお", new Font(Font.SANS_SERIF, Font.PLAIN, size));
        createLabel("あいうえお", new Font(Font.MONOSPACED, Font.PLAIN, size));
        createLabel("あいうえお", new Font(Font.DIALOG, Font.PLAIN, size));
        createLabel("あいうえお", new Font(Font.DIALOG_INPUT, Font.PLAIN, size));

        // Actual Fonts
        createLabel("Hello, World!", new Font("Arial", Font.BOLD | Font.ITALIC, size));
        createLabel("Hello, World!", new Font("Arial BOLD ITALIC", Font.PLAIN, size));
        createLabel("あいうえお", new Font("MS ゴシック", Font.PLAIN, size));
        createLabel("あいうえお", new Font("メイリオ", Font.PLAIN, size));
    }

    private void createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Stream.of(getContentPane().getComponents())
              .filter(c -> c instanceof JLabel)
              .forEach(c -> {
                  float size = Float.parseFloat(e.getActionCommand());
                  c.setFont(c.getFont().deriveFont(size)); // int: style, float: size, ...
                  this.size = (int) size;
              });

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FontDemo::new);
    }
}
