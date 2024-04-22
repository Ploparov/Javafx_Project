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

    private Label scoreLabel = new Label();

    private VBox vbox = new VBox(20);

    public GameOver(int score){

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // Set font to Arial, bold, size 50
        gameOverLabel.setAlignment(Pos.CENTER); // Center align the label

        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        scoreLabel.setText("Score: " + GameMap.getInstance().getScoreTime());
        System.out.println("Score: " + GameMap.getInstance().getScoreTime());

        Button start = new Button("Back to Main Menu");
        start.setOnMouseClicked(mouseEvent -> {
            Goto.MainMenuPage();
            GameMap.getInstance().resetScore();
        });


        vbox.getChildren().addAll(gameOverLabel,scoreLabel, start); // Add the label and button to the VBox
        vbox.setAlignment(Pos.CENTER); // Center align the VBox
        scoreLabel.setText("Score: " + score);

         // Add the VBox to the pane
        getChildren().add(vbox);
    }

    public static GameOver getGameOver(){
        if(instance == null){
            instance = new GameOver(0);
        }
        return instance;
    }

    public void updateScore(int score) {
        System.out.println("Updating score: " + score);
        // Update scoreLabel
        scoreLabel.setText("Score: " + score);

        // Add scoreLabel back to vbox
    }
}
