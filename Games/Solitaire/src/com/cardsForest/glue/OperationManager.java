package com.cardsForest.glue;

import com.cardsForest.platform.Game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * keeps a log of the operations perform on the stacks <br>
 * runs the operation:<br>
 * 1. logical phase is done immediately <br>
 * 2. display phase is triggered by {@link Game} (by calling {@code doDisplayOperations})
 * and is performed after all logic is done
 * <p>
 * contains Operation subclasses
 *
 * @see Operation
 */
public final class OperationManager {

    /**
     * log of operations occurred during logic phase
     * (so we can retrace them at display phase)
     */
    private static List<Operation> log = new ArrayList<Operation>();

    /**
     * history of operations occurred during logic phase
     * (so we can undo those operations)
     */
    private static Deque<Operation> history = new ArrayDeque<Operation>();

    /**
     * static class behavior:
     *
     * @throws UnsupportedOperationException on creation
     */
    private OperationManager() {}

    /**
     * add a new operation to log
     * and perform the logic phase immediately
     *
     * @param o the operation to add
     */
    public static void add(Operation o) {
        o.doLogic();
        log.add(o);
        history.push(o);
    }

    /**
     * do the display phase of the operations in the log
     */
    public static void doDisplayOperations() {
        while (!log.isEmpty()) {
            Operation o = log.remove(0);
            o.doDisplay();
        }
    }
}
