package me.masterdash5.unoproject.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController {

    @FXML
    private Button playervsplayer, playervsbot, Quitbtn;

    private Stage primaryStage;
    private Scene unoGameScene;
    private UnoController unoController;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Set specific window size
        primaryStage.setWidth(600); // Set your desired width
        primaryStage.setHeight(600); // Set your desired height
        primaryStage.setResizable(false); // Prevent resizing
    }

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
        if (primaryStage != null && unoGameScene != null && unoController != null) {
            unoController.setPrimaryStage(primaryStage); // Pass the stage to UnoController
            unoController.startGame();
            applySceneTransition(unoGameScene);
            primaryStage.setTitle("UNO - Game");
            // Enforce window size in case it needs adjustment
            primaryStage.setWidth(800);
            primaryStage.setHeight(780);
            primaryStage.setResizable(false);
        } else {
            System.err.println("Error: PrimaryStage, UnoGameScene, or UnoController is not set!");
        }
    }



    private void applySceneTransition(Scene newScene) {
        // Ensure the new scene's root has the correct background color
        newScene.getRoot().setStyle("-fx-background-color: black;");

        // Create a fade-out transition for the current scene
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), primaryStage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeOut.setOnFinished(event -> {
            // Switch to the new scene
            primaryStage.setScene(newScene);

            // Apply fade-in transition for the new scene
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), newScene.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });

        fadeOut.play(); // Start the fade-out transition
    }




}
