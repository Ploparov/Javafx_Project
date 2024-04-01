import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pane.MainMenu;
import pane.RootPane;
import pane.map;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        Scene scene = new Scene(map.getMap(), 700, 700);
        Scene scene = new Scene(RootPane.getRootPane(), 1500, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
