package item;

import javafx.css.Size;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class MapPart extends ImageView {
    private String Type;
    private String Owner;
    private boolean HaveOwner;
    private int count;
    private String imagestring;

    public MapPart(String type,String image){
        super(image);
        setType(type);
        setImagestring(image);
        setPreserveRatio(true);
        setFitHeight(300);
        setFitWidth(300);

    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public boolean isHaveOwner() {
        return HaveOwner;
    }

    public void setHaveOwner(boolean haveOwner) {
        HaveOwner = haveOwner;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImagestring() {
        return imagestring;
    }

    public void setImagestring(String imagestring) {
        this.imagestring = imagestring;
    }
}
