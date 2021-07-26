package demo.gui.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MenuDemo extends JFrame implements ActionListener {
    private JLabel label;

    public static void main(String[] args) {
        MenuDemo frame = new MenuDemo("Menu Demo");
        frame.setVisible(true);
    }

    MenuDemo(String title) {
        setTitle(title);
        setBounds(100, 100, 300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Tools");
        JMenu menu4 = new JMenu("Help");

        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(menu4);

        JMenu submenu = new JMenu("Encode");

        JMenuItem submenuitem1 = new JMenuItem("UTF-8");
        JMenuItem submenuitem2 = new JMenuItem("Shift_JIS");
        JMenuItem submenuitem3 = new JMenuItem("EUC");
        submenuitem1.addActionListener(e -> label.setText(submenuitem1.getText()));
        submenuitem2.addActionListener(e -> label.setText(submenuitem2.getText()));
        submenuitem3.addActionListener(e -> label.setText(submenuitem3.getText()));

        submenu.add(submenuitem1);
        submenu.add(submenuitem2);
        submenu.add(submenuitem3);

        JMenuItem menuitem1_1 = new JMenuItem("New");
        menuitem1_1.setMnemonic(KeyEvent.VK_N);
        menuitem1_1.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menuitem1_1.addActionListener(this);

        JMenuItem menuitem1_2 = new JMenuItem("Open");
        menuitem1_2.setMnemonic(KeyEvent.VK_O);
        menuitem1_2.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuitem1_2.addActionListener(this);

        JMenuItem menuitem1_3 = new JMenuItem("Save");
        menuitem1_3.setMnemonic(KeyEvent.VK_S);
        menuitem1_1.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuitem1_3.addActionListener(this);

        JMenuItem menuitem1_4 = new JMenuItem("Close(X)");
        menuitem1_4.setMnemonic(KeyEvent.VK_X);
        menuitem1_3.setActionCommand("Close"); // textと違う場合に指定する
        menuitem1_4.addActionListener(
                e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        menu1.add(menuitem1_1);
        menu1.add(menuitem1_2);
        menu1.addSeparator();
        menu1.add(submenu);
        menu1.addSeparator();
        menu1.add(menuitem1_3);
        menu1.add(menuitem1_4);

        JCheckBoxMenuItem checkmenuitem1 = new JCheckBoxMenuItem("Auto Save");
        JCheckBoxMenuItem checkmenuitem2 = new JCheckBoxMenuItem("Auto Check");
        checkmenuitem1.setSelected(true);

        JRadioButtonMenuItem radiomenuitem1 = new JRadioButtonMenuItem("Auto Save");
        JRadioButtonMenuItem radiomenuitem2 = new JRadioButtonMenuItem("Auto Check");

        ButtonGroup group = new ButtonGroup();
        group.add(radiomenuitem1);
        group.add(radiomenuitem2);

        menu3.add(checkmenuitem1);
        menu3.add(checkmenuitem2);
        menu3.addSeparator();
        menu3.add(radiomenuitem1);
        menu3.add(radiomenuitem2);

        menu4.add("Help");
        menu4.addSeparator();
        menu4.add("About");

        setJMenuBar(menubar);

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        getContentPane().add(label, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText(e.getActionCommand());
    }
}
