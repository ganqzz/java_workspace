package com.cardsForest.glue;

import com.cardsForest.foundations.Card;
import com.cardsForest.foundations.Stack;
import com.cardsForest.platform.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulate stack change (i.e. operation)
 * <p>
 * operation is done in two phases:<br>
 * the first logic phase is done immediately,
 * the second display phase is done after all logic is done
 *
 * @see OperationManager
 */
public abstract class Operation {

    protected Stack src;
    protected List<Card> cards;
    protected StackSprite sprite;

    /**
     * initialize the new operation object
     *
     * @param src the stack on which the operation is performed
     */
    protected Operation(Stack src) {
        this.src = src;
        this.cards = src.cards;
        this.sprite = src.getSprite();
    }

    /**
     * default actions to be performed on cards as part of the operation
     *
     * @param cards the cards to perform the operation on
     */
    protected abstract void action(List<Card> cards);

    /**
     * do the logical part of the operation (done immediately)
     */
    final public void doLogic() {
        action(cards);
    }

    /**
     * do the display part of the operation (done after all logic is done)
     */
    public void doDisplay() {
        action(sprite.cards);
    }

    /**
     * return the stacks cards (only operation objects have access to the modifiable copy)
     *
     * @param stack the stack to get the cards from
     * @return stack's cards
     */
    protected List<Card> getModifiableCards(Stack stack) {
        return stack.cards;
    }

    /************************/
    /* Operation subclasses */
    /************************/

    /**
     * shuffle all the cards in a stack
     * (delegate to {@link Collections}' shuffle)
     */
    public static final class ShuffleOperation extends Operation {

        ArrayList<Card> tempCards;

        public ShuffleOperation(Stack src) {
            super(src);

            tempCards = new ArrayList<Card>();
        }

        @Override
        protected void action(List<Card> cards) {
            Collections.shuffle(cards);

            //save the shuffled cards
            //TODO: improve this, by remembering random key
            //of the shuffle and trace it to sprite's cards
            //instead of replacing objects
            for (Card card : cards) {
                tempCards.add(new Card(card));
            }
        }

        @Override
        public void doDisplay() {
            //set the shuffled cards to sprite
            sprite.cards = tempCards;
        }
    }

    /**
     * remove all cards from a stack
     * (delgate to collection's clear)
     */
    public static final class ClearOperation extends Operation {

        public ClearOperation(Stack src) {
            super(src);
        }

        @Override
        protected void action(List<Card> cards) {
            cards.clear();
        }
    }

    /**
     * flips the top card in a stack
     */
    public static final class FlipTopOperation extends Operation {

        public FlipTopOperation(Stack src) {
            super(src);
        }

        @Override
        protected void action(List<Card> cards) {
            if (!cards.isEmpty()) {
                cards.get(cards.size() - 1).flip();
            }
        }
    }

    /**
     * set all the faceUp property of stack's cards
     */
    public static final class SetAllFaceUpOperation extends Operation {

        boolean faceUp;

        public SetAllFaceUpOperation(Stack src, boolean faceUp) {
            super(src);
            this.faceUp = faceUp;
        }

        @Override
        protected void action(List<Card> cards) {
            for (Card card : cards) {
                card.setFaceUp(faceUp);
            }
        }
    }

    /**
     * move cards from one stack to another
     * (creates {@link CardMoveAnimation} on display phase)
     */
    public static final class MoveToOperation extends Operation {

        Stack dst;
        int num;
        boolean immediate;

        List<Card> dstCards;

        public MoveToOperation(Stack src, Stack dst, int num, boolean immediate) {
            super(src);
            this.dst = dst;
            this.num = num;
            this.immediate = immediate;

            dstCards = getModifiableCards(dst);
        }

        @Override
        protected void action(List<Card> cards) {
            List<Card> view = cards.subList(cards.size() - num, cards.size());
            dstCards.addAll(view);
            view.clear();
        }

        @Override
        public void doDisplay() {
            if (immediate) {
                dstCards = dst.getSprite().cards;
                action(sprite.cards);
            } else {
                Game.drawMotion(new CardMoveAnimation(src, dst, num));
            }
            sprite.updateBounds();
            dst.getSprite().updateBounds();
        }
    }
}
