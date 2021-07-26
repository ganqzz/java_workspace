package demo.gui.misc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class LookAndFeelDemo extends JFrame implements ActionListener {

    public static void main(String[] args) {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();

        for (int i = 0; i < infos.length; i++) {
            System.out.println("getClassName: " + infos[i].getClassName() +
                               "getName: " + infos[i].getName());
        }

        System.out.println();
        // current
        LookAndFeel laf = UIManager.getLookAndFeel();
        System.out.println(laf.getName());

        LookAndFeelDemo frame = new LookAndFeelDemo();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 450, 300);
        frame.setTitle("Look & Feel");
        frame.setVisible(true);
    }

    LookAndFeelDemo() {

        JButton btn1 = new JButton("Metal");
        JButton btn2 = new JButton("CDE/Motif");
        JButton btn3 = new JButton("Windows");
        JButton btn4 = new JButton("WindowsClassic");
        JButton btn5 = new JButton("Nimbus");

        btn1.addActionListener(this);
        btn1.setActionCommand("javax.swing.plaf.metal.MetalLookAndFeel");
        btn2.addActionListener(this);
        btn2.setActionCommand("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        btn3.addActionListener(this);
        btn3.setActionCommand("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        btn4.addActionListener(this);
        btn4.setActionCommand("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        btn5.addActionListener(this);
        btn5.setActionCommand("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);

        String[] listData = {"Blue", "Green", "Red", "Whit", "Black", "Yellow"};
        JList<String> list = new JList<>(listData);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.getViewport().setView(list);
        scrollPane1.setPreferredSize(new Dimension(200, 80));

        JPanel listPanel = new JPanel();
        listPanel.add(scrollPane1);

        JCheckBox checkBox1 = new JCheckBox("JCheckBox1");
        JCheckBox checkBox2 = new JCheckBox("JCheckBox2", true);

        JPanel checkPanel = new JPanel();
        checkPanel.add(checkBox1);
        checkPanel.add(checkBox2);

        Vector<String> vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("JTree Node");
            sb.append(i);
            vector.add(new String(sb));
        }

        JTree tree = new JTree(vector);
        tree.setRootVisible(true);
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.getViewport().setView(tree);
        scrollPane2.setPreferredSize(new Dimension(200, 80));

        JPanel treePanel = new JPanel();
        treePanel.add(scrollPane2);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(listPanel);
        p.add(checkPanel);
        p.add(treePanel);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String lafClassName = e.getActionCommand();

        try {
            UIManager.setLookAndFeel(lafClassName);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.out.println("Error Look & Feel Setting");
        }
    }
}
