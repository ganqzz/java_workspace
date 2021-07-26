package demo.gui.components;

import demo.gui.Constants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JOptionPaneDemo extends JFrame implements ActionListener {

    private final JPanel buttonPanel;
    private final JLabel ansLabel;
    private static final String[] SELECTIONS = {
            "読書", "ドライブ", "映画", "スポーツ", "インターネット"};
    private final ImageIcon icon;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JOptionPaneDemo::new);
    }

    JOptionPaneDemo() {
        setBounds(100, 100, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Message Dialogs");

        icon = new ImageIcon(Constants.RESOURCE_DIR + "icon/smile.png");

        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.CENTER);

        createMessageButton("Error");
        createMessageButton("Info");
        createMessageButton("Warn");
        createMessageButton("Question");
        createMessageButton("Plain");

        createMessageButton("Selection");

        createMessageButton("InputText");
        createMessageButton("InputSelect");

        ansLabel = new JLabel();
        add(ansLabel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void createMessageButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setActionCommand(text);
        buttonPanel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int option = JOptionPane.CLOSED_OPTION;
        String value = null;
        boolean input = false;

        ansLabel.setText("");

        if (cmd.equals("Error")) {
            JOptionPane.showMessageDialog(this, "ERROR_MESSAGE", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } else if (cmd.equals("Info")) {
            JOptionPane.showMessageDialog(this, "INFORMATION_MESSAGE", "Info",
                                          JOptionPane.INFORMATION_MESSAGE);
        } else if (cmd.equals("Warn")) {
            JOptionPane.showMessageDialog(this, "WARNING_MESSAGE", "Warn",
                                          JOptionPane.WARNING_MESSAGE);
        } else if (cmd.equals("Question")) {
            JOptionPane.showMessageDialog(this, "QUESTION_MESSAGE", "Question",
                                          JOptionPane.QUESTION_MESSAGE);
        } else if (cmd.equals("Plain")) {
            JOptionPane.showMessageDialog(this, "PLAIN_MESSAGE", "Plain",
                                          JOptionPane.PLAIN_MESSAGE);
        } else if (cmd.equals("Selection")) {
            option = JOptionPane.showConfirmDialog(this, "Are you OK?", "Plain",
                                                   JOptionPane.YES_NO_CANCEL_OPTION);
        } else if (cmd.equals("InputText")) {
            input = true;
            value = JOptionPane.showInputDialog(this, "お名前は？",
                                                "名前を入力して下さい");
        } else if (cmd.equals("InputSelect")) {
            input = true;
            value = (String) JOptionPane
                    .showInputDialog(this, "", "休日の過ごし方", JOptionPane.INFORMATION_MESSAGE,
                                     icon, SELECTIONS, SELECTIONS[0]);
        }

        if (input) {
            ansLabel.setText(value);
        } else {
            if (option == JOptionPane.YES_OPTION) {
                ansLabel.setText("OK!");
            } else if (option == JOptionPane.NO_OPTION) {
                ansLabel.setText("Well...");
            } else if (option == JOptionPane.CANCEL_OPTION) {
                ansLabel.setText("Canceled");
            }
        }
    }
}
