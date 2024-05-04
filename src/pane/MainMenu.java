package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

import java.io.File;

public class MainMenu extends StackPane {
    private static MainMenu instance;
    SoundW soundW = new SoundW();

    public MainMenu(){
        playEffect(0);
//        Button start = new Button("Start");
//        start.setOnMouseClicked(mouseEvent -> {Goto.mapPage();});
//        getChildren().add(start);
//        LinearGradient linearGradient = new LinearGradient(
//                0, // start X
//                0, // start Y
//                1, // end X
//                1, // end Y
//                true, // proportional
//                javafx.scene.paint.CycleMethod.NO_CYCLE, // cycle colors
//                new Stop(0.0, Color.RED),
//                new Stop(1/6.0, Color.ORANGE),
//                new Stop(2/6.0, Color.YELLOW),
//                new Stop(3/6.0, Color.GREEN),
//                new Stop(4/6.0, Color.BLUE),
//                new Stop(5/6.0, Color.INDIGO),
//                new Stop(1.0, Color.VIOLET)
//        );
        VBox vbox = new VBox(20); // Create a VBox layout with a spacing of 20
        vbox.setAlignment(Pos.CENTER); // Center

//        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
//        Background background = new Background(backgroundFill);
//        this.setBackground(background);

        Image image = new Image("Background/kittenChoreBackground.png"); // Replace with the actual path to your image
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        this.setBackground(new Background(backgroundImage));

        Label titleLabel = new Label("Kitten Chore");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // Set the font to Arial, bold, size 50
        titleLabel.setTextFill(Color.BLACK); // Set the text color to dark blue

        // Add drop shadow effect to create a stroke-like effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GOLD); // Set the shadow color to white
        dropShadow.setRadius(10); // Set the shadow radius
        titleLabel.setEffect(dropShadow); // Apply the drop shadow effect
        // Position the label a bit upper than the center
        titleLabel.setTranslateY(-50); // Adjust this value as needed

        // Center the label horizontally
        StackPane.setAlignment(titleLabel, Pos.CENTER);

        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(mouseEvent -> {Goto.test();});
        startGame.setMinHeight(100);
        startGame.setMinWidth(200);
        startGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        startGame.setStyle("-fx-background-radius: 20;");

        Button quitGame = new Button("Quit Game");
        quitGame.setOnMouseClicked(mouseEvent -> {System.exit(0);}); // Exit the application when the button is clicked
        quitGame.setMinHeight(50);
        quitGame.setMinWidth(200);
        quitGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        quitGame.setStyle("-fx-background-radius: 20;");

//        String path = getClass().getResource("Grean.mp3").getPath();
//        Button soundButton = new Button("Play pane.Sound");
//        Media sound = new Media(new File(path).toURI().toString()); // Replace with the actual path to your sound file
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);


//        soundButton.setOnAction(event -> {
//            mediaPlayer.stop(); // Stop the sound if it's currently playing
//            mediaPlayer.play(); // Play the sound
//        });
//        getChildren().add(soundButton);
//
//// Add the quit game button to the VBox
        vbox.getChildren().addAll(titleLabel, startGame, quitGame);
        getChildren().add(vbox);
    }

    public static MainMenu getMainMenu(){
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;

    }
    public void playEffect(int i) {
        soundW.setFile(i);
        soundW.play();
    }
    public void stopEffect(){
        soundW.stop();
    }

}
