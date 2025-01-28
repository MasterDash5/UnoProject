package me.masterdash5.unoproject.model;

/**
 * Represents the distinct colors available for cards in a deck.
 * This enum is typically used in card games to assign one of several
 * predefined colors to a card object. Each constant corresponds to one
 * of the available colors in the deck: RED, GREEN, BLUE, or YELLOW.
 *
 * The {@code toString} method overrides the default {@link Enum#toString()}
 * behavior to return the name of the color in lowercase format.
 *
 * Example use cases include defining card attributes, filtering cards
 * by color, or iterating over all available colors for initializing a deck.
 */
public enum CardColor {

    RED,
    GREEN,
    BLUE,
    YELLOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
