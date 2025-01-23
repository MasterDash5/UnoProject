package me.masterdash5.unoproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private List<Card> hand;
    private int selectedCard;

    public Player() {
        hand = new ArrayList<>();
        selectedCard = -1;
    }

    public List<Card> getHand() { return hand; }

    public Optional<Card> getSelectedCard() {
        if (selectedCard == -1)
            return Optional.empty();

        return Optional.of(hand.get(selectedCard));
    }

}
