package pane;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import utils.Goto;

public class MainMenu extends StackPane {
    private static MainMenu instance;

    public MainMenu(){
//        Button start = new Button("Start");
//        start.setOnMouseClicked(mouseEvent -> {Goto.mapPage();});
//        getChildren().add(start);

        Button test = new Button("test");
        test.setOnMouseClicked(mouseEvent -> {Goto.test();});
        getChildren().add(test);
    }

    public static MainMenu getMainMenu(){
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

}
