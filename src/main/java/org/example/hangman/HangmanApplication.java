package org.example.hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HangmanApplication extends Application {

    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanApplication.class.getResource("Hangman-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hangman");
        stage.setScene(scene);
        stage.setMinWidth(HEIGHT);
        stage.setMinHeight(WIDTH);
        stage.setMaxHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}