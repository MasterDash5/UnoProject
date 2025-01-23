package me.masterdash5.unoproject.model.cards;

import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardAction;
import me.masterdash5.unoproject.model.CardColor;

public class NumberCard implements Card {

    private CardColor color;
    private byte number;

    public NumberCard(CardColor color, byte number) {
        this.color = color;
        this.number = number;
    }

    public int number() { return number; }

    @Override
    public CardColor color() { return color; }

    @Override
    public CardAction action() { return null; }

}
