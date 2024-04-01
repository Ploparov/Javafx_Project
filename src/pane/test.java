package pane;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import utils.Goto;

public class test extends StackPane {
    private static test instance;

    public test(){

//        getChildren().add(start);
    }

    public static test getMainMenu(){
        if(instance == null){
            instance = new test();
        }
        return instance;
    }

}

