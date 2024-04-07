package Game;

import item.MapGroup;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player extends ImageView {
    public String[] CR_front = {"Cat/CatFront/runfrontpng1.png", "Cat/CatFront/runfrontpng2.png", "Cat/CatFront/runfrontpng3.png","Cat/CatFront/runfrontpng4.png","Cat/CatFront/runfrontpng5.png","Cat/CatFront/runfrontpng6.png","Cat/CatFront/runfrontpng7.png","Cat/CatFront/runfrontpng8.png"};
    public String[] CR_side_right = {"Cat/CatSide/runsidepng1.png", "Cat/CatSide/runsidepng2.png", "Cat/CatSide/runsidepng3.png","Cat/CatSide/runsidepng4.png","Cat/CatSide/runsidepng5.png","Cat/CatSide/runsidepng6.png","Cat/CatSide/runsidepng7.png","Cat/CatSide/runsidepng8.png","Cat/CatSide/runsidepng9.png","Cat/CatSide/runsidepng10.png"};
    public String[] CR_side_left = {"Cat/CatSide/mirror_runsidepng1.png", "Cat/CatSide/mirror_runsidepng2.png", "Cat/CatSide/mirror_runsidepng3.png","Cat/CatSide/mirror_runsidepng4.png","Cat/CatSide/mirror_runsidepng5.png","Cat/CatSide/mirror_runsidepng6.png","Cat/CatSide/mirror_runsidepng7.png","Cat/CatSide/mirror_runsidepng8.png","Cat/CatSide/mirror_runsidepng9.png","Cat/CatSide/mirror_runsidepng10.png"};
    public String[] CIdle = {"Cat/CatIdle/idle1.png","Cat/CatIdle/idle2.png"};
    private int speed = 5;

    private Rectangle hitbox;

    private double hitboxX;
    private double hitboxY;


    public Player() {
        super("Cat/CatFront/runfrontpng1.png");
        setFitHeight(200);
        setFitWidth(100);
        setDefaultValues();

        hitbox = new Rectangle(getFitWidth(), getFitHeight());
        hitbox.setTranslateX(getHitboxX());
        hitbox.setTranslateY(getHitboxY());
        hitbox.setStroke(Color.RED);
        hitbox.setFill(Color.TRANSPARENT);


        // Add the hitbox to the player's parent group
        this.parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                ((Group) newParent).getChildren().add(hitbox);
            }
            if (oldParent != null) {
                ((Group) oldParent).getChildren().remove(hitbox);
            }
        });

        this.translateXProperty().addListener((obs, oldX, newX) -> hitbox.setTranslateX(newX.doubleValue()));
        this.translateYProperty().addListener((obs, oldY, newY) -> hitbox.setTranslateY(newY.doubleValue()));
    }

    public boolean collidesWith(MapGroup other) {
        //System.out.println("FFFFFFFFFFFFF");
        Shape intersection = Shape.intersect(this.hitbox, other.getHitbox());
        return !intersection.getBoundsInLocal().isEmpty();
    }


    public void setDefaultValues() {
        setSpeed(4);
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public double getHitboxX() {
        return hitboxX;
    }

    public void setHitboxX(double hitboxX) {
        this.hitboxX = hitboxX;
    }

    public double getHitboxY() {
        return hitboxY;
    }

    public void setHitboxY(double hitboxY) {
        this.hitboxY = hitboxY;
    }
}
