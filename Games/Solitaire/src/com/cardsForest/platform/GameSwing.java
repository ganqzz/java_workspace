package com.cardsForest.platform;

import com.cardsForest.logic.Command;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameSwing extends JFrame {

    private static final long serialVersionUID = 1L;

    /* GUI elements */

    /**
     * the canvas the game is drawn onto
     */
    public GameCanvas canvas;

    /**
     * the GameView
     */
    private GameView gameView;

    boolean started = false;

    public GameSwing() throws HeadlessException {
        // setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ウィンドウ×ボタン

        // create the game thread
        Game.create(this);

        // create the GUI
        gameView = new GameView();
        getContentPane().add(gameView);
        canvas = gameView.getCanvas();

        pack(); // サブに合わせる
        setVisible(true);

        start();
    }

    /* I don't really have any use for those functions now */
    public void start() {
        if (!started) {
            //initiate the double buffer strategy
            canvas.initStrategy();
            /* start the Game */
            Game.queue.offer(Command.START);
            started = true;
        }
    }

    /**
     * set the status bar to a selected string
     *
     * @param str the string to put as status
     */
    public void setStatus(final String str) {
        gameView.setStatus(str);
    }

    /**
     * Swing App entry
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameSwing::new);
    }
}
