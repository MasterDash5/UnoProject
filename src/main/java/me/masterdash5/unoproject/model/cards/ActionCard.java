package me.masterdash5.unoproject.model.cards;

import me.masterdash5.unoproject.model.CardAction;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardColor;

public class ActionCard implements Card {
    public ActionCard(CardAction action, CardColor color) {

    }

    @Override
    public CardColor color() {
        return null;
    }
}
