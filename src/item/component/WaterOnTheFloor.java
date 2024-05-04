package item.component;

import Game.Player;
import Interface.activeAble;
import Interface.taskAble;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import pane.SoundW;
import utils.TimerManager;

public class WaterOnTheFloor extends GroupObjectActivable implements taskAble, activeAble {
    private final ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    SoundW soundW = new SoundW();
    public WaterOnTheFloor() {
        super("Component/WaterOnTheFloor/waterOnthefloor.png");
        this.setVisible(true);
        alert = new ImageView("UI/Wait/WaitRed/WaitRed1.png");
        alert.setFitWidth(200);
        alert.setFitHeight(200);
        alert.setTranslateX(200);
        alert.setTranslateY(-100);
        getChildren().add(alert);
        alert.setVisible(false);
        lastUpdateTime = System.nanoTime();
    }
    @Override
    public void taskAlert() {
        currentWaitFrameIndex = 0;
        isAlert = true;
        alert.setVisible(false);
        this.setVisible(false);
        alert.setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - lastUpdateTime;
                if (elapsedTime >= 500_000_000) { // 100 milliseconds in nanoseconds
                    alert.setImage(new Image(waitRedImage[currentWaitFrameIndex % 17]));
                    currentWaitFrameIndex++;
                    if (currentWaitFrameIndex >= 17) {
                        Player.getInstance().decreaseHearts();
                        currentWaitFrameIndex = 0;
                        alert.setVisible(true);
                        stop();
                    }
                    else if(!isAlert){
                        currentWaitFrameIndex = 0;
                        isAlert = true;
                        stop();
                    }
                    lastUpdateTime = now; // Reset the last update time for timing
                }
            }
        };
        // Define the Timeline with a KeyFrame that starts the AnimationTimer every 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3 + Math.random()), event -> {
            this.setVisible(true);
            timer.start();
            alert.setVisible(true);
            instance.setImage(new Image("Component/WaterOnTheFloor/waterOnthefloor.png"));
            playEffect(2);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // The Timeline will loop indefinitely
        timeline.play();

        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    @Override
    public void Active() {
        isAlert = false;
        alert.setVisible(false);
        this.setVisible(false);
    }
    public void playEffect(int i) {
        soundW.setFile(i);
        soundW.play();
    }
    public void stopEffect(){
        soundW.stop();
    }
}