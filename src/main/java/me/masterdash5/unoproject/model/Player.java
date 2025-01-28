package me.masterdash5.unoproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private final List<Card> hand; // Represents the player's cards
    private final boolean computer;
    private int selectedCardIndex; // Index of the currently selected card
    private int forceDraw; // Amount the player must draw.
    private String name;

    /**
     * Constructs a new {@code Player} object with the specified type (computer or not).
     * Initializes the player's hand, sets the computer flag, and sets the selected card index to -1.
     *
     * @param computer a boolean indicating whether the player is controlled by a computer (true) or is a human player (false).
     */
    public Player(boolean computer, String name) {
        this.hand = new ArrayList<>();
        this.computer = computer;
        this.selectedCardIndex = -1; // No card is selected initially
        this.name = name;
    }

    // Get the entire hand of the player
    public List<Card> getHand() { return hand; }

    // Add a card to the player's hand
    public void addCard(Card card) { hand.add(card); }

    public String getName() { return name; }

    public int getForceDraw() { return forceDraw; }

    public void setForceDraw(int forceDraw) { this.forceDraw = forceDraw; }

    public boolean isComputer() { return computer; }

}
