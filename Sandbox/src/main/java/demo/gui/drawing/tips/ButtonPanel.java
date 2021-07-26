package demo.gui.drawing.tips;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonPanel extends JPanel implements ActionListener {
    private DrawingArea drawingArea;

    public ButtonPanel(DrawingArea drawingArea) {
        this.drawingArea = drawingArea;

        add(createButton(" ", Color.BLACK));
        add(createButton("	", Color.RED));
        add(createButton("	", Color.GREEN));
        add(createButton("	", Color.BLUE));
        add(createButton("	", Color.ORANGE));
        add(createButton("	", Color.YELLOW));
        add(createButton("Clear", null));
    }

    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if ("Clear".equals(e.getActionCommand())) {
            drawingArea.clear();
        } else {
            drawingArea.setForeground(button.getBackground());
        }
    }
}
