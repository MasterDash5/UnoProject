package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a "Reverse" card in the card game. This type of card reverses
 * the order of play during gameplay and is associated with a specific color.
 *
 * The card has the following properties:
 * - A designated {@code CardColor}, representing the card's color.
 * - A fixed {@code CardType} set to {@code CardType.REVERSE}.
 *
 * Methods related to card number are deprecated, as "Reverse" cards
 * do not utilize numerical values. The functionality of this card is
 * defined by its type and color.
 *
 * Instances should be initialized with a valid {@code CardColor}. The card's
 * type is automatically set to {@code REVERSE} upon creation.
 */
public class Card_Reverse implements Card {

    private CardColor color;
    private Image image;

    public Card_Reverse(CardColor color) {
        setCardColor(color);
        setCardType(CardType.REVERSE);
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.REVERSE;
    }

    @Override
    @Deprecated
    public int getNumber() {
        //dont use this method
        return -1;
    }

    @Override
    public void setCardColor(CardColor color) {
        if(color != null) {
            this.color = color;
        }
    }

    @Override
    @Deprecated
    public void setCardType(CardType type) {
        //dont use this method
    }

    @Override
    @Deprecated
    public void setCardNumber(int number) {
        //dont use this method
    }

}
