package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import me.masterdash5.unoproject.model.*;

import java.util.List;
import java.util.Random;

/**
 * The UnoController class manages the logic and user interface interactions for an UNO game.
 *
 * This class handles game setup, player actions, turn management, game state updates,
 * and user interface updates. It also includes methods for event handlers triggered by
 * user interactions with the game UI.
 *
 * Fields:
 * - Game elements such as card and deck images (e.g., `image_Card1`, `image_BackDeck`).
 * - UI buttons for player actions (e.g., `button_RotateLeft`, `button_CallUNO`).
 * - Fields representing player and game state (e.g., `deck`, `players`, `activePlayer`).
 * - Indicators for game-specific behaviors (e.g., `selectedColor`, `wildToggle`).
 *
 * Supported Operations:
 * - Game session initialization and start.
 * - Turn management and player transitions.
 * - Card play validation and handling.
 * - Wild card action toggling and finalization.
 * - Updates to the UI to reflect game progress.
 * - Input event handling for user interaction with game elements.
 */
public class UnoController {

    @FXML
    private ImageView image_Card1, image_Card2, image_Card3, image_Card4, image_Card5, image_Deck, image_BackDeck;
    @FXML
    private Button button_RotateLeft, button_RotateRight, button_CallUNO, button_Wildcard_Red, button_Wildcard_Blue, button_Wildcard_Yellow, button_Wildcard_Green;
    @FXML
    private TextField tf_PlayersCardAmount, tf_OpponentsCardAmount;

    // Constants for the game
    public static final int MAX_PLAYERS = 2; // Number of players in the game
    public static final int VISIBLE_CARDS = 5; // Number of visible cards for the player
    private Deck deck; // The deck of cards
    private Player[] players; // The players in the game
    private int activePlayer; // Index of the active player
    private int playerCardIndexStart = 0; // For rotating player's visible cards
    private CardColor selectedColor; // The color selected by the player from a wild card
    private boolean wildToggle = false; // Toggles the wild card action
    private boolean unoCalled = false; // Tracks if the active player called UNO
    private int playerTurn = 1;
    private int round = 1;

    private Stage primaryStage;
    private Scene StartScene; // Reference to the start scene


    /**
     * Constructs a new instance of the UnoController class.
     *
     * This constructor performs the following initialization tasks:
     * - Creates a new deck of cards.
     * - Initializes the players array to hold the maximum number of players.
     * - Sets the starting active player index to 0.
     */
    public UnoController() {
        deck = new Deck(); // Create a new deck
        players = new Player[MAX_PLAYERS]; // Initialize the players array
        activePlayer = 0; // Start with the first player
    }

    /**
     * Initializes and starts a new UNO game session by performing the following operations:
     * - Resets and shuffles the deck of cards.
     * - Instantiates players and deals each of them 7 cards.
     * - Places one card from the deck onto the discard pile to start the game.
     * - Randomly selects the first active player.
     * - Updates the game UI to reflect the initial game state.
     */
    public void startGame() {
        System.out.println("game started");
        deck.reset();
        deck.shuffle();

        //loop through all players and deal them 7 cards
        for (int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = new Player(false);

            for (int j = 0; j < 7; j++) // Each player gets exactly 7 cards
                players[i].addCard(deck.drawCard());
        }

        deck.addToDiscardPile(deck.drawCard()); // Add 1 card to discard pile.

        activePlayer = new Random().nextInt(MAX_PLAYERS);
        updateUI();
    }

    /**
     * Handles the transition between players in the UNO game. This method performs several actions
     * related to game state and player turn management:
     *
     * 1. Checks if the active player has only one card remaining without having called "UNO!".
     *    - If this is the case, the player is penalized by drawing two cards from the deck.
     * 2. Determines if the current player has no cards left to declare them the winner.
     *    - If a player wins, the game transitions to the main menu scene, provided the necessary
     *      references to `primaryStage` and `startScene` are available.
     * 3. Ensures that the deck is not empty; if it is, refills and shuffles the deck from the
     *    discard pile.
     * 4. Advances to the next player by updating the `activePlayer` index.
     * 5. Resets the `unoCalled` flag to false to prepare for the next player's turn.
     * 6. Resets the starting card index for the active player's hand to zero.
     * 7. Updates the user interface to reflect the new game state.
     */
    public void swapPlayers() {
        if (players[activePlayer].getHand().size() == 1 && !unoCalled) {
            for (int i = 0; i < 2; i++) {
                players[activePlayer].addCard(deck.drawCard());
            }
        } else if (players[activePlayer].getHand().isEmpty()) {
            System.out.println("Player has won!");

            // Switch to the start scene
            if (primaryStage != null && StartScene != null) {
                primaryStage.setScene(StartScene);
                primaryStage.setTitle("UNO - Main Menu");
            } else {
                System.err.println("PrimaryStage or StartScene is not set!");
            }
            return;
        }

        unoCalled = false;

        if (deck.isEmpty()) {
            deck.refillFromDiscardPile();
            deck.shuffle();
        }

        activePlayer = getNextPlayer();
        playerCardIndexStart = 0;
        updateUI();
    }


    /**
     * Determines the next player's turn in the game by calculating the next player index
     * using the current active player and the total number of players in the game.
     *
     * The method ensures that the player order cycles correctly in a circular fashion.
     *
     * @return the index of the next player in the sequence
     */
    public int getNextPlayer() { return (activePlayer + 1) % MAX_PLAYERS; }

    /**
     * Handles the logic for playing a card by the active player. This method checks whether the
     * selected card can be played, performs any necessary actions associated with the card type,
     * updates the game state, and determines if further actions are required (e.g., color selection
     * for Wild cards).
     *
     * @param cardIndex the index of the card being played from the active player's hand
     * @return true if the card was successfully played or further actions (e.g., Wild card color selection) are pending,
     *         false if the card could not be played or the card index is invalid
     */
    private boolean handleCardPlay(int cardIndex) {
        int absoluteIndex = playerCardIndexStart + cardIndex;
        List<Card> playerHand = players[activePlayer].getHand();

        if (absoluteIndex >= playerHand.size()) return false; // Invalid card selected.

        Card selectedCard = playerHand.get(absoluteIndex);

        if (!isValidPlay(selectedCard)) return false; // Card couldn't be played.

        playerHand.remove(absoluteIndex); // Remove the card from the player's hand
        deck.addToDiscardPile(selectedCard); // Add to discard pile

        // Allow the player to call UNO before their turn ends
        switch (selectedCard.getType()) {
            case WILD4:
                players[getNextPlayer()].setForceDraw(4);
                toggleWildAction(); // Show color selection
                break; // Wait for color selection to continue
            case WILD:
                toggleWildAction(); // Show color selection
                break;// Wait for color selection to continue
            case DRAW2:
                players[getNextPlayer()].setForceDraw(players[getNextPlayer()].getForceDraw() + 2);
                if (deck.getTopCard().getType() == CardType.DRAW2)
                    players[getNextPlayer()].setForceDraw(players[getNextPlayer()].getForceDraw() + 2); // Stacking draw2 effectively adds 4 when it loops back to the original player, this will handle that
                break;
            case REVERSE:
                swapPlayers(); // Reverse the order of players, effectively skipping the current player
                break;
            case SKIP:
                swapPlayers(); // Skip the next player
                break;
        }

        swapPlayers(); // End the turn
        return true;
    }

    /**
     * Toggles the state of the "wild action" mode in the UNO game and adjusts the
     * user interface and gameplay elements accordingly.
     *
     * This method performs the following actions:
     * - Updates the user interface to reflect the current game state by calling updateUI.
     * - Switches the wild action state by inverting the value of the `wildToggle` field.
     * - Logs the current state of wild action to the console.
     * - Shows or hides the color selection buttons (`button_Wildcard_Blue`,
     *   `button_Wildcard_Green`, `button_Wildcard_Red`, `button_Wildcard_Yellow`)
     *   depending on the wild action state.
     * - Disables or enables the color selection buttons to prevent unintended interactions.
     * - Disables or enables the card images (`image_Card1`, `image_Card2`, `image_Card3`,
     *   `image_Card4`, `image_Card5`) to restrict players from playing other cards
     *   while selecting a wild color.
     * - Disables or enables the card deck image (`image_BackDeck`) to prevent players from
     *   drawing cards during wild action mode.
     * - Calls the `swapPlayers` method to transition to the next player's turn.
     */
    private void toggleWildAction() {
        updateUI();
        wildToggle = !wildToggle;
        System.out.println("Toggling wild action. Current state: " + wildToggle);

        // Show or hide the color selection buttons
        button_Wildcard_Blue.setVisible(wildToggle);
        button_Wildcard_Green.setVisible(wildToggle);
        button_Wildcard_Red.setVisible(wildToggle);
        button_Wildcard_Yellow.setVisible(wildToggle);

        // Disable or enable the color selection buttons
        button_Wildcard_Blue.setDisable(!wildToggle);
        button_Wildcard_Green.setDisable(!wildToggle);
        button_Wildcard_Red.setDisable(!wildToggle);
        button_Wildcard_Yellow.setDisable(!wildToggle);

        // Disable or enable the card images to prevent playing other cards
        image_Card1.setDisable(wildToggle);
        image_Card2.setDisable(wildToggle);
        image_Card3.setDisable(wildToggle);
        image_Card4.setDisable(wildToggle);
        image_Card5.setDisable(wildToggle);

        // Disable or enable the deck to prevent drawing cards
        image_BackDeck.setDisable(wildToggle);
        swapPlayers(); // Move to the next player's turn
    }

    /**
     * Determines if the given card can be played based on the UNO game rules and
     * the current game state. This includes checking card type, color, and value.
     *
     * @param card the card to be evaluated for playability against the current
     *             game state
     * @return true if the card is valid to play, false otherwise
     */
    private boolean isValidPlay(Card card) {
        Card top = deck.getTopCard(); // Get the top card from the discard pile

        if (players[activePlayer].getForceDraw() > 0) // Forced draw active
            return card.getType() == CardType.DRAW2 && top.getType() != CardType.WILD4; // Allowing stacking of draw2 but not allowing play otherwise

        if (card.getType() == CardType.WILD || card.getType() == CardType.WILD4) // Wild cards
            return true;

        if (card.getType() == CardType.NUMBER && top.getType() == CardType.NUMBER) // Number cards
            if (card.getNumber() == top.getNumber())
                return true;

        if (card.getType() != CardType.NUMBER && card.getType() == top.getType()) // Special cards
            return true;

        return card.getColor() == top.getColor(); // Color cards
    }

//    private void autoPlayTurn() {
//        try {
//            Thread.sleep(1000); // Wait a second so the CPU doesn't play instantly.
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//        }
//
//        if (players[activePlayer].getForceDraw() > 0) {
//            for (int i = 0; i < players[activePlayer].getForceDraw(); i++)
//                players[activePlayer].addCard(deck.drawCard());
//
//            players[activePlayer].setForceDraw(0);
//            return;
//        }
//
//        for (int i = 0; i < players[activePlayer].getHandSize(); i++) {
//            players[activePlayer].selectCard(i);
//
//            if (handleCardPlay(i))
//                return; // Card was successfully played.
//        }
//
//        players[activePlayer].addCard(deck.drawCard()); // No card could be played.
//    }

    /**
     * Updates the User Interface (UI) to reflect the current state of the game.
     *
     * This method performs the following operations:
     * 1. Updates the display of the active player's card hand by iterating
     *    through the visible card slots and assigning the appropriate
     *    card images to the corresponding ImageView components.
     *    - If fewer cards are available than visible slots, extra slots are hidden.
     * 2. Sets the image of the top card in the discard pile to the deck's top card.
     * 3. Displays the total count of cards held by the active player and their opponent.
     * 4. Toggles the visibility and*/
    private void updateUI() {
        List<Card> playerCards = players[activePlayer].getHand(); // Get the active player's hand

        for (int i = 0; i < VISIBLE_CARDS; i++) { // Update the visible cards
            if (playerCardIndexStart + i < playerCards.size()) { // Show the card if it exists
                ImageView cardImageView = getCardImageView(i); // Get the corresponding ImageView
                Image cardImage = playerCards.get((playerCardIndexStart + i)).getImage();
                cardImageView.setImage(cardImage);
                cardImageView.setVisible(true);
            } else
                getCardImageView(i).setVisible(false); // Hide the card if it doesn't exist
        }

        image_Deck.setImage(deck.getTopCard().getImage()); // Update the top card in the discard pile
        tf_PlayersCardAmount.setText(String.valueOf(players[activePlayer].getHand().size())); // Update the card count
        tf_OpponentsCardAmount.setText(String.valueOf(players[(activePlayer + 1) % MAX_PLAYERS].getHand().size())); // Update the opponent's card count

        // Show or hide the "Call UNO" button based on the active player's card count
        if (players[activePlayer].getHand().size() == 2) { // Show the button if the player has 2 cards
            button_CallUNO.setVisible(true);
            button_CallUNO.setDisable(false);
        } else {
            button_CallUNO.setVisible(false);
            button_CallUNO.setDisable(true);
        }
    }

    /**
     * Retrieves the ImageView corresponding to a card slot index.
     *
     * This method maps the given index to a specific ImageView that represents a
     * card slot in the game. Valid indices are 0 through 4, corresponding to
     * the five visible card slots. If an invalid index is provided, an exception
     * is thrown.
     *
     * @param index the index of the card slot (valid range: 0 to 4)
     * @return the ImageView corresponding to the specified card slot
     * @throws IllegalArgumentException if the index is outside the valid range
     */
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

    /**
     * Completes the action sequence for a Wild card in the UNO game. This method
     * finalizes the process by performing the following steps:
     *
     * - Ensures that the color selection buttons are hidden if they are currently visible.
     * - Applies the selected color to the Wild card by updating its color property.
     * - Logs the selected color to the console.
     * - Transitions to the next player's turn by calling the swapPlayers method.
     */
    private void finishWildAction() {
        // Ensure buttons are hidden after the action
        if (wildToggle) {
            toggleWildAction(); // Hide the color selection buttons
        }

        Card topCard = deck.getTopCard();
        topCard.setCardColor(selectedColor); // Apply the selected color to the wild card
        System.out.println("Wild card color set to: " + selectedColor);

        swapPlayers(); // Move to the next player's turn
    }


    // Event handlers for UI elements
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
        button_CallUNO.setOnAction(_ -> onCallUNO());
        button_Wildcard_Blue.setOnAction(_ -> onColorBlue());
        button_Wildcard_Green.setOnAction(_ -> onColorGreen());
        button_Wildcard_Red.setOnAction(_ -> onColorRed());
        button_Wildcard_Yellow.setOnAction(_ -> onColorYellow());

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
    public void onColorRed() {
        selectedColor = CardColor.RED;
        finishWildAction();
    }
    @FXML
    public void onColorBlue() {
        selectedColor = CardColor.BLUE;
        finishWildAction();
    }
    @FXML
    public void onColorYellow() {
        selectedColor = CardColor.YELLOW;
        finishWildAction();
    }
    @FXML
    public void onColorGreen() {
        selectedColor = CardColor.GREEN;
        finishWildAction();
    }

    @FXML
    public void onCallUNO() {
        if (players[activePlayer].getHand().size() == 2) {
            unoCalled = true; // Mark that the player called UNO
            System.out.println("UNO called!");
        } else {
            System.out.println("You can only call UNO when you have 2 cards!");
        }
    }

    @FXML
    public void onDrawCard() { // Draw a card from the deck
        if (!deck.isEmpty()) {
            if (players[activePlayer].getForceDraw() > 0) {
                for (int i = 0; i < players[activePlayer].getForceDraw(); i++) {
                    players[activePlayer].addCard(deck.drawCard()); // Draw the forced amount of cards at once
                }
                players[activePlayer].setForceDraw(0); // Reset how many the player must draw
                players[getNextPlayer()].setForceDraw(0);  // Reset both players, this handles the case of stacking draw2
            } else players[activePlayer].addCard(deck.drawCard()); // Draw normally if the player was not forced to draw
            swapPlayers();
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
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setStartScene(Scene StartScene) {
        this.StartScene = StartScene;
    }


}
