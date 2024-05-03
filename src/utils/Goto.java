package utils;

import pane.*;

public class Goto {
    private static RootPane rootPane;
    public static void setPane(RootPane pane){
        Goto.rootPane = pane;
    }
    public static void MainMenuPage(){
        clear();
        getRootPane().getChildren().add(new MainMenu());
    }
    public static void gameOverPage(int score){
        clear();
        getRootPane().getChildren().add(new GameOver(score));
    }
    public static void gameMap(){
        clear();
        getRootPane().getChildren().add(new GameMap());
    }
    public static void clear(){
        getRootPane().getChildren().clear();
    }

    public static RootPane getRootPane() {
        return rootPane;
    }
}
