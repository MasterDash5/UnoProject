package me.masterdash5.unoproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private final List<Card> hand; // Represents the player's cards
    private final boolean computer;
    private int selectedCardIndex; // Index of the currently selected card
    private int forceDraw; // Amount the player must draw.

    /**
     * Constructs a new {@code Player} object with the specified type (computer or not).
     * Initializes the player's hand, sets the computer flag, and sets the selected card index to -1.
     *
     * @param computer a boolean indicating whether the player is controlled by a computer (true) or is a human player (false).
     */
    public Player(boolean computer) {
        this.hand = new ArrayList<>();
        this.computer = computer;
        this.selectedCardIndex = -1; // No card is selected initially
    }

    // Get the entire hand of the player
    public List<Card> getHand() { return hand; }

    // Add a card to the player's hand
    public void addCard(Card card) { hand.add(card); }

    // Remove a card from the player's hand
    public Optional<Card> removeCard(int index) {
        if (index >= 0 && index < hand.size())
            return Optional.of(hand.remove(index));

        return Optional.empty(); // Invalid index
    }

    // Get the currently selected card (if any)
    public Optional<Card> getSelectedCard() {
        if (selectedCardIndex == -1 || selectedCardIndex >= hand.size())
            return Optional.empty();

        return Optional.of(hand.get(selectedCardIndex));
    }

    // Set the currently selected card by index
    public void selectCard(int index) {
        if (index >= 0 && index < hand.size())
            selectedCardIndex = index; // Set the selected card index
        else
            selectedCardIndex = -1; // Deselect if the index is invalid
    }

    // Get the index of the currently selected card
    public int getSelectedCardIndex() {
        return selectedCardIndex;
    }

    // Clear the hand (e.g., when starting a new game)
    public void clearHand() {
        hand.clear();
        selectedCardIndex = -1; // Reset the selected card
    }

    // Check if the player's hand is empty
    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    // Get the size of the player's hand
    public int getHandSize() { return hand.size(); }

    public int getForceDraw() { return forceDraw; }

    public void setForceDraw(int forceDraw) { this.forceDraw = forceDraw; }

    public boolean isComputer() { return computer; }

}
