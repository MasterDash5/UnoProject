package me.masterdash5.unoproject.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JavaFXLauncher extends Application {

    public static void startup() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("/javafx/Uno.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 751, 525);
        stage.setTitle("Uno");
        stage.setScene(scene);
        stage.show();
    }

}
