package com.cardsForest.games.klondike;

import com.cardsForest.foundations.Stack;
import com.cardsForest.glue.Selection;
import com.cardsForest.logic.Behavior;
import com.cardsForest.logic.SequenceChecker;

/**
 *
 */
public class WasteBehavior extends Behavior {

    Stack[] foundation;

    public WasteBehavior(Stack[] foundation) {
        this.foundation = foundation;
    }

    /* (non-Javadoc)
     * @see com.cardsForest.foundations.Behavior#Click(com.cardsForest.foundations.Stack, int)
     */
    @Override
    public void click(Stack stack, int cardIndex) {
        if (!Selection.getInstance().isAvailable() &&
            !stack.isEmpty()) {
            Selection.getInstance().select(stack, stack.size() - 1);
        }
    }

    @Override
    public void doubleClick(Stack stack, int cardIndex) {
        Selection selection = Selection.getInstance();
        if (selection.stack.size() == 1) {
            SequenceChecker sc = new FoundationSequenceChecker();
            for (Stack f : foundation) {
                if (f.isEmpty()) {
                    if (sc.checkStarter(selection.stack.getTop())) {
                        if (!selection.src.isEmpty() &&
                            !selection.src.getTop().isFaceUp()) {
                            selection.src.flipTop();
                        }
                        selection.selectEnd(f);
                        return;
                    }
                } else {
                    if (sc.checkSequential(f.getTop(), selection.stack.getTop())) {
                        if (!selection.src.isEmpty() &&
                            !selection.src.getTop().isFaceUp()) {
                            selection.src.flipTop();
                        }
                        selection.selectEnd(f);
                        return;
                    }
                }
            }
        }
    }
}
