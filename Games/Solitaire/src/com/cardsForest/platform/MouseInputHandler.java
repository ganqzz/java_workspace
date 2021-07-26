package com.cardsForest.platform;

import com.cardsForest.glue.Drag;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * responsible of getting mouse events targeted at {@link GameCanvas}
 * and transfer them to {@link Game}'s queue
 * <p>
 * relevant events: click, started drag
 */
public class MouseInputHandler extends MouseAdapter {

    private final static int COUNT_NUM = 5;

    /**
     * marks that dragging has started
     */
    private boolean startedDrag = false;
    //counter is useful to soften the start dragging action
    //we decide that we need to get several drag events before actually decide it's a drag and not
    // a simple click
    private int dragCounter = 0;
    private boolean counting = false;

    /**
     * holds the current drag event information
     */
    private Drag drag;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (InputEvent.BUTTON1_MASK == (e.getModifiers() & InputEvent.BUTTON1_MASK)) {
            //clicked left mouse button
            //add event to Game's queue
            Game.queue.offer(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (InputEvent.BUTTON1_MASK == (e.getModifiers() & InputEvent.BUTTON1_MASK)) {
            //dragging left mouse button
            if (!startedDrag) {
                //started dragging
                //create new Drag object and add it to Game's queue
                startedDrag = true;
                drag = new Drag(e.getPoint());
                counting = true;
                dragCounter = 0;
            } else {
                if (counting) { // before dragging
                    if (dragCounter < COUNT_NUM) {
                        dragCounter++;
                    } else { // start dragging
                        counting = false;
                        Game.queue.offer(drag);
                    }
                }

                //continue dragging
                //update current Drag object with the new mouse location
                drag.calcOffsetToPoint(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (InputEvent.BUTTON1_MASK == (e.getModifiers() & InputEvent.BUTTON1_MASK)) {
            //released left mouse button

            if (startedDrag) {
                if (counting) { // normal click
                    Game.queue.offer(e);
                } else {
                    //finished dragging
                    drag.end(e.getPoint());
                }

                startedDrag = false;
            }
        }
    }
}
