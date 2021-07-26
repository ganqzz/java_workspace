package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Java Minesweeper Game
 */
public class Minesweeper extends JFrame {

    private JLabel statusbar;

    public Mines() {
        initUI();
    }

    private void initUI() {
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        statusbar = new JLabel();
        statusbar.setBorder(BorderFactory.createLineBorder(Color.black));
        add(new Board(statusbar));
        add(statusbar, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Minesweeper ex = new Minesweeper();
            ex.setVisible(true);
        });
    }
}
