package breakout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class Breakout extends JFrame {

    public Breakout() {
        initUI();
    }

    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //setLayout(new FlowLayout());
        add(new Board());
        pack();
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Breakout game = new Breakout();
            game.setVisible(true);
        });
    }
}
