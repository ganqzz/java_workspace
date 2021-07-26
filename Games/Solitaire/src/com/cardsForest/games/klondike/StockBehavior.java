package com.cardsForest.games.klondike;

import com.cardsForest.foundations.Stack;
import com.cardsForest.glue.Selection;
import com.cardsForest.logic.Behavior;

/**
 *
 */
public class StockBehavior extends Behavior {

    private Stack waste;

    public StockBehavior(Stack waste) {
        this.waste = waste;
    }

    @Override
    public void click(Stack stack, int cardIndex) {
        if (!Selection.getInstance().isAvailable()) {
            if (!stack.isEmpty()) {
                stack.flipTop();
                stack.moveTo(waste, 1);
            } else {
                if (!waste.isEmpty()) {
                    waste.setAllFaceUp(false);
                    waste.shuffle();
                    waste.moveTo(stack, waste.size());
                }
            }
        }

    }

}
