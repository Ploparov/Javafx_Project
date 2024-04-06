package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends ImageView {
    public String[] CR_front = {"Cat/CatFront/runfrontpng1.png", "Cat/CatFront/runfrontpng2.png", "Cat/CatFront/runfrontpng3.png","Cat/CatFront/runfrontpng4.png","Cat/CatFront/runfrontpng5.png","Cat/CatFront/runfrontpng6.png","Cat/CatFront/runfrontpng7.png","Cat/CatFront/runfrontpng8.png"};
    public String[] CR_side_right = {"Cat/CatSide/runsidepng1.png", "Cat/CatSide/runsidepng2.png", "Cat/CatSide/runsidepng3.png","Cat/CatSide/runsidepng4.png","Cat/CatSide/runsidepng5.png","Cat/CatSide/runsidepng6.png","Cat/CatSide/runsidepng7.png","Cat/CatSide/runsidepng8.png","Cat/CatSide/runsidepng9.png","Cat/CatSide/runsidepng10.png"};
    public String[] CR_side_left = {"Cat/CatSide/mirror_runsidepng1.png", "Cat/CatSide/mirror_runsidepng2.png", "Cat/CatSide/mirror_runsidepng3.png","Cat/CatSide/mirror_runsidepng4.png","Cat/CatSide/mirror_runsidepng5.png","Cat/CatSide/mirror_runsidepng6.png","Cat/CatSide/mirror_runsidepng7.png","Cat/CatSide/mirror_runsidepng8.png","Cat/CatSide/mirror_runsidepng9.png","Cat/CatSide/mirror_runsidepng10.png"};
    public String[] CIdle = {"Cat/CatIdle/idle1.png","Cat/CatIdle/idle2.png"};
    private int speed = 5;


    public Player() {
        super("Cat/CatFront/runfrontpng1.png");
        setFitHeight(200);
        setFitWidth(100);
        setDefaultValues();


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


}
