package item;

import Game.Player;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;

public class GroupObjectActivable extends Group {
    public Object instance;
    public GroupObjectActivable(String name){
        instance = new Object(name);
        instance.setTranslateY(0);
        instance.setTranslateX(0);
        getChildren().add(instance);
    }
    public boolean Canselect(Player player){
        if(player.getTranslateX() >= this.getTranslateX() -80
        && player.getTranslateX() <= this.getTranslateX() +80
        && player.getTranslateY() >= this.getTranslateY() - 100
        && player.getTranslateY() <= this.getTranslateY() + 100)
        {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.2); // Adjust brightness
            colorAdjust.setContrast(0.1); // Adjust contrast
            this.setEffect(colorAdjust);
            return true;
        }
        else {
            this.setEffect(null);
            return false;
        }
    }
}
