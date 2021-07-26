package com.cardsForest.platform;

import com.cardsForest.foundations.Stack;
import com.cardsForest.games.klondike.Klondike;
import com.cardsForest.glue.CardSpriteStore;
import com.cardsForest.glue.Drag;
import com.cardsForest.glue.Motion;
import com.cardsForest.glue.OperationManager;
import com.cardsForest.glue.Selection;
import com.cardsForest.logic.Command;
import com.cardsForest.logic.GameLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.concurrent.ArrayBlockingQueue;

import static com.cardsForest.platform.Shorthands.error;
import static com.cardsForest.platform.Shorthands.print;

/**
 * the main thread of the game <br>
 * <p>
 * responsibilities: <br>
 * - load the game's sprites <br>
 * - create the GameLogic <br>
 * - transfer events to GameLogic<br>
 * - handle animation / dragging
 * - update the GameApplet when needed
 *
 * @see GameLogic
 * @see com.cardsForest.glue.Sprite
 */
public class Game extends Thread {

    /**
     * animation Frames Per Seconds
     */
    public static final long FPS = 60;
    private static final long INTERVAL = 1000000000L / FPS; // ns
    private static final long THRESHOLD = INTERVAL / 4; // Frame skip
    /**
     * this is to be nice to other threads in the game <br>
     * we may be running the animation for NO_DELAYS_PER_YIELD
     * without yielding <br>
     * (which means we give up our time share to
     * let another thread run)
     */
    private static final int NO_DELAYS_PER_YIELD = 16;
    /**
     * maximum number of animation updates (frames) <br>
     * to skip when failed to draw all frames on
     * requested FPS <br>
     * (setting this high will mean animation
     * will always run at the same speed but may have very
     * large jumps) s
     */
    private static final int MAX_FRAME_SKIPS = 6;

    /**
     * singleton instance of the game
     */
    private static Game game = null;

    /**
     * manage the game logic decisions
     */
    private static GameLogic logic;
    /**
     * if true the game is running and responds
     * to user events
     */
    private static boolean running;

    /**
     * used as input to the game <br>
     * most game event and requests must
     * through this queue
     */
    public static ArrayBlockingQueue<Object> queue;

    /**
     * reference to the game applet <br>
     * needed when we want to update the display <br>
     * TODO: consider transferring a more simple object
     * Game doesn't need all GameApplet
     */
//	private static GameApplet applet;
    private static GameSwing applet;

    /**
     * create a new game instance: <br>
     * - create the queue <br>
     * - load all images <br>
     * - create the GameLogic <br>
     * - and start the Game thread
     *
     * @param applet the game will update this when needed
     */
//	private Game(GameApplet applet) {
//		if (applet == null) {
//			throw new NullPointerException();
//		}
//		Game.applet = applet;
//		//load the images
//		CardSpriteStore.loadAll();
//
//		queue = new ArrayBlockingQueue<Object>(1);
//		logic = new Klondike();
//		running = false;
//
//		//start the game
//		start();
//	}
    private Game(GameSwing applet) {
        if (applet == null) {
            throw new NullPointerException();
        }
        Game.applet = applet;
        //load the images
        CardSpriteStore.loadAll();

        queue = new ArrayBlockingQueue<Object>(1);
        logic = new Klondike(); // TODO: Game Selection
        running = false;

        //start the game
        start();
    }

    /**
     * create the singleton instance of the game
     *
     * @param applet the game will update this when needed
     */
//	public static void create(GameApplet applet) {
//		if (game == null) {
//			game = new Game(applet);
//		} else {
//			print("game already created"); //aka stupid explorer
//		}
//	}
    public static void create(GameSwing applet) {
        if (game == null) {
            game = new Game(applet);
        } else {
            print("game already created");
        }
    }

    /**
     * cyclicly go over the queue and handle incoming events
     * <p>
     * <p>
     * events are (types are in <b>bold</b>):<br>
     * <b> String </b> <br>
     * "start" - game is ready to begin for the first time <br>
     * "paint" - draw the game to canvas <br>
     * "deal" - restart the game <br>
     * <b> Dimension </b> - update screen size <br>
     * <b> MouseEvent </b> - mouse click (ignored if not running) <br>
     * <b> Drag </b> - drag handling start (ignored if not running) <br>
     * <p>
     * at the end of most events checks if game is done
     * (ask GameLogic) and update applet if so
     */
    @Override
    public void run() {
        // Event looper
        while (true) {
            Object o = null;
            try {
                // get a new event
                o = queue.take();
            } catch (InterruptedException e) {
                error("interrupted while waiting for game event");
            }

            if (o instanceof Command) {
                handleCommandEvent((Command) o);
                continue;
            }

            if (o instanceof Dimension) {
                handleScreenSizeEvent(((Dimension) o).width, ((Dimension) o).height);
                continue;
            }

            if (running && o instanceof MouseEvent) {
                handleClick((MouseEvent) o);
                continue;
            }

            if (running && o instanceof Drag) {
                handleDragEvent((Drag) o);
            }
        }
    }

    /**
     * handle single Enum command
     * <p>
     * see run method header
     */
    private void handleCommandEvent(Command command) {
        switch (command) {
        case START:
            running = true;
            logic.deal();

            //update the display
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);

            checkGameDone();
            break;

        case PAINT:
            applet.canvas.paintGame(null);
            break;

        case DEAL:
            running = true;
            applet.setStatus(null);
            logic.redeal();

            //update the display
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);

            checkGameDone();
            break;
        }
    }

    /**
     * see run method header
     */
    private void handleScreenSizeEvent(int w, int h) {
        for (Stack stack : logic.getStacks()) {
            stack.getSprite().updateScreenSize(w, h);
        }
        Selection.getInstance().updateScreenSize(w, h);
    }

    /**
     * got a mouse click
     * delegate to logic
     * <p>
     * see run method header
     */
    private void handleClick(MouseEvent e) {
        logic.updateClick(e.getPoint(), e.getClickCount() > 1); // multi/single click

        //update the display
        OperationManager.doDisplayOperations();
        applet.canvas.paintGame(null);

        checkGameDone();
    }

    /**
     * got a drag start
     * (drag object will keep update from MouseInputHandler)
     * <p>
     * see run method header
     */
    private void handleDragEventBak(Drag drag) {
        final Selection selection = Selection.getInstance();
        if (selection.isAvailable()
            &&
            !selection.getBounds().contains(drag.getStart())) {
            //got a selection but it's not in bounds
            //end the selection
            selection.selectEnd(null);
            //update the display
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);
        }

        if (!selection.isAvailable()) {
            //don't have a selection (maybe because we just now ended it)
            //so try to select
            logic.updateClick(drag.getStart(), false);
            //update the display with the changes
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);
        }

        if (selection.isAvailable()) {
            //we have a selection
            //and it's in bounds of the click
            //(got to be in bounds otherwise it wouldn't have been selected)

            //it's in bounds -> GOT drag
            //draw the drag animation
            drawMotion(drag);

            //update the logic with drag end
            //(for logic's concern this is just two clicks
            //start and end ... except end click now use
            //the bounds of the dragged selection
            //(instead of just a point)
            logic.updateClick(selection.getBounds());
            //update the display with the changes
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);
        }

        checkGameDone();
    }

    private void handleDragEvent(Drag drag) {
        final Selection selection = Selection.getInstance();
        if (selection.isAvailable() &&
            !selection.getBounds().contains(drag.getStart())) {
            //got a selection but it's not in bounds
            //end the selection
            selection.selectEnd(null);
            //update the display
//			OperationManager.doDisplayOperations();
//			applet.canvas.paintGame(null);
        }

        if (!selection.isAvailable()) {
            //don't have a selection (maybe because we just now ended it)
            //so try to select
            logic.updateClick(drag.getStart(), false);
            //update the display with the changes
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);
        }

        if (selection.isAvailable()) {
            //we have a selection
            //and it's in bounds of the click
            //(got to be in bounds otherwise it wouldn't have been selected)

            //it's in bounds -> GOT drag
            //draw the drag animation
            drawMotion(drag);

            //update the logic with drag end
            //(for logic's concern this is just two clicks
            //start and end ... except end click now use
            //the bounds of the dragged selection
            //(instead of just a point)
            logic.updateClick(selection.getBounds());
            //update the display with the changes
            OperationManager.doDisplayOperations();
            applet.canvas.paintGame(null);
        }

        checkGameDone();
    }

    /**
     * ask logic if game is done
     * if so update applet's status field
     * <p>
     * see run method header
     */
    private void checkGameDone() {
        if (logic.checkGameDone()) {
            running = false;
            applet.setStatus("GAME DONE");
        }
    }

    /**
     * draw the game <br>
     * and selection if available <br>
     * and animation if not null
     *
     * @param g    graphics to draw on
     * @param anim animation to draw (if not null)
     */
    public static void draw(Graphics g, Motion anim) {
        for (Stack stack : logic.getStacks()) {
            stack.draw(g);
        }
        if (Selection.getInstance().isAvailable()) {
            Selection.getInstance().draw(g);
        }
        if (anim != null) {
            anim.draw(g);
        }
    }

    /**
     * draw {@link Motion} <br>
     * draw frame by frame according to FPS until
     * anim's isDone returns true
     *
     * @param anim motion to draw
     */
    public static void drawMotion(Motion anim) {
        anim.init();
        if (anim.isDone()) {
            return;
        }

        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;

        beforeTime = System.nanoTime();

        while (true) {
            anim.updateFrame();
            applet.canvas.paintGame(anim);

            if (anim.isDone()) {
                return;
            }

            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (INTERVAL - timeDiff) - overSleepTime;

            if (sleepTime > THRESHOLD) { // some time left in this cycle
                try {
                    Thread.sleep(sleepTime / 1000000L); // nano -> ms
                } catch (InterruptedException ex) {
                    error("animator was interrupted");
                }
                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            } else { // sleepTime <= 0; the frame took longer than
                // the period
                excess -= sleepTime; // store excess time value
                overSleepTime = 0L;

                if (++noDelays >= NO_DELAYS_PER_YIELD) {
                    Thread.yield(); // give another thread a chance
                    // to run
                    noDelays = 0;
                }
            }

            beforeTime = System.nanoTime();

            /*
             * If frame animation is taking too long, update the
             * game state without rendering it, to get the
             * updates/sec nearer to the required FPS.
             */
            int skips = 0;
            while ((excess > INTERVAL) && (skips < MAX_FRAME_SKIPS)) {
                excess -= INTERVAL;
                anim.updateFrame();
                skips++;
                if (anim.isDone()) {
                    return;
                }
            }
        }
    }
}
