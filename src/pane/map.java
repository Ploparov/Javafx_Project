package pane;

import item.MapGroup;
import item.MapPart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class map extends StackPane{
    private static map instance;
    public map(){


        place("university","University.png",0,0);
        place("university","Lake.png",175,72.5);
        place("university","Forest.png",-175,72.5);
        place("university","University.png",0,145);

//        getChildren().add(new MapGroup("img","University.png"));
//        getChildren().add(new MapGroup("img","University.png"));



    }

    private void place(String type,String image,double x,double y){
        MapGroup mapgroup = new MapGroup(type,image);
        mapgroup.setTranslateX(x);
        mapgroup.setTranslateY(y);
        getChildren().add(mapgroup);

    }

    public static map getMap(){
        if(instance == null){
            instance = new map();
        }
        return instance;
    }

}
