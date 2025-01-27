package me.masterdash5.unoproject.model.cards;

import javafx.scene.image.Image;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.CardType;

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
