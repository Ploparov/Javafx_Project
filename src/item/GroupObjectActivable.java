package item;

import Game.Player;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GroupObjectActivable extends Group {
    public Object instance;
    public ImageView button;
    public GroupObjectActivable(String name){
        Object i = new Object(name);
        i.setTranslateY(0);
        i.setTranslateX(0);
        getChildren().add(i);

//        button = new ImageView("UI/ebutton/E_Button1.png");
//        button.setFitWidth(200);
//        button.setFitHeight(200);
//        button.setTranslateX(200);
//        button.setTranslateY(-550);
//        getChildren().add(button);
//        button.setVisible(false);
    }
    public boolean Canselect(Player player){
        if(
                player.getTranslateX() >= this.getTranslateX() -100
                        && player.getTranslateX() <= this.getTranslateX() +100
                        && player.getTranslateY() >= this.getTranslateY() - 100
                        && player.getTranslateY() <= this.getTranslateY() + 100
        ){
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.2); // Adjust brightness
            colorAdjust.setContrast(0.1); // Adjust contrast
            this.setEffect(colorAdjust);


            return true;
        }else {
            this.setEffect(null);

            return false;
        }
    }
}
