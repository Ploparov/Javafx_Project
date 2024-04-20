package pane;

import item.GroupObjectActivable;
import item.Object;
import item.component.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import Game.Player;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;
import utils.TimerManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class GameMap extends StackPane {
    final int tileSize = 48; //16*16*3
    final int screenWidth = tileSize * 16;
    final int screenHeight = tileSize * 12;

    Player player = Player.getInstance();

    private long lastPressedTime = 0;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    int currentFrameIndex = 0;
    private GroupObjectActivable clothbucket;

    private ImageView buttonE;
    private washingMachine washingmachine;
    private Bin bin;
    private Sink sink;
    private waterOnTheFloor wateronthefloor;
    private rider rider;
    private GasStove gasStove;
    boolean isPressE = false;

    private List<ImageView> hearts;

    private List<AnimationTimer> timers = new ArrayList<>();

    double minX = -285.0; // Minimum x value
    double maxX = 280.0; // Maximum x value
    double minY = -80.0; // Minimum y value
    double maxY = 140.0; // Maximum y value


    public GameMap() {
        HouseFloor();
        WallBack();

        Player.getInstance().setHearts(3);

//        place("Component/WashingMachine/WashingMachine.png",150,150,0,-150);
//        place("Component/WashingMachine/ClothBucket.png",150,150,150,-145);
        washingmachine = new washingMachine();
        washingmachine.setScaleX(0.2);
        washingmachine.setScaleY(0.2);
        washingmachine.setTranslateY(-150);
        getChildren().add(washingmachine);

        bin = new Bin();
        bin.setScaleX(0.2);
        bin.setScaleY(0.2);
        bin.setTranslateX(-125);
        bin.setTranslateY(-150);
        getChildren().add(bin);
        bin.taskAlert();

        sink = new Sink();
        sink.setScaleX(0.3);
        sink.setScaleY(0.3);
        sink.setTranslateX(-250);
        sink.setTranslateY(-175);
        getChildren().add(sink);
        sink.taskAlert();

        wateronthefloor = new waterOnTheFloor();
        wateronthefloor.setScaleX(0.2);
        wateronthefloor.setScaleY(0.2);
        wateronthefloor.setTranslateX(minX + (Math.random() * (maxX - minX)));
        wateronthefloor.setTranslateY(minY + (Math.random() * (maxY - minY)));
        getChildren().add(wateronthefloor);
        wateronthefloor.taskAlert();

        gasStove = new GasStove();
        gasStove.setScaleX(0.3);
        gasStove.setScaleY(0.3);
        gasStove.setTranslateX(-440);
        gasStove.setTranslateY(0);
        getChildren().add(gasStove);
        gasStove.taskAlert();



        clothbucket = new GroupObjectActivable("Component/WashingMachine/ClothBucket.png");
        clothbucket.setTranslateX(150);
        clothbucket.setTranslateY(-145);
        clothbucket.setScaleX(0.2);
        clothbucket.setScaleY(0.2);
        getChildren().add(clothbucket);

        //add score
//        getChildren().add(player.getScoreText());

        // Initialize the hearts list
        hearts = new ArrayList<>();

        // Add the initial number of hearts to the list
        updateHearts();

        //System.out.println("testwalk");
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        getChildren().add(player);
        setKeyHandlers();
        startAnimation();


        WallFront();

        rider = new rider();
        rider.setScaleX(0.4);
        rider.setScaleY(0.4);
        rider.setTranslateX(650);
        rider.setTranslateY(150);
        getChildren().add(rider);
        rider.taskAlert();

        buttonE = new ImageView("UI/ebutton/E_Button1.png");
        buttonE.setFitWidth(100);
        buttonE.setFitHeight(100);
        buttonE.setTranslateX(0);
        buttonE.setTranslateY(300);
        getChildren().add(buttonE);
        buttonE.setVisible(false);

    }

    public void updateHearts() {
        // Remove all heart images from the game map
        getChildren().removeAll(hearts);

        // Clear the hearts list
        hearts.clear();

        // Load the heart image
        Image heartImage = new Image("Component/Heart/heart.png");

        // Add the current number of hearts to the list
        for (int i = 0; i < player.getHearts(); i++) {
            // Create a new ImageView object for the heart
            ImageView heart = new ImageView(heartImage);

            // Set the size of the heart
            heart.setFitWidth(60);
            heart.setFitHeight(60);

            // Position the heart in the top right corner
            heart.setTranslateX(-700 + i * 60); // Adjust these values as needed
            heart.setTranslateY(-350);

            // Add the heart to the hearts list
            hearts.add(heart);
        }

        // Add the hearts to the game map
        getChildren().addAll(hearts);
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
                isPressE = true;
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
                isPressE = false;
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

                // Check if the player's hearts have decreased
                if (Player.getInstance().getHearts() < hearts.size()) {
                    // Update the hearts on the game map
                    updateHearts();
                }
                if(Player.getInstance().getHearts()==0){
                    TimerManager.getInstance().stopAll();
                    Goto.gameOverPage();
                }

            }
        };
        timer.start();

        TimerManager.getInstance().addTimer(timer);
    }

    public void stopAllTimers() {
        for (AnimationTimer timer : timers) {
            timer.stop();
        }
    }

    public void showE(){
        if(clothbucket.Canselect(player) || washingmachine.Canselect(player) || bin.Canselect(player) || sink.Canselect(player) || wateronthefloor.Canselect(player) || gasStove.Canselect(player) || rider.Canselect(player) || gasStove.Canselect(player) || rider.Canselect(player) ){
            buttonE.setVisible(true);
            if(isPressE){
                if(washingmachine.Canselect(player)){washingmachine.Active();}
                else if (bin.Canselect(player)) { bin.Active(); }
                else if (sink.Canselect(player)) { sink.Active(); }
                else if (gasStove.Canselect(player)) { gasStove.Active(); }
                else if (rider.Canselect(player)) { rider.Active(); }
                else if (wateronthefloor.Canselect(player)) {
                    wateronthefloor.setTranslateX(minX + (Math.random() * (maxX - minX)));
                    wateronthefloor.setTranslateY(minY + (Math.random() * (maxY - minY)));
                    wateronthefloor.Active();
                }
            }
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
        System.out.println("X : "+player.getTranslateX());
        System.out.println("Y : "+player.getTranslateY());
    }

    public void WallBack(){
        place("Component/Wall/wall_noborder.png",1000,250,0,-250);
//        place("Component/Wall/wall_side.png",200,150,-500,-300);
//        place("Component/Wall/wall_side_border.png",200,200,-500,-200);
        place("Component/Wall/wall_side.png",200,250,500,-250);
        place("Component/Wall/wall_side_border.png",200,200,500,-100);
        place("Component/Wall/door.png",200,250,430,-75);
    }

    public void WallFront(){
        place("Component/Wall/wall_noborder.png",1000,250,0,400);
        place("Component/Wall/wall_side.png",200,400,500,200);
        place("Component/Wall/wall_side.png",200,850,-500,50);

    }
    public  void HouseFloor(){
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-400,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-200,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,0,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,200,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,400,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-350);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-150);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-50);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,150);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,350);
    }


    public void place(String s,double w,double h,double x,double y){
//        ImageView obj = new ImageView(s);
        Object obj = new Object(s);
        obj.setFitWidth(w);
        obj.setFitHeight(h);
        obj.setTranslateX(x);
        obj.setTranslateY(y);

        getChildren().add(obj);

    }


}
