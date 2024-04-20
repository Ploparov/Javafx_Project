package pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

public class GameOver extends StackPane {
    private static GameOver instance;

    public GameOver(){

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // Set font to Arial, bold, size 50
        gameOverLabel.setAlignment(Pos.CENTER); // Center align the label

        Button start = new Button("Back to Main Menu");
        start.setOnMouseClicked(mouseEvent -> {
            Goto.MainMenuPage();});

        VBox vbox = new VBox(20); // Create a VBox with a spacing of 20
        vbox.getChildren().addAll(gameOverLabel, start); // Add the label and button to the VBox
        vbox.setAlignment(Pos.CENTER); // Center align the VBox

        getChildren().add(vbox); // Add the VBox to the pane
    }

    public static GameOver getGameOver(){
        if(instance == null){
            instance = new GameOver();
        }
        return instance;
    }
}
