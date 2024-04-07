package pane;

import item.GroupObjectActivable;
import item.Object;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import Game.Player;
import item.MapGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.stream.Collectors;

public class GameMap extends StackPane {
    final int tileSize = 48; //16*16*3
    final int screenWidth = tileSize * 16;
    final int screenHeight = tileSize * 12;

    Player player = new Player();

    private long lastPressedTime = 0;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    int currentFrameIndex = 0;

    private GroupObjectActivable clothbucket;
    private Rectangle hitbox;


    private ImageView buttonE;
    private boolean isPressE;

    public GameMap() {
        WallBack();

        place("Component/WashingMachine/WashingMachine.png",150,150,0,-150);
        place("Component/WashingMachine/ClothBucket.png",150,150,150,-145);
        clothbucket = new GroupObjectActivable("Component/WashingMachine/ClothBucket.png");
        clothbucket.setTranslateX(150);
        clothbucket.setTranslateY(-145);
        clothbucket.setScaleX(0.2);
        clothbucket.setScaleY(0.2);
        getChildren().add(clothbucket);



        System.out.println("testwalk");
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);


        getChildren().add(player);
        getChildren().add(player.getHitbox());
        setKeyHandlers();
        startAnimation();

        WallFront();

        buttonE = new ImageView("UI/ebutton/E_Button1.png");
        buttonE.setFitWidth(100);
        buttonE.setFitHeight(100);
        buttonE.setTranslateX(0);
        buttonE.setTranslateY(300);
        getChildren().add(buttonE);
        buttonE.setVisible(false);
        isPressE = false;
    }

    public void setKeyHandlers() {
        this.setOnKeyPressed(event -> {
            long currentTime = System.nanoTime();
            if (event.getCode() == KeyCode.W) {
                movingUp = true;
                lastPressedTime = currentTime;
                //System.out.println("true");
            } else if (event.getCode() == KeyCode.A) {
                movingLeft = true;
                lastPressedTime = currentTime;
                //System.out.println("true");
            } else if (event.getCode() == KeyCode.S) {
                movingDown = true;
                lastPressedTime = currentTime;
                //System.out.println("true");
            } else if (event.getCode() == KeyCode.D) {
                movingRight = true;
                lastPressedTime = currentTime;
                //System.out.println("true");
            } else if (event.getCode() == KeyCode.E){
                buttonE.setImage(new Image("UI/ebutton/E_Button2.png"));
        }
        });

        this.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.W) {
                movingUp = false;
                //System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.A) {
                movingLeft = false;
                //System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.S) {
                movingDown = false;
                //System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.D) {
                movingRight = false;
                //System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.E){
                buttonE.setImage(new Image("UI/ebutton/E_Button1.png"));
            }
        });
    }

    public void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
                ifAnimationFrontDown(now);
                ifAnimationSideRight(now);
                ifAnimationSideLeft(now);
                ifAnimationIdle(now);
                showE();

                // Get a list of all MapGroup objects
                List<MapGroup> mapGroups = getChildren().stream()
                        .filter(child -> child instanceof MapGroup)
                        .map(child -> (MapGroup) child)
                        .collect(Collectors.toList());

                // Check for collisions with the player
                for (MapGroup mapGroup : mapGroups) {
                    if (player.collidesWith(mapGroup)) {
                        // Handle collision
                        System.out.println("Collision detected with " + mapGroup);
                    }
//                    else{
//                        System.out.println("No Collision detected with " + mapGroup);
//                    }
                }
            }
        };
        timer.start();
    }

    public void showE(){
        if(clothbucket.Canselect(player)){
            buttonE.setVisible(true);
        }else {
            buttonE.setVisible(false);
        }
    }

    public  void ifAnimationFrontDown(long now){
        if (movingUp || movingDown) {
            long elapsedTime = now - lastPressedTime;
            if (elapsedTime >= 20_000_000) { // 100_000_000 คือ 0.1 วินาทีในหน่วย nano seconds
                // ตรวจสอบให้แน่ใจว่า currentFrameIndex ไม่เกินขนาดของอาร์เรย์ CR_front
                //System.out.println(currentFrameIndex);
                //System.out.println(player.CR_front[currentFrameIndex % 8]);
                player.setImage(new Image(player.CR_front[currentFrameIndex % 8]));
                currentFrameIndex++;

                lastPressedTime = now; // รีเซ็ตเวลาล่าสุดเพื่อนับเวลาใหม่
            }
        }
    }

    public  void ifAnimationSideRight(long now){
        if ( movingRight ) {
            long elapsedTime = now - lastPressedTime;
            if (elapsedTime >= 20_000_000) { // 100_000_000 คือ 0.1 วินาทีในหน่วย nano seconds

                // ตรวจสอบให้แน่ใจว่า currentFrameIndex ไม่เกินขนาดของอาร์เรย์ CR_front
                //System.out.println(currentFrameIndex);
                //System.out.println(player.CR_side_right[currentFrameIndex % 10]);
                player.setImage(new Image(player.CR_side_right[currentFrameIndex % 10]));
                currentFrameIndex++;


                lastPressedTime = now; // รีเซ็ตเวลาล่าสุดเพื่อนับเวลาใหม่
            }
        }
    }

    public  void ifAnimationSideLeft(long now){
        if (movingLeft) {
            long elapsedTime = now - lastPressedTime;
            if (elapsedTime >= 20_000_000) { // 100_000_000 คือ 0.1 วินาทีในหน่วย nano seconds

                // ตรวจสอบให้แน่ใจว่า currentFrameIndex ไม่เกินขนาดของอาร์เรย์ CR_front
                //System.out.println(currentFrameIndex);
                //System.out.println(player.CR_side_left[currentFrameIndex % 10]);
                player.setImage(new Image(player.CR_side_left[currentFrameIndex % 10]));
                currentFrameIndex++;


                lastPressedTime = now; // รีเซ็ตเวลาล่าสุดเพื่อนับเวลาใหม่
            }
        }
    }
    public  void ifAnimationIdle(long now){
        if ( !(movingLeft || movingRight || movingUp || movingDown) ) {
            long elapsedTime = now - lastPressedTime;
            if (elapsedTime >= 500_000_000) { // 100_000_000 คือ 0.1 วินาทีในหน่วย nano seconds

                // ตรวจสอบให้แน่ใจว่า currentFrameIndex ไม่เกินขนาดของอาร์เรย์ CR_front
                //System.out.println(currentFrameIndex);
                //System.out.println(player.CIdle[currentFrameIndex % 2]);
                player.setImage(new Image(player.CIdle[currentFrameIndex % 2]));
                currentFrameIndex++;


                lastPressedTime = now; // รีเซ็ตเวลาล่าสุดเพื่อนับเวลาใหม่
            }
        }
    }

    public void movePlayer() {
        if (movingUp) {
            player.setTranslateY(player.getTranslateY() - 5);
        }
        if (movingDown) {
            player.setTranslateY(player.getTranslateY() + 5);
        }
        if (movingLeft) {
            player.setTranslateX(player.getTranslateX() - 5);
        }
        if (movingRight) {
            player.setTranslateX(player.getTranslateX() + 5);
        }
//        System.out.println("X : "+player.getTranslateX());
//        System.out.println("Y : "+player.getTranslateY());
    }

    public void WallBack(){
        place("Component/Wall/wall_noborder.png",1000,250,0,-250);
        place("Component/Wall/wall_side.png",200,150,-500,-300);
        place("Component/Wall/wall_side_border.png",200,200,-500,-200);
        place("Component/Wall/wall_side.png",200,250,500,-250);
        place("Component/Wall/wall_side_border.png",200,200,500,-100);
    }

    public void WallFront(){
        place("Component/Wall/wall_side.png",200,500,-500,150);
        place("Component/Wall/wall_noborder.png",1000,250,0,400);

    }


    public void place(String s,double w,double h,double x,double y){
//        ImageView obj = new ImageView(s);
        MapGroup obj = new MapGroup(s,s,w,h);
        obj.setTranslateX(x);
        obj.setTranslateY(y);
        hitbox = new Rectangle();
        hitbox.setStroke(Color.BLUE); // Set the stroke color to blue
        hitbox.setFill(Color.TRANSPARENT); // Set the fill color to transparent
        hitbox.setHeight(h);
        hitbox.setWidth(w);

        // Add the hitbox to the group
        getChildren().add(hitbox);

        getChildren().add(obj);

    }
}
