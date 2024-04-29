package item;

import javafx.scene.image.ImageView;

public class MapPart extends ImageView {
    private String type;
    private String imagestring;
    public MapPart(String type,String image){
        super(image);
        setType(type);
        setImagestring(image);
        setPreserveRatio(true);
        setFitHeight(300);
        setFitWidth(300);
    }
    public void setType(String type) {
        type = type;
    }
    public void setImagestring(String imagestring) {
        this.imagestring = imagestring;
    }
}
