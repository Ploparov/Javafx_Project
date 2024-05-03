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
    private VBox vbox;
    public GameOver(int score){
        initialVbox();
        gameOverText();
        scoreText(score);
        startButton();
    }

    public void gameOverText(){
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        gameOverLabel.setAlignment(Pos.CENTER);
        getVbox().getChildren().add(gameOverLabel);
    }

    public void scoreText(int score){
        Label scoreLabel = new Label();
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        scoreLabel.setText("Score: " + score);
        getVbox().getChildren().add(scoreLabel);
    }

    public void startButton(){
        Button start = new Button("Back to Main Menu");
        start.setOnMouseClicked(mouseEvent -> {
            Goto.MainMenuPage();
            GameMap.getInstance().resetScore();
        });
        getVbox().getChildren().add(start);
    }

    public void initialVbox(){
        setVbox(new VBox(20));
        getVbox().setAlignment(Pos.CENTER);
        getChildren().add(getVbox());
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public VBox getVbox() {
        return vbox;
    }
}
