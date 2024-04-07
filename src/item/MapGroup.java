package item;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class MapGroup extends Group {
    private String Type;
    private String Owner;
    private boolean HaveOwner;
    private int count;
    private Rectangle hitbox;
    public MapGroup(String type,String image,double width,double height){

        Object object = new Object(image);
        object.setFitWidth(width);
        object.setFitHeight(height);
        getChildren().add(object);

        hitbox = new Rectangle(width, height);
        hitbox.setStroke(Color.BLUE); // Set the stroke color to blue
        hitbox.setFill(Color.TRANSPARENT); // Set the fill color to transparent

        // Add the hitbox to the group
        getChildren().add(hitbox);
    }

    public void setHitboxPosition(double x, double y) {
        hitbox.setTranslateX(x);
        hitbox.setTranslateY(y);
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
}
