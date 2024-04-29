package pane;

import item.MapGroup;
import javafx.scene.layout.StackPane;

public class Map extends StackPane{
    private static Map instance;
    public Map(){
        place("university","University.png",0,0);
        place("university","Lake.png",175,72.5);
        place("university","Forest.png",-175,72.5);
        place("university","University.png",0,145);
    }
    private void place(String type,String image,double x,double y){
        MapGroup mapgroup = new MapGroup(type,image);
        mapgroup.setTranslateX(x);
        mapgroup.setTranslateY(y);
        getChildren().add(mapgroup);
    }
    public static Map getMap(){
        if(instance == null){instance = new Map();}
        return instance;
    }
}
