package demo.gui.layouts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;

public class LayoutExamples extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final Map<String, Component> demos = new LinkedHashMap<>();
    private String current; // command

    LayoutExamples() {
        setLocation(100, 100);
        setSize(new Dimension(200, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Layout Demo");

        createDemo();
        createMenu();

        getContentPane().add(new Label("Start", Label.CENTER));
        setVisible(true);
    }

    private void createDemo() {
        demos.put("NullLayout", new NullLayoutExample());
        demos.put("BorderLayout", new BorderLayoutExample());
        demos.put("BoxLayout", new BoxLayoutExample());
        demos.put("CardLayout", new CardLayoutExample());
        demos.put("FlowLayout", new FlowLayoutExample());
        demos.put("GridLayout", new GridLayoutExample());
    }

    private void createMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Demo");
        menubar.add(menu);
        for (String command : demos.keySet()) {
            menu.add(createMenuItem(command));
        }
        setJMenuBar(menubar);
    }

    private JMenuItem createMenuItem(String command) {
        JMenuItem item = new JMenuItem(command);
        item.addActionListener(this);
        return item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selected = e.getActionCommand();
        System.out.println(selected);
        if (!selected.equals(current)) {
            //getContentPane().remove(
            //        ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER));
            current = selected;
            setTitle(current);
            getContentPane().removeAll();
            getContentPane().add(demos.get(current));
            //validate(); // important
            pack(); // calls validate() internally
        }
    }

    private static class NullLayoutExample extends JPanel {
        NullLayoutExample() {
            setPreferredSize(new Dimension(300, 200));

            setLayout(null); //

            JButton button1 = new JButton("Apple");
            button1.setBounds(10, 10, 80, 30);

            JButton button2 = new JButton("Orange");
            button2.setBounds(30, 30, 80, 30);

            JButton button3 = new JButton("Strawberry");
            button3.setBounds(50, 50, 80, 30);

            add(button1);
            add(button2);
            add(button3);
        }
    }

    private static class BorderLayoutExample extends JPanel {
        BorderLayoutExample() {
            setLayout(new BorderLayout(5, 5));
            add(new JButton("Top"), BorderLayout.PAGE_START); // NORTH
            add(new JButton("Right"), BorderLayout.LINE_END); // EAST
            add(new JButton("Bottom"), BorderLayout.PAGE_END); // SOUTH
            add(new JButton("Left"), BorderLayout.LINE_START); // WEST
            add(new JButton("Center"), BorderLayout.CENTER);
        }
    }

    private static class BoxLayoutExample extends JPanel {
        BoxLayoutExample() {
            setPreferredSize(new Dimension(100, 300));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(Box.createVerticalGlue());
            for (int i = 1; i <= 5; i++) {
                add(Box.createVerticalStrut(5));
                add(new JButton("Button " + i));
            }
        }
    }

    private static class CardLayoutExample extends JPanel implements ItemListener {
        private static final String CARD_ONE = "Card One";
        private static final String CARD_TWO = "Card Two";
        JPanel panelCards;

        CardLayoutExample() {
            Vector<String> cardDetails = new Vector<>();
            cardDetails.add(CARD_ONE);
            cardDetails.add(CARD_TWO);
            JComboBox<String> jcb = new JComboBox<>(cardDetails);
            jcb.setEditable(false);
            jcb.addItemListener(this);
            add(jcb);

            JPanel c1 = new JPanel();
            c1.add(new JButton("Press Me"));
            c1.add(new JTextField("Some Text Here"));

            JPanel c2 = new JPanel();
            c2.add(new JLabel("Enter your name:"));
            c2.add(new JButton("Press me Instead"));

            panelCards = new JPanel(new CardLayout());
            panelCards.add(c1, CARD_ONE);
            panelCards.add(c2, CARD_TWO);

            add(panelCards);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            CardLayout cl = (CardLayout) (panelCards.getLayout());
            cl.show(panelCards, (String) e.getItem());
        }
    }

    private static class FlowLayoutExample extends JPanel {
        FlowLayoutExample() {
            setPreferredSize(new Dimension(400, 100));
            setLayout(new FlowLayout());
            for (int i = 1; i <= 9; i++) {
                add(new JButton("Button " + i));
            }
        }
    }

    private static class GridLayoutExample extends JPanel {
        GridLayoutExample() {
            setLayout(new GridLayout(3, 3, 5, 5));
            for (int i = 1; i <= 9; i++) {
                add(new JButton("Button " + i));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LayoutExamples::new);
    }
}
