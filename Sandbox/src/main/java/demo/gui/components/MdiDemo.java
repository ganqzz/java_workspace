package demo.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MdiDemo extends JFrame {

    private static final int WIN_WIDTH = 640;
    private static final int WIN_HEIGHT = 480;

    private JDesktopPane dp;

    MdiDemo() {
        setTitle("MDI Demo");

        dp = new JDesktopPane();
        getContentPane().add(dp);

        createMenu();
    }

    private void createMenu() {
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);

        JMenu mnuFile = new JMenu("File");
        jmb.add(mnuFile);

        // add an exit item with no frame
        JMenuItem mnuItemExit = new JMenuItem("Exit");
        mnuItemExit.addActionListener(arg0 -> {
            // exit
            int close = JOptionPane
                    .showConfirmDialog(null,
                                       "Are you sure you want to exit?",
                                       "End Application?",
                                       JOptionPane.YES_NO_OPTION,
                                       JOptionPane.INFORMATION_MESSAGE);
            if (close == 1) return;
            System.exit(0);
        });
        mnuFile.add(mnuItemExit);

        JMenu mnitemIFs = new JMenu("Internal Frames");
        jmb.add(mnitemIFs);

        // add the two menu items
        JMenuItem mnuJIFOne = new JMenuItem("Frame One");
        mnuJIFOne.addActionListener(e -> dp.add(newInternalFrame(newFlowPanel())));
        mnitemIFs.add(mnuJIFOne);

        JMenuItem mnuJIFTwo = new JMenuItem("Frame Two");
        mnuJIFTwo.addActionListener(e -> dp.add(newInternalFrame(newBorderPanel())));
        mnitemIFs.add(mnuJIFTwo);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MdiDemo window = new MdiDemo();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(100, 100, WIN_WIDTH + 20, WIN_HEIGHT + 60);
            window.setVisible(true);
        });
    }

    private static JInternalFrame newInternalFrame(Component component) {
        JInternalFrame frame = new JInternalFrame();
        frame.setResizable(true);
        frame.setClosable(true);
        frame.setMaximizable(true);
        frame.setIconifiable(true);
        frame.setBackground(Color.WHITE);
        frame.add(component);
        frame.pack();
        frame.setVisible(true); // Frameと同じく必要
        return frame;
    }

    private static JPanel newFlowPanel() {
        JPanel panel = new JPanel();
        for (int i = 1; i <= 5; i++) {
            panel.add(new JButton("Button " + i));
        }
        return panel;
    }

    private static JPanel newBorderPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setLayout(new BorderLayout());
        panel.add(new JButton("Top"), BorderLayout.NORTH);
        panel.add(new JButton("Right"), BorderLayout.EAST);
        panel.add(new JButton("Bottom"), BorderLayout.SOUTH);
        panel.add(new JButton("Left"), BorderLayout.WEST);
        panel.add(new JButton("Center"), BorderLayout.CENTER);
        return panel;
    }
}
