package me.masterdash5.unoproject.model;

import me.masterdash5.unoproject.model.cards.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;
    private final List<Card> discardPile;

    /**
     * Constructs a new {@code Deck} object that represents a deck of cards used in gameplay.
     * The constructor initializes the deck by preparing a list of cards and a discard pile.
     * It then calls the {@code populateDeck} method to populate the deck with the required cards
     * and subsequently shuffles the deck to randomize the card order before gameplay begins.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>(); // Initialize discardPile

        populateDeck();
        shuffle();
    }

    /**
     * Populates the deck of cards with a standard set of cards. This includes:
     * - Color cards with numbers ranging from 0 to 9.
     * - Action cards (Draw2, Reverse, and Skip) in each color.
     * - Wild cards (Wild and Wild Draw4).
     *
     * The method follows these rules:
     * 1. For each color in {@link CardColor}, it adds:
     *    - One card with number 0.
     *    - Two cards each for numbers 1 through 9.
     *    - Two Draw2 cards.
     *    - Two Reverse cards.
     *    - Two Skip cards.
     * 2. Adds four Wild cards.
     * 3. Adds four Wild Draw4 cards.
     *
     * The resulting deck contains all the necessary cards for gameplay.
     * This method does not shuffle the cards and should be called
     * during the deck initialization process.
     */
    private void populateDeck() {
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

    public void shuffle() { Collections.shuffle(cards); }

    /**
     * Draws a card from the deck. If the deck is empty, it repopulates and shuffles
     * the deck before drawing a card. This method removes the top card of the deck
     * and returns it for gameplay or other operations.
     *
     * @return the {@code Card} drawn from the top of the deck.
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            populateDeck();
            shuffle();
        }

        return cards.removeLast(); // Removes and returns the top card
    }

    /**
     * Retrieves the total number of cards currently in the deck.
     *
     * @return the number of cards in the deck.
     */
    public int getSize() { return cards.size(); }

    /**
     * Checks whether the deck of cards is empty or not.
     *
     * @return {@code true} if the deck contains no cards, {@code false} otherwise.
     */
    public boolean isEmpty() { return cards.isEmpty(); }

    /**
     * Resets the deck to its initial state for a new gameplay session.
     * This method performs the following actions:
     * 1. Clears all the cards from the current deck.
     * 2. Repopulates the deck with a standard set of cards by invoking the {@code populateDeck} method.
     * 3. Shuffles the newly populated deck to randomize the card order.
     */
    public void reset() {
        cards.clear();
        populateDeck();
        shuffle();
    }

    /**
     * Adds the specified {@code Card} to the discard pile.
     *
     * @param card the card to be added to the discard pile. This represents
     *             a card that has been played by a player or for other gameplay
     *             purposes.
     */
    public void addToDiscardPile(Card card) { discardPile.add(card); }

    /**
     * Retrieves the top card from the discard pile in the deck.
     * Throws an {@code IllegalStateException} if the discard pile is empty.
     *
     * @return the {@code Card} object representing the top card of the discard pile.
     * @throws IllegalStateException if the discard pile is empty.
     */
    public Card getTopCard() {
        if (discardPile.isEmpty())
            throw new IllegalStateException("Discard pile is empty");

        return discardPile.getLast();
    }

    public Card getSecondTopCard() {
        if (discardPile.isEmpty())
            throw new IllegalStateException("Discard pile is empty");
        if (discardPile.size() < 2)
            return getTopCard();
        return discardPile.get(discardPile.size() - 2);
    }

    public int getDiscardPileSize() {
        return discardPile.size();
    }

    /**
     * Transfers cards from the discard pile back into the main deck while ensuring
     * the integrity of the discard pile. This method is typically used when the
     * deck is exhausted during gameplay and needs to be replenished.
     *
     * The method performs the following actions:
     * 1. Ensures that the discard pile is not empty. If it is empty, an
     *    {@code IllegalStateException} is thrown.
     * 2. Removes all but the topmost card from the discard pile and adds them
     *    back to the main deck.
     * 3. Clears the discard pile, retaining only the topmost card.
     *
     * @throws IllegalStateException if the discard pile is empty.
     */
    public void refillFromDiscardPile() {
        if (discardPile.isEmpty()) {
            throw new IllegalStateException("Discard pile is empty. Cannot refill the deck.");
        }

        // Save the top card of the discard pile
        Card topCard = discardPile.remove(discardPile.size() - 1);

        // Add all other cards from the discard pile to the deck
        cards.addAll(discardPile);

        // Clear the discard pile and keep the top card
        discardPile.clear();
        discardPile.add(topCard);
    }

}