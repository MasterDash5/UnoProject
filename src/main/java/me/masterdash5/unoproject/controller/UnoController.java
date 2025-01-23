package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.CardAction;
import me.masterdash5.unoproject.model.Deck;
import me.masterdash5.unoproject.model.Player;
import me.masterdash5.unoproject.model.cards.NumberCard;

import java.util.Random;

public class UnoController {

    public static final int MAX_PLAYERS = 2;

    private Deck deck;
    private Player[] players;
    private int activePlayer;

    public UnoController() {
        deck = new Deck();
        players = new Player[MAX_PLAYERS];
        activePlayer = 0;
    }

    public void startGame() {
        deck.reset();
        deck.shuffle();

        for (int i = 0; i < MAX_PLAYERS; i++)
            players[i] = new Player();

        activePlayer = new Random().nextInt(0, MAX_PLAYERS);
    }

    public boolean testCard(Card card) {
        if (card.action() == CardAction.WILD || card.action() == CardAction.DRAW4)
            return true;

        Card top = deck.getTopDiscard();

        if (top instanceof NumberCard topNumber && card instanceof NumberCard cardNumber)
            if (topNumber.number() == cardNumber.number())
                return true;

        return card.color() == top.color() || card.action() == top.action();
    }

    public void swapPlayers() {
        activePlayer = (activePlayer + 1) % MAX_PLAYERS;
    }

    @FXML
    public void initialize() {

    }

}