package item.component;

import item.GroupObjectActivable;
import Interface.taskAble;
import Interface.activeAble;
import item.Object;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bin extends GroupObjectActivable implements taskAble,activeAble{
    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};


    public Bin() {
        super("Component/bin/bin1.png");

        alert = new ImageView("UI/Wait/WaitRed/WaitRed1.png");
        alert.setFitWidth(250);
        alert.setFitHeight(250);
        alert.setTranslateX(200);
        alert.setTranslateY(-100);
        getChildren().add(alert);
        alert.setVisible(false);




        lastUpdateTime = System.nanoTime();
    }

    @Override
    public void taskAlert() {
        isAlert = true;
        this.instance.setImage(new Image("Component/bin/bin2.png"));
        alert.setVisible(true);
        alert.setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - lastUpdateTime;
                if (elapsedTime >= 500_000_000) { // 100 milliseconds in nanoseconds
                    alert.setImage(new Image(waitRedImage[currentWaitFrameIndex % 17]));
                    currentWaitFrameIndex++;
                    if (currentWaitFrameIndex >= 17) {
                        currentWaitFrameIndex = 0;
                        alert.setVisible(false);
                        stop(); // Stop the AnimationTimer
                    }
                    lastUpdateTime = now; // Reset the last update time for timing
                }
            }
        };
        timer.start();
    }

    @Override
    public void Active() {
        isAlert = false;
        alert.setVisible(false);
        this.instance.setImage(new Image("Component/bin/bin1.png"));
    }
}
