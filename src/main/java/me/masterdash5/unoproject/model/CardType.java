package me.masterdash5.unoproject.model;

/**
 * Represents the different types of cards that can be used in a card game.
 * This enum categorizes cards based on their specific functionality or behavior
 * during gameplay. The possible card types include:
 *
 * - `DRAW2`: A card forcing the next player to draw two cards.
 * - `SKIP`: A card skipping the next player's turn.
 * - `REVERSE`: A card reversing the order of play.
 * - `WILD`: A card allowing the player to choose the next color of play.
 * - `WILD4`: A wild card that also forces the next player to draw four cards.
 * - `NUMBER`: A standard numbered card from a deck.
 *
 * This enumeration provides the `toString` method, which has been overridden to return
 * the name of the card type in lowercase format for easy display or file naming.
 */
public enum CardType {

    DRAW2,
    SKIP,
    REVERSE,
    WILD,
    WILD4,
    NUMBER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
