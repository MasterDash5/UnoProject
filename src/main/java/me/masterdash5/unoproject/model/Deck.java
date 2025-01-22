package me.masterdash5.unoproject.model;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck = new Stack<>(),
            discarded = new Stack<>();

    public Deck() {
        // create card instances.
    }


    public void refill() {
        Card newDiscard = discarded.pop();
        deck.addAll(discarded);
        discarded.push(newDiscard);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

}
