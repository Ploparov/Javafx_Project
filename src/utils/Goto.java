package utils;

import pane.*;

public class Goto {
    private static RootPane rootPane;
    public static void setPane(RootPane pane){
        Goto.rootPane = pane;
    }
    public static void MainMenuPage(){
        clear();
        rootPane.getChildren().add(new MainMenu());
    }
    public static void gameOverPage(int score){
        clear();
        rootPane.getChildren().add(new GameOver(score));
    }
    public static void mapPage(){
        clear();
        rootPane.getChildren().add(new Map());
    }
    public static void test(){
        clear();
        rootPane.getChildren().add(new GameMap());
    }
    public static void clear(){
        rootPane.getChildren().clear();
    }
}
