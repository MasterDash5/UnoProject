package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.masterdash5.unoproject.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UnoController {

    @FXML
    private ImageView image_Card1, image_Card2, image_Card3, image_Card4, image_Card5, image_Deck, image_BackDeck, player2hand1img, player2hand2img, player2hand3img, player2hand4img, player2hand5img;

    @FXML
    private Button button_RotateLeft, button_RotateRight, button_SkipTurn, button_CallUNO;

    @FXML
    private TextField tf_PlayersCardAmount, tf_OpponentsCardAmount;

    public static final int MAX_PLAYERS = 2; // Number of players in the game
    public static final int VISIBLE_CARDS = 5; // Number of visible cards for the player

    private Deck deck; // The deck of cards
    private Player[] players; // The players in the game
    private int activePlayer; // Index of the active player
    private int playerCardIndexStart = 0; // For rotating player's visible cards

    public UnoController() {
        deck = new Deck(); // Create a new deck
        players = new Player[MAX_PLAYERS]; // Initialize the players array
        activePlayer = 0; // Start with the first player
    }

    public void startGame() {
        System.out.println("game started");
        deck.reset();
        deck.shuffle();

        //loop through all players and deal them 7 cards
        for (int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = new Player();

            for (int j = 0; j < 7; j++) // Each player gets exactly 7 cards
                players[i].addCard(deck.drawCard());
        }

        deck.addToDiscardPile(deck.drawCard()); // Add 1 card to discard pile.

        activePlayer = new Random().nextInt(MAX_PLAYERS);
        updateUI();
    }

    public void swapPlayers() {
        activePlayer = (activePlayer + 1) % MAX_PLAYERS; // Switch to the next player
        playerCardIndexStart = 0;
        updateUI(); // Update the UI for the new active player
    }

    public int getNextPlayer() {
        return (activePlayer + 1) % MAX_PLAYERS;
    }

    private boolean handleCardPlay(int cardIndex) {
        int absoluteIndex = playerCardIndexStart + cardIndex;
        List<Card> playerHand = players[activePlayer].getHand();

        if (absoluteIndex >= playerHand.size())
            return false; // Invalid card selected.

        Card selectedCard = playerHand.get(absoluteIndex);

        if (!isValidPlay(selectedCard))
            return false; // Card couldn't be played.

        playerHand.remove(absoluteIndex);
        deck.addToDiscardPile(selectedCard); // Add to discard pile

        if (selectedCard.getType() == CardType.DRAW2) {
            players[activePlayer].setForceDraw(0); // Rests force draw if player stacked draw2
            players[getNextPlayer()].setForceDraw(players[getNextPlayer()].getForceDraw() + 2);
        }

        if (selectedCard.getType() == CardType.WILD4)
            players[getNextPlayer()].setForceDraw(4);

        if (selectedCard.getType() == CardType.WILD || selectedCard.getType() == CardType.WILD4)
            selectedCard.setCardColor(requestCardColor());

        swapPlayers();
        updateUI(); // Only update the UI, do not reset or shuffle the deck
        return true;
    }

    private CardColor requestCardColor() {
        // TODO: return from UI.

        return CardColor.RED; // Temp testing value.
    }

    private boolean isValidPlay(Card card) {
        if (players[activePlayer].getForceDraw() == 2)
            return card.getType() == CardType.DRAW2; // Allowing stacking of draw2 but not allowing play otherwise

        if (card.getType() == CardType.WILD || card.getType() == CardType.WILD4)
            return true;

        Card top = deck.getTopCard();

        if (card.getType() == CardType.NUMBER && top.getType() == CardType.NUMBER)
            if (card.getNumber() == top.getNumber())
                return true;

        if (card.getType() != CardType.NUMBER && card.getType() == top.getType())
            return true;

        return card.getColor() == top.getColor();
    }

    private void autoPlayTurn() {
        try {
            Thread.sleep(1000); // Wait a second so the CPU doesn't play instantly.
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for (int i = 0; i < players[activePlayer].getHandSize(); i++) {
            players[activePlayer].selectCard(i);

            if (handleCardPlay(i))
                return; // Card was successfully played.
        }

        players[activePlayer].addCard(deck.drawCard()); // No card could be played.
    }

    private void updateUI() {
        List<Card> playerCards = players[activePlayer].getHand();

        for (int i = 0; i < VISIBLE_CARDS; i++) {
            if (playerCardIndexStart + i < playerCards.size()) {
                ImageView cardImageView = getCardImageView(i);
                Image cardImage = playerCards.get((playerCardIndexStart + i)).getImage();
                cardImageView.setImage(cardImage);
                cardImageView.setVisible(true);
            } else
                getCardImageView(i).setVisible(false);
        }

        image_Deck.setImage(deck.getTopCard().getImage());
        tf_PlayersCardAmount.setText(String.valueOf(players[activePlayer].getHand().size()));
        tf_OpponentsCardAmount.setText(String.valueOf(players[(activePlayer + 1) % MAX_PLAYERS].getHand().size()));
    }

    private ImageView getCardImageView(int index) {
        return switch (index) { // Return the corresponding ImageView based on the index
            case 0 -> image_Card1;
            case 1 -> image_Card2;
            case 2 -> image_Card3;
            case 3 -> image_Card4;
            case 4 -> image_Card5;
            default -> throw new IllegalArgumentException("Invalid card index");
        };
    }

    @FXML
    public void initialize() {
        // Set up listeners for card images and buttons
        image_Card1.setOnMouseClicked(_ -> onCardClick1());
        image_Card2.setOnMouseClicked(_ -> onCardClick2());
        image_Card3.setOnMouseClicked(_ -> onCardClick3());
        image_Card4.setOnMouseClicked(_ -> onCardClick4());
        image_Card5.setOnMouseClicked(_ -> onCardClick5());
        image_Deck.setOnMouseClicked(_ -> onDrawCard());
        button_RotateLeft.setOnAction(_ -> onRotateLeft());
        button_RotateRight.setOnAction(_ -> onRotateRight());
        button_SkipTurn.setOnAction(_ -> onSkipTurn());
        button_CallUNO.setOnAction(_ -> onCallUNO());

        startGame(); // Start the game
    }

    @FXML
    public void onCardClick1() { handleCardPlay(0); } // Handle card play for each card slot
    @FXML
    public void onCardClick2() { handleCardPlay(1); } // Handle card play for each card slot
    @FXML
    public void onCardClick3() { handleCardPlay(2); } // Handle card play for each card slot
    @FXML
    public void onCardClick4() { handleCardPlay(3); } // Handle card play for each card slot
    @FXML
    public void onCardClick5() { handleCardPlay(4); } // Handle card play for each card slot

    @FXML
    public void onCallUNO() {
        if (players[activePlayer].getHand().size() == 2) { // Check if the player has 2 cards
            System.out.println("UNO called!");
        } else {
            System.out.println("You can only call UNO when you have 2 cards!");
        }
    }

    @FXML
    public void onSkipTurn() { // Skip the current player's turn
        swapPlayers();
    }

    @FXML
    public void onDrawCard() { // Draw a card from the deck
        if (!deck.isEmpty()) {
            if (players[activePlayer].getForceDraw() > 0) {
                for (int i = 0; i < players[activePlayer].getForceDraw(); i++) {
                    players[activePlayer].addCard(deck.drawCard()); // Draw the forced amount of cards at once
                }
                players[activePlayer].setForceDraw(0); // Reset how many the player must draw
            } else players[activePlayer].addCard(deck.drawCard()); // Draw normally if the player was not forced with draw 2/4
            swapPlayers();
            updateUI();
        } else {
            System.out.println("Deck is empty!");
        }
    }

    @FXML
    public void onRotateLeft() { // Rotate the visible cards to the left
        if (playerCardIndexStart > 0) {
            playerCardIndexStart--;
            updateUI();
        }
    }

    @FXML
    public void onRotateRight() { // Rotate the visible cards to the right
        if (playerCardIndexStart + VISIBLE_CARDS < players[activePlayer].getHand().size()) {
            playerCardIndexStart++;
            updateUI();
        }
    }

}
