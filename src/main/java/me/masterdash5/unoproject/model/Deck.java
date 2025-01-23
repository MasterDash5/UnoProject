package me.masterdash5.unoproject.model;

import me.masterdash5.unoproject.model.cards.NumberCard;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck = new Stack<>();
    private Stack<Card> discarded = new Stack<>();

    public Deck() {
        for (CardColor color : CardColor.values()) {
            for (int i = 1; i < 20; i++)
                deck.add(new NumberCard(color, (byte) (i % 10)));

            for (int i = 0; i < 2; i++) {
                for (CardAction action : CardAction.values()) {
                    deck.add(new Card() {
                        @Override
                        public CardColor color() {
                            if (action == CardAction.WILD || action == CardAction.DRAW4)
                                return null;

                            return color;
                        }

                        @Override
                        public CardAction action() { return action; }
                    });
                }
            }
        }
    }

    public Card draw() { return deck.pop(); }

    public void discard(Card card) { discarded.push(card); }

    public void refill() {
        Card newDiscard = discarded.pop();
        deck.addAll(discarded);
        discarded.push(newDiscard);
        shuffle();
    }

    public void reset() {
        deck.addAll(discarded);
        discarded.clear();
    }

    public void shuffle() { Collections.shuffle(deck); }

    public Card getTopDiscard() { return discarded.getLast(); }

}
