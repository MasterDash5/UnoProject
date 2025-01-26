package me.masterdash5.unoproject.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.masterdash5.unoproject.controller.StartController;

import java.io.IOException;
import java.net.URL;

public class JavaFXLauncher extends Application {

    public static void startup() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL startUrl = getClass().getResource("/javafx/Start.fxml");
        FXMLLoader startLoader = new FXMLLoader(startUrl);
        Scene startScene = new Scene(startLoader.load(), 600, 400);

        URL unoUrl = getClass().getResource("/javafx/Uno.fxml");
        FXMLLoader unoLoader = new FXMLLoader(unoUrl);
        Scene unoScene = new Scene(unoLoader.load(), 779, 712);

        primaryStage.setTitle("UNO - Start Screen");
        primaryStage.setScene(startScene);
        primaryStage.show();

        StartController startController = startLoader.getController();
        startController.setPrimaryStage(primaryStage);
        startController.setUnoGameScene(unoScene);
    }
}
