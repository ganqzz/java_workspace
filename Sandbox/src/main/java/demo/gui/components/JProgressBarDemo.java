package demo.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class JProgressBarDemo extends JFrame implements ActionListener {

    private Timer timer;
    private JButton startButton;
    private JButton stopButton;
    private JProgressBar bar;
    private JProgressBar vbar;
    private JLabel label;
    private int count;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JProgressBarDemo::new);
    }

    JProgressBarDemo() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ProgressBar");

        count = 0;
        label = new JLabel("Not Started", JLabel.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setActionCommand("start");

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        stopButton.setActionCommand("stop");
        stopButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        JPanel progressPanel = new JPanel();
        bar = new JProgressBar(0, 200);
        bar.setStringPainted(true);
        bar.setValue(0);
        progressPanel.add(bar);

        vbar = new JProgressBar(0, 200);
        vbar.setOrientation(JProgressBar.VERTICAL);
        vbar.setBorderPainted(false);
        vbar.setForeground(Color.RED);
        vbar.setValue(0);

        timer = new Timer(20, this);
        timer.setActionCommand("timer");

        getContentPane().add(label, BorderLayout.PAGE_START);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        getContentPane().add(progressPanel, BorderLayout.CENTER);
        getContentPane().add(vbar, BorderLayout.LINE_END);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("start")) {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            timer.start();
        } else if (cmd.equals("stop")) {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            timer.stop();
        } else if (cmd.equals("timer")) {
            label.setText(count + " count");
            if (count >= 200) {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                timer.stop();
                bar.setValue(count);
                vbar.setValue(count);
                count = 0;
            } else {
                count++;
                bar.setValue(count);
                vbar.setValue(count);
            }

            // 表示文字列をカスタマイズ
            int per = (int) (bar.getPercentComplete() * 100);
            bar.setString(per + "% 完了");
        }
    }
}
