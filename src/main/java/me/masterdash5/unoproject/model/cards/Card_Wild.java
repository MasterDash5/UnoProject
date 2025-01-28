package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

/**
 * Represents a "Wild" card in the card game. This card type allows the player
 * to choose any color during their turn, providing tactical flexibility in gameplay.
 *
 * The card has the following properties:
 * - No fixed {@code CardColor} until set dynamically by the player during gameplay.
 * - A fixed {@code CardType} set to {@code CardType.WILD}.
 *
 * Methods related to card number are deprecated, as "Wild" cards do not utilize
 * numerical values. Instead, the functionality of the card is determined by its type.
 *
 * This class overrides methods to ensure proper behavior associated with a "Wild" card.
 * The card's type is inherently set to {@code WILD} upon creation.
 */
public class Card_Wild implements Card {

    private CardColor color;
    private Image image;

    public Card_Wild() {
        setCardType(CardType.WILD);
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.WILD;
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
