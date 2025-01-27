package me.masterdash5.unoproject.model;

import me.masterdash5.unoproject.model.cards.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final List<Card> discardPile;

    // Constructor to initialize the deck
    public Deck() {
        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>(); // Initialize discardPile
        initializeDeck();
        shuffle();
    }

    // Initialize the deck with all UNO cards
    private void initializeDeck() {
        // Add Number Cards (0-9 for each color)
        for (CardColor color : CardColor.values()) {
            // Add one "0" card per color
            cards.add(new Card_Number(color, 0));

            // Add two of each number card (1-9) per color
            for (int number = 1; number <= 9; number++) {
                cards.add(new Card_Number(color, number));
                cards.add(new Card_Number(color, number));
            }

            // Add Action Cards (2 Draw2, Reverse, and Skip per color)
            cards.add(new Card_Draw2(color));
            cards.add(new Card_Draw2(color));
            cards.add(new Card_Reverse(color));
            cards.add(new Card_Reverse(color));
            cards.add(new Card_Skip(color));
            cards.add(new Card_Skip(color));
        }

        // Add Wild Cards (4 Wild and 4 Wild Draw4 cards)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card_Wild());
            cards.add(new Card_Wild_Draw4());
        }
    }

    // Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Draw a card from the top of the deck
    public Card drawCard() {
        if (cards.isEmpty())
            throw new IllegalStateException("The deck is empty!");

        return cards.removeLast(); // Removes and returns the top card
    }

    // Get the remaining size of the deck
    public int getSize() {
        return cards.size();
    }

    // Check if the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Reset the deck by clearing and reinitializing it
    public void reset() {
        cards.clear();
        initializeDeck();
        shuffle();
    }

    // Refill the deck from cards in discard pile.
    public void refill() {

    }

    // Add a card to the discard pile
    public void addToDiscardPile(Card card) {
        discardPile.add(card);
    }

    // Get the top card of the discard pile
    public Card getTopCard() {
        if (discardPile.isEmpty())
            throw new IllegalStateException("Discard pile is empty");

        return discardPile.getLast();
    }
}