package item;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class MapGroup extends Group {
    private boolean haveOwner;
    private int count;
    public MapGroup(String type,String image){
        haveOwner = false;
        getChildren().add(new MapPart(type,image));
        setOnMouseEntered(mouseEvent -> {setTranslateY(getTranslateY()-30);});
        setOnMouseExited(mouseEvent -> {setTranslateY(getTranslateY()+30);});
        setOnMouseClicked(mouseEvent -> {Click();});
    }

    private void Click(){
        if(!haveOwner){Occupy();haveOwner = true;}
    }
    private void Occupy(){
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.LIGHTGREEN);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setTranslateX(200);
        circle.setTranslateY(150);
        getChildren().add(circle);

        count = 1;
        Text text = new Text(Integer.toString(count));
        text.setFont(new Font(30));
        text.setTranslateX(190);
        text.setTranslateY(160);
        getChildren().add(text);
    }
}
