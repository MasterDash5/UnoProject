package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button playervsplayer, playervsbot, Quitbtn;

    private Stage primaryStage;
    private Scene unoGameScene;
    private UnoController unoController;

    public void setPrimaryStage(Stage primaryStage) { this.primaryStage = primaryStage; }

    public void setUnoGameScene(Scene unoGameScene) { this.unoGameScene = unoGameScene; }

    public void setUnoController(UnoController unoController) { this.unoController = unoController; }

    @FXML
    private void initialize() {
        // Action for "Player vs Player" button
        playervsplayer.setOnAction(_ -> switchToUnoScene());

        // Disable "Player vs Bot" button
        playervsbot.setDisable(true);
        playervsbot.setStyle("-fx-background-color: gray; -fx-text-fill: lightgray; -fx-border-color: white; -fx-border-width: 2;");

        // Add hover effects for "Player vs Player"
        playervsplayer.setOnMouseEntered(e -> playervsplayer.setStyle("-fx-background-color: black; -fx-text-fill: yellow; -fx-border-color: yellow; -fx-border-width: 3;"));
        playervsplayer.setOnMouseExited(e -> playervsplayer.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2;"));

        // Add hover effects for "Player vs Bot" (even if it's disabled)
        playervsbot.setOnMouseEntered(e -> playervsbot.setStyle("-fx-background-color: darkgray; -fx-text-fill: yellow; -fx-border-color: yellow; -fx-border-width: 3;"));
        playervsbot.setOnMouseExited(e -> playervsbot.setStyle("-fx-background-color: gray; -fx-text-fill: lightgray; -fx-border-color: white; -fx-border-width: 2;"));

        Quitbtn.setOnAction(e -> System.exit(0));
        Quitbtn.setOnMouseEntered(e -> Quitbtn.setStyle("-fx-background-color: black; -fx-text-fill: yellow; -fx-border-color: yellow; -fx-border-width: 3;"));
        Quitbtn.setOnMouseExited(e -> Quitbtn.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2;"));
    }



    private void switchToUnoScene() {
        if (primaryStage != null && unoGameScene != null) {
            unoController.startGame();
            primaryStage.setScene(unoGameScene); // Switch to the UNO game scene
            primaryStage.setTitle("UNO - Game");
        } else {
            System.err.println("PrimaryStage or UnoGameScene is not set!");
        }
    }

}
