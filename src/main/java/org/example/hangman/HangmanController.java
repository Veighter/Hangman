package org.example.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HangmanController {
    private String chosenWord;
    private int livesLeft;
    private int lettersLeft;

    @FXML
    private Label wordLbl;

    @FXML
    private Label livesLbl;

    @FXML
    private Label endLbl;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private ImageView hangman;

    @FXML
    protected void onLetterButtonClick(ActionEvent event) {
        Button letterButton = (Button) event.getSource();

        letterButton.setDisable(true);
        String letter = letterButton.getText();

        processLetter(letter);
    }

    private void processLetter(String letter) {
        char charLetter = letter.charAt(0);
        boolean found = false;
        for(int i = 0; i< chosenWord.length(); i++){
            if(charLetter == chosenWord.charAt(i)){
                showLetter(i);
                found=true;
                lettersLeft--;
            }
        }
        if(!found) {
            livesLeft--;
            updateLivesLeft();
            updateHangman();
        }
        if(livesLeft ==0 || lettersLeft==0){
            showEndScreen();
        }
    }

    private void updateHangman(){
        String pathToImage = "src/main/resources/org/example/hangman/Graphics/step"+(8-livesLeft)+".png";
        File imageFile = new File(pathToImage);
        hangman.setImage(new Image(imageFile.toURI().toString()));
    }

    private void updateLivesLeft(){
        livesLbl.setText(""+livesLeft);
    }

    private void showEndScreen(){
        if(livesLeft==0){
            endLbl.setText("He hanged man ...");
            wordLbl.setTextFill(Paint.valueOf("red"));
        }
        else{
            endLbl.setText("To the rescue!!");
        }
        disableAllButtons();
    }

    private void disableAllButtons(){
        for(Node node: buttonGrid.getChildren()){
            node.setDisable(true);
        }
    }

    private void showLetter(int showIndex){
        StringBuilder newGuess = new StringBuilder(wordLbl.getText());
        newGuess.setCharAt(showIndex, chosenWord.charAt(showIndex));
        wordLbl.setText(newGuess.toString());
    }

    public void initialize() {
        chosenWord = getWordOutOfTXTFile().toUpperCase();
        lettersLeft= chosenWord.length();
        wordLbl.setText("_".repeat(chosenWord.length()));
        livesLeft = 8;
        updateLivesLeft();

    }

    private String getWordOutOfTXTFile() {
        try {
            List<String> words = Files.readAllLines(Path.of("src/main/resources/org/example/hangman/Words/words.txt"));
            int chosenWordIndex = (int) (Math.random() * words.size());
            return words.get(chosenWordIndex);

        } catch (IOException e) {
            System.out.println("Wrong file path given.");
            System.exit(-1);
        }

        return "";
    }
}