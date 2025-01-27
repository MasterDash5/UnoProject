package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

import java.awt.*;

public class Card_Draw2 implements Card {

    private CardColor color;
    private int number;
    private Image image;

    @Override
    public void setImage(javafx.scene.image.Image image) {
        this.image = image;
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

    public Card_Draw2(CardColor color) {
        setCardColor(color);
        setCardType(CardType.DRAW2);
    }
}
