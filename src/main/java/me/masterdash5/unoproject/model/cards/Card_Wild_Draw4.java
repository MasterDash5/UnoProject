package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a "Wild Draw 4" card in the card game. This card type combines the
 * functionality of allowing the player to choose the next color of play with the
 * additional effect of forcing the next player to draw four cards.
 *
 * The card has the following properties:
 * - No fixed {@code CardColor} until dynamically set during gameplay.
 * - A fixed {@code CardType} set to {@code CardType.WILD4}.
 *
 * Methods related to card number and setting card type are deprecated, as "Wild Draw 4"
 * cards do not utilize numerical values, and their type is inherently fixed.
 *
 * This class ensures proper behavior and enforcement specific to a "Wild Draw 4" card.
 * The card's type is inherently set to {@code WILD4} upon creation.
 */
public class Card_Wild_Draw4 implements Card {

    private CardColor color;
    private Image image;

    public Card_Wild_Draw4() {
        setCardType(CardType.WILD4);
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.WILD4;
    }

    @Override
    @Deprecated
    public int getNumber() {
        //dont use this method
        return -1;
    }

    @Override
    public void setCardColor(CardColor color) {
        if (color != null)
            this.color = color;
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
