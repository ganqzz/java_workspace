package com.cardsForest.platform;

import com.cardsForest.logic.Command;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * contains GUI initializations
 * <p>
 * Generally delegates user event to {@link GameCanvas}
 */
public class GameView extends JPanel {

    private static final long serialVersionUID = 0L;

    /* GUI elements */

    /**
     * the canvas the game is drawn onto
     */
    public GameCanvas canvas;

    /**
     * the status field can be updated by the game
     */
    private JTextField status;

    boolean started = false;

    public GameView() {
        /* create the GUI */
        createGUI();
    }

    /**
     * @return the canvas
     */
    public GameCanvas getCanvas() {
        return canvas;
    }

    /**
     * create all the GUI elements <br>
     * <p>
     * at this point, those are: <br>
     * GameCanvas in it's own panel
     * deal button in statusPanel
     * status field in statusPanel
     */
    public void createGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // create the canvas
        canvas = new GameCanvas();
        add(canvas, BorderLayout.CENTER);
//		panel = new GamePanel();
//		add(panel, BorderLayout.CENTER);

        //create the status and command panel
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(Color.LIGHT_GRAY);

        //add the deal button
        JButton deal = new JButton("Deal");
        deal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.queue.offer(Command.DEAL);
            }
        });
        statusPanel.add(deal);

        //add the deal button
        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });
        statusPanel.add(undo);

        //add the status field
        status = new JTextField(15);
        status.setEditable(false);
        status.setBackground(statusPanel.getBackground());
        statusPanel.add(status);

        //add it to the applet
        add(statusPanel, BorderLayout.NORTH);
    }

    /**
     * set the status bar to a selected string
     *
     * @param str the string to put as status
     */
    public void setStatus(final String str) {
        status.setText(str);
    }
}
