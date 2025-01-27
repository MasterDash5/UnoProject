package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

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
