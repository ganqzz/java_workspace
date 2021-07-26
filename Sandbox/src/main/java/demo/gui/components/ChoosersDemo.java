package demo.gui.components;

import demo.gui.Constants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Chooser Demo
 */
public class ChoosersDemo extends JFrame implements ActionListener {

    private final JPanel main;
    private final JLabel status;

    public ChoosersDemo() {
        setTitle("Toolbars");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createToolBar();

        main = new JPanel();
        main.setBackground(Color.WHITE);
        add(main, BorderLayout.CENTER);

        status = new JLabel();
        status.setPreferredSize(new Dimension(100, 20));
        add(status, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar();

        ImageIcon newIcon = new ImageIcon(Constants.RESOURCE_DIR + "icon/new.png");
        ImageIcon openIcon = new ImageIcon(Constants.RESOURCE_DIR + "icon/open.png");
        ImageIcon saveIcon = new ImageIcon(Constants.RESOURCE_DIR + "icon/save.png");
        ImageIcon paletteIcon = new ImageIcon(Constants.RESOURCE_DIR + "icon/palette.png");

        JButton newBtn = new JButton("New", newIcon);
        newBtn.addActionListener(this);
        JButton openBtn = new JButton("Open", openIcon);
        openBtn.addActionListener(this);
        JButton saveBtn = new JButton("Save", saveIcon);
        saveBtn.addActionListener(this);
        JButton paletteBtn = new JButton("Color", paletteIcon);
        paletteBtn.addActionListener(this);

        toolBar.add(newBtn);
        toolBar.add(openBtn);
        toolBar.add(saveBtn);
        toolBar.add(paletteBtn);

        add(toolBar, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        // ColorChooser
        if (cmd.equals("Color")) {
            Color color = JColorChooser.showDialog(this, "色の選択", Color.WHITE);
            if (color != null) {
                main.setBackground(color);
            }
            return;
        }

        // FileChooser
        JFileChooser fileChooser = new JFileChooser();
        int selected;

        if (cmd.equals("New")) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            selected = fileChooser.showOpenDialog(this);
        } else if (cmd.equals("Open")) {
            selected = fileChooser.showOpenDialog(this);
        } else if (cmd.equals("Save")) {
            selected = fileChooser.showSaveDialog(this);
        } else {
            selected = JFileChooser.CANCEL_OPTION;
        }

        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            status.setText(file.getName());
        } else if (selected == JFileChooser.CANCEL_OPTION) {
            status.setText("キャンセルされました");
        } else if (selected == JFileChooser.ERROR_OPTION) {
            status.setText("エラー又は取消しがありました");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(ChoosersDemo::new);
    }
}
