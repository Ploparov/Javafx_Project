package item.component;

import Interface.activeAble;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class washingMachine extends GroupObjectActivable implements activeAble {
    private final ImageView wait;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    boolean active = false;
    private final String[] waitBlueImage = {"UI/Wait/WaitBlue/WaitBlue1.png", "UI/Wait/WaitBlue/WaitBlue2.png", "UI/Wait/WaitBlue/WaitBlue3.png", "UI/Wait/WaitBlue/WaitBlue4.png", "UI/Wait/WaitBlue/WaitBlue5.png", "UI/Wait/WaitBlue/WaitBlue6.png", "UI/Wait/WaitBlue/WaitBlue7.png", "UI/Wait/WaitBlue/WaitBlue8.png", "UI/Wait/WaitBlue/WaitBlue9.png", "UI/Wait/WaitBlue/WaitBlue10.png", "UI/Wait/WaitBlue/WaitBlue11.png", "UI/Wait/WaitBlue/WaitBlue12.png", "UI/Wait/WaitBlue/WaitBlue13.png", "UI/Wait/WaitBlue/WaitBlue14.png", "UI/Wait/WaitBlue/WaitBlue15.png", "UI/Wait/WaitBlue/WaitBlue16.png", "UI/Wait/WaitBlue/WaitBlue17.png"};

    public washingMachine() {
        super("Component/WashingMachine/WashingMachine.png");

        wait = new ImageView("UI/Wait/WaitBlue/WaitBlue1.png");
        wait.setFitWidth(250);
        wait.setFitHeight(250);
        wait.setTranslateX(200);
        wait.setTranslateY(-100);
        getChildren().add(wait);
        wait.setVisible(false);

        lastUpdateTime = System.nanoTime();
    }

    public void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - lastUpdateTime;
                if (elapsedTime >= 100_000_000) { // 20 วินาทีในหน่วย nano seconds
                    wait.setImage(new Image(waitBlueImage[currentWaitFrameIndex%17]));
                    currentWaitFrameIndex++;
                    if (currentWaitFrameIndex >= 17) {
                        active = false;
                        currentWaitFrameIndex = 0;
                        wait.setVisible(false);

                        stop();
                    }
                    lastUpdateTime = now; // รีเซ็ตเวลาล่าสุดเพื่อนับเวลาใหม่
                }
            }
        };
        timer.start();
    }

    @Override
    public void Active() {
        if(!active) {
            active = true;
            wait.setVisible(true);
            startAnimation();
        }
    }
}


