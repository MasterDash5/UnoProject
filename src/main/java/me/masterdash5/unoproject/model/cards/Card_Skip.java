package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a "Skip" card in the card game. This specific card type skips
 * the next player's turn during gameplay and is associated with a particular color.
 *
 * The card has the following properties:
 * - A specific {@code CardColor}, determining the color of the card.
 * - A fixed {@code CardType} set to {@code CardType.SKIP}.
 *
 * Methods related to card number are deprecated, as "Skip" cards do not utilize
 * numerical values. Instead, the functionality of the card is determined by its type.
 *
 * Instances should be initialized with a valid {@code CardColor}. The card's type
 * is inherently set to {@code SKIP} upon creation.
 */
public class Card_Skip implements Card {

    private CardColor color;
    private Image image;

    public Card_Skip(CardColor color) {
        setCardColor(color);
        setCardType(CardType.SKIP);
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.SKIP;
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
