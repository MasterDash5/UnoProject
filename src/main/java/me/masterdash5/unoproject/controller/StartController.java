package me.masterdash5.unoproject.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button playervsplayer, playervsbot;

    private Stage primaryStage;
    private Scene unoGameScene;

    public void setPrimaryStage(Stage primaryStage) { this.primaryStage = primaryStage; }

    public void setUnoGameScene(Scene unoGameScene) { this.unoGameScene = unoGameScene; }

    @FXML
    private void initialize() {
        playervsplayer.setOnAction(_ -> switchToUnoScene());
        playervsbot.setOnAction(_ -> switchToUnoScene());
    }

    private void switchToUnoScene() {
        if (primaryStage != null && unoGameScene != null) {
            primaryStage.setScene(unoGameScene); // Switch to the UNO game scene
            primaryStage.setTitle("UNO - Game");
        } else {
            System.err.println("PrimaryStage or UnoGameScene is not set!");
        }
    }

}
