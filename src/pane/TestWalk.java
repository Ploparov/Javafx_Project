package pane;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import Game.Player;

import java.util.Arrays;
import java.util.Iterator;

public class TestWalk extends StackPane {
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


    public TestWalk() {


        System.out.println("testwalk");
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setFocusTraversable(true);
        getChildren().add(player);
        setKeyHandlers();
        startAnimation();

    }

    public void setKeyHandlers() {
        this.setOnKeyPressed(event -> {
            long currentTime = System.nanoTime();
            if (event.getCode() == KeyCode.W) {
                movingUp = true;
                lastPressedTime = currentTime;
                System.out.println("true");
            } else if (event.getCode() == KeyCode.A) {
                movingLeft = true;
                lastPressedTime = currentTime;
                System.out.println("true");
            } else if (event.getCode() == KeyCode.S) {
                movingDown = true;
                lastPressedTime = currentTime;
                System.out.println("true");
            } else if (event.getCode() == KeyCode.D) {
                movingRight = true;
                lastPressedTime = currentTime;
                System.out.println("true");
            }
        });

        this.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.W) {
                movingUp = false;
                System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.A) {
                movingLeft = false;
                System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.S) {
                movingDown = false;
                System.out.println("false");
                currentFrameIndex = 0;
            } else if (event.getCode() == KeyCode.D) {
                movingRight = false;
                System.out.println("false");
                currentFrameIndex = 0;
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
            }
        };
        timer.start();
    }

    public  void ifAnimationFrontDown(long now){
        if (movingUp || movingDown) {
            long elapsedTime = now - lastPressedTime;
            if (elapsedTime >= 20_000_000) { // 100_000_000 คือ 0.1 วินาทีในหน่วย nano seconds
                // ตรวจสอบให้แน่ใจว่า currentFrameIndex ไม่เกินขนาดของอาร์เรย์ CR_front
                System.out.println(currentFrameIndex);
                System.out.println(player.CR_front[currentFrameIndex % 8]);
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
                System.out.println(currentFrameIndex);
                System.out.println(player.CR_side_right[currentFrameIndex % 10]);
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
                System.out.println(currentFrameIndex);
                System.out.println(player.CR_side_left[currentFrameIndex % 10]);
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
                System.out.println(currentFrameIndex);
                System.out.println(player.CIdle[currentFrameIndex % 2]);
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
        System.out.println("x"+player.getTranslateX());
    }
}
