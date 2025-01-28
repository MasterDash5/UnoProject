package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a numbered card in the card game, typically part of the standard deck.
 * These cards are defined by a specific number, a color, and a card type set to {@code CardType.NUMBER}.
 *
 * This class provides methods to set and retrieve the card's color, number, and type,
 * ensuring that these values adhere to the valid ranges and constraints for numbered cards:
 * - The card number must be between 0 and 9.
 * - The card color must be one of the predefined {@code CardColor} values.
 *
 * Upon initialization, the card's type is automatically set to {@code CardType.NUMBER}.
 * The {@code toString} method produces a readable string representation combining the card's color and number.
 *
 * Instances of this class should only be created if the parameters provided are valid.
 */
public class Card_Number implements Card {

    private CardColor color;
    private CardType type;
    private int number;
    private Image image;

    public Card_Number(CardColor color, int number) {
        setCardColor(color);
        setCardNumber(number);
        setCardType(CardType.NUMBER);
    }

    @Override
    public void setCardColor(CardColor color) {
        if (color != null)
            this.color = color;
    }

    @Override
    public void setCardType(CardType type) {
        if (type != null)
            this.type = type;
    }

    @Override
    public void setCardNumber(int number) {
        if (number >= 0 && number <= 9)
            this.number = number;
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return color + " " + number;
    }

}
