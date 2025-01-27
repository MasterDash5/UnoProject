package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import me.masterdash5.unoproject.model.Card;
import me.masterdash5.unoproject.model.Deck;
import me.masterdash5.unoproject.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnoController {

    @FXML
    private ImageView image_Card1, image_Card2, image_Card3, image_Card4, image_Card5, image_Deck, player2hand1img, player2hand2img, player2hand3img, player2hand4img, player2hand5img;

    @FXML
    private Button button_RotateLeft, button_RotateRight, button_SkipTurn, button_CallUNO;

    @FXML
    private TextField tf_PlayersCardAmount, tf_OpponentsCardAmount;

    public static final int MAX_PLAYERS = 2; // Number of players in the game

    private Deck deck; // The deck of cards
    private Player[] players; // The players in the game
    private int activePlayer; // Index of the active player

    private int playerCardIndexStart = 0; // For rotating player's visible cards
    private final int VISIBLE_CARDS = 5; // Number of visible cards for the player

    public UnoController() {
        deck = new Deck(); // Create a new deck
        players = new Player[MAX_PLAYERS]; // Initialize the players array
        activePlayer = 0; // Start with the first player
    }

    public void startGame() {
        deck.reset(); // Reset the deck
        deck.shuffle(); // Shuffle the deck

        for (int i = 0; i < MAX_PLAYERS; i++) { // Initialize each player
            players[i] = new Player(); // Create a new player
            // Each player starts with 7 cards
            for (int j = 0; j < 7; j++) {
                players[i].addCard(deck.drawCard()); // Draw a card from the deck 7 times for each player
            }
        }

        activePlayer = new Random().nextInt(MAX_PLAYERS); // Randomly select the starting player

        updateUI(); // Update the UI to reflect the initial state
    }

    public void swapPlayers() {
        activePlayer = (activePlayer + 1) % MAX_PLAYERS; // Switch to the next player
        updateUI(); // Update the UI for the new active player
    }

    private void updateUI() {
        // Update the visible cards for the active player
        List<Card> playerCards = players[activePlayer].getHand(); // Get the active player's hand
        for (int i = 0; i < VISIBLE_CARDS; i++) {
            if (playerCardIndexStart + i < playerCards.size()) { // Check if the card index is within the player's hand
                ImageView cardImageView = getCardImageView(i); // Get the ImageView for the card
                cardImageView.setImage(playerCards.get(playerCardIndexStart + i).getImage()); // Set the card image
                cardImageView.setVisible(true); // Show the card
            } else {
                getCardImageView(i).setVisible(false); // Hide unused slots
            }
        }

        // Update card counts for both players
        tf_PlayersCardAmount.setText(String.valueOf(players[activePlayer].getHand().size()));
        tf_OpponentsCardAmount.setText(String.valueOf(players[(activePlayer + 1) % MAX_PLAYERS].getHand().size()));
    }

    private ImageView getCardImageView(int index) {
        switch (index) { // Return the corresponding ImageView based on the index
            case 0: return image_Card1;
            case 1: return image_Card2;
            case 2: return image_Card3;
            case 3: return image_Card4;
            case 4: return image_Card5;
            default: throw new IllegalArgumentException("Invalid card index");
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

    @FXML
    public void onSkipTurn() { // Skip the current player's turn
        swapPlayers();
    }

    @FXML
    public void onDrawCard() { // Draw a card from the deck
        if (!deck.isEmpty()) {
            players[activePlayer].addCard(deck.drawCard());
            updateUI();
        } else {
            System.out.println("Deck is empty!");
        }
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

    private void handleCardPlay(int cardIndex) {
        int absoluteIndex = playerCardIndexStart + cardIndex; // Calculate the absolute index of the card
        List<Card> playerHand = players[activePlayer].getHand();  // Get the active player's hand

        if (absoluteIndex < playerHand.size()) { // Check if the index is valid
            Card selectedCard = playerHand.get(absoluteIndex); // Get the selected card
            // Validate if the card can be played
            if (isValidPlay(selectedCard)) { // Check if the card can be played
                playerHand.remove(absoluteIndex); // Remove the card from the player's hand
                // TODO: Update the current card on the discard pile
                swapPlayers(); // Switch to the next player
            } else {
                System.out.println("Invalid card play!"); // Display an error message
            }
        }

        updateUI(); // Update the UI after the card play
    }

    private boolean isValidPlay(Card card) {
        // TODO: make sure the card can be played based on the current card on the discard pile
        return true;
    }

    @FXML
    public void onCallUNO() {
        if (players[activePlayer].getHand().size() == 2) { // Check if the player has 2 cards
            System.out.println("UNO called!");
        } else {
            System.out.println("You can only call UNO when you have 2 cards!");
        }
    }

    @FXML
    public void initialize() {

        // Set up listeners for card images and buttons
        image_Card1.setOnMouseClicked(event -> onCardClick1());
        image_Card2.setOnMouseClicked(event -> onCardClick2());
        image_Card3.setOnMouseClicked(event -> onCardClick3());
        image_Card4.setOnMouseClicked(event -> onCardClick4());
        image_Card5.setOnMouseClicked(event -> onCardClick5());
        image_Deck.setOnMouseClicked(event -> onDrawCard());
        button_RotateLeft.setOnAction(event -> onRotateLeft());
        button_RotateRight.setOnAction(event -> onRotateRight());
        button_SkipTurn.setOnAction(event -> onSkipTurn());
        button_CallUNO.setOnAction(event -> onCallUNO());

        startGame(); // Start the game

    }
}
