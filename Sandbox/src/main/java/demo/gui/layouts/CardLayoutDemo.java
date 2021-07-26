package demo.gui.layouts;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CardLayoutDemo extends JFrame implements ActionListener {

    private JPanel cardPanel;
    private CardLayout layout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CardLayoutDemo::new);
    }

    CardLayoutDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("CardLayout");

        createCardPanel();
        createControlPanels();

        pack();
        setVisible(true);
    }

    private void createCardPanel() {
        cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(400, 200));
        //cardPanel.setPreferredSize(null);
        cardPanel.setBackground(Color.WHITE);
        layout = new CardLayout(20, 10);
        cardPanel.setLayout(layout);

        /* カード1 */
        JPanel card1 = new JPanel();
        card1.setBackground(Color.BLUE);
        card1.add(new JButton("Card1 Button"));

        /* カード2 */
        JPanel card2 = new JPanel();
        card2.setBackground(Color.GREEN);
        card2.add(new JLabel("Card2 Label"));
        card2.add(new JTextField("Card2", 10));

        /* カード3 */
        JPanel card3 = new JPanel();
        card3.setBackground(Color.YELLOW);
        card3.add(new JCheckBox("Card3 CheckBox1"));
        card3.add(new JCheckBox("Card3 CheckBox2"));

        cardPanel.add(card1, "Card1");
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");

        getContentPane().add(cardPanel);
    }

    private void createControlPanels() {
        JPanel btnPanel1 = new JPanel();
        btnPanel1.setPreferredSize(null);
        JButton firstButton = new JButton("First");
        firstButton.addActionListener(this);

        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(this);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        JButton lastButton = new JButton("Last");
        lastButton.addActionListener(this);

        btnPanel1.add(firstButton);
        btnPanel1.add(prevButton);
        btnPanel1.add(nextButton);
        btnPanel1.add(lastButton);

        JPanel btnPanel2 = new JPanel();
        btnPanel1.setPreferredSize(null);
        JButton button1 = new JButton("Card1");
        button1.addActionListener(this);

        JButton button2 = new JButton("Card2");
        button2.addActionListener(this);

        JButton button3 = new JButton("Card3");
        button3.addActionListener(this);

        btnPanel2.add(button1);
        btnPanel2.add(button2);
        btnPanel2.add(button3);

        getContentPane().add(btnPanel1);
        getContentPane().add(btnPanel2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "First":
                layout.first(cardPanel);
                break;
            case "Last":
                layout.last(cardPanel);
                break;
            case "Next":
                layout.next(cardPanel);
                break;
            case "Prev":
                layout.previous(cardPanel);
                break;
            default:
                layout.show(cardPanel, cmd);
                break;
        }
    }
}
