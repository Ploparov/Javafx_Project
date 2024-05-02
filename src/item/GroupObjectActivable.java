package item;

import Game.Player;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;

public class GroupObjectActivable extends Group {
    public Object instance;
    public GroupObjectActivable(String name){
        setInstance(new Object(name));
        getInstance().setTranslateY(0);
        getInstance().setTranslateX(0);
        getChildren().add(getInstance());
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

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
