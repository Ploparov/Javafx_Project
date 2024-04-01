package item;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class MapGroup extends Group {
    private String Type;
    private String Owner;
    private boolean HaveOwner;
    private int count;
    public MapGroup(String type,String image){

        HaveOwner = false;

        getChildren().add(new MapPart(type,image));
        setOnMouseEntered(mouseEvent -> {
            setTranslateY(getTranslateY()-30);
        });

        setOnMouseExited(mouseEvent -> {
            setTranslateY(getTranslateY()+30);
        });

        setOnMouseClicked(mouseEvent -> {
            Click();
        });
    }

    private void Click(){
        if(!HaveOwner){
            Occupy();
            HaveOwner = true;
            System.out.println("ooc");
        }

    }

    private void Occupy(){
        //ตอนทำจริงเอาImage circle textเป็นตัวแปรตั้งต้นไปเลย
        Image image = new Image("infj.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        imageView.setTranslateX(70);
        imageView.setTranslateY(70);

        getChildren().add(imageView);

        ////////////
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.LIGHTGREEN);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        circle.setTranslateX(200);
        circle.setTranslateY(150);
        getChildren().add(circle);
        /////////////
        count = 1;
        Text text = new Text(Integer.toString(count));
        text.setFont(new Font(30));

        text.setTranslateX(190);
        text.setTranslateY(160);
        getChildren().add(text);
        ////////////

    }
}
