package me.masterdash5.unoproject.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>(),
            disgarded = new ArrayList<>();

    public Deck() {
        // create card instances.
    }

    public void refill() {
        List<Card> sublist = disgarded.subList(0, disgarded.size());

        disgarded.removeAll(sublist);
        deck.addAll(sublist);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

}
