package pane;

import Sound.SoundW;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

public class MainMenu extends StackPane {

    private VBox vbox;
    private SoundW soundW = new SoundW();

    public MainMenu(){
        playEffect(0);
        initialVbox();
        setBackGround();
        startGameButton();
        quitGameButton();


    }

    public void setBackGround(){
        Image image = new Image("Background/kittenChoreBackground.png");
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        this.setBackground(new Background(backgroundImage));
    }

   public void startGameButton(){
        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(mouseEvent -> {Goto.gameMap();});
        startGame.setMinHeight(100);
        startGame.setMinWidth(200);
        startGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        startGame.setStyle("-fx-background-radius: 20;");
        vbox.getChildren().add(startGame);
    }

    public void quitGameButton(){
        Button quitGame = new Button("Quit Game");
        quitGame.setOnMouseClicked(mouseEvent -> {System.exit(0);});
        quitGame.setMinHeight(50);
        quitGame.setMinWidth(200);
        quitGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        quitGame.setStyle("-fx-background-radius: 20;");
        vbox.getChildren().add(quitGame);
    }

    public void initialVbox(){
        setVbox(new VBox(20));
        getVbox().setAlignment(Pos.CENTER_LEFT);
        getVbox().setPadding(new Insets(0, 0, 0, 150));
        getChildren().add(getVbox());
    }
    public void playEffect(int i) {
        soundW.setFile(i);
        soundW.play();
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public VBox getVbox() {
        return vbox;
    }
}
