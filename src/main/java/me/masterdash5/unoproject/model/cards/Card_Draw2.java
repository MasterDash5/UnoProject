package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a "Draw 2" card in the card game. This specific card type forces
 * the next player to draw two cards during gameplay and can also designate a color.
 *
 * The card has the following properties:
 * - A specific {@code CardColor}, determining the color of the card.
 * - A fixed {@code CardType} set to {@code CardType.DRAW2}.
 *
 * Methods related to card number are deprecated, as "Draw 2" cards do not make use of
 * numerical values. Instead, the functionality of this card is defined by its type.
 *
 * Instances should be initialized with a valid {@code CardColor}. The card's type
 * is inherently set to {@code DRAW2} upon creation.
 */
public class Card_Draw2 implements Card {

    private CardColor color;

    public Card_Draw2(CardColor color) {
        setCardColor(color);
        setCardType(CardType.DRAW2);
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.DRAW2;
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
