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
import pane.SoundMP3;
import pane.SoundW;
import utils.TimerManager;


public class GasStove extends GroupObjectActivable implements taskAble, activeAble {

    private final ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int holdAction = 0;
    private int currentWaitFrameIndex = 0;
    private int currentActiveFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    private final String[] waitBlueImage = {"UI/Wait/WaitBlue/WaitBlue1.png", "UI/Wait/WaitBlue/WaitBlue2.png", "UI/Wait/WaitBlue/WaitBlue3.png", "UI/Wait/WaitBlue/WaitBlue4.png", "UI/Wait/WaitBlue/WaitBlue5.png", "UI/Wait/WaitBlue/WaitBlue6.png", "UI/Wait/WaitBlue/WaitBlue7.png", "UI/Wait/WaitBlue/WaitBlue8.png", "UI/Wait/WaitBlue/WaitBlue9.png", "UI/Wait/WaitBlue/WaitBlue10.png", "UI/Wait/WaitBlue/WaitBlue11.png", "UI/Wait/WaitBlue/WaitBlue12.png", "UI/Wait/WaitBlue/WaitBlue13.png", "UI/Wait/WaitBlue/WaitBlue14.png", "UI/Wait/WaitBlue/WaitBlue15.png", "UI/Wait/WaitBlue/WaitBlue16.png", "UI/Wait/WaitBlue/WaitBlue17.png"};
    SoundMP3 sound = new SoundMP3();
    public GasStove() {
        super("Component/GasStove/GasStove1.png");
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
        isAlert = true;
        this.instance.setImage(new Image("Component/GasStove/GasStove1.png"));
        alert.setVisible(false);
        alert.setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(isAlert){AlertAnimation(now);}
                else {ActiveAnimation();}
            }
        };
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5 + Math.random()*10), event -> {
            isAlert= true;
            timer.start();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // The Timeline will loop indefinitely
        timeline.play();

        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    public void AlertAnimation(long now){

        alert.setVisible(true);

        long elapsedTime = now - lastUpdateTime;
        if (elapsedTime >= 500_000_000) { // 100 milliseconds in nanoseconds
            alert.setImage(new Image(waitRedImage[currentWaitFrameIndex % 17]));
            currentWaitFrameIndex++;
            if (currentWaitFrameIndex >= 17) {
                Player.getInstance().decreaseHearts();
                instance.setImage(new Image("Component/GasStove/GasStove1.png"));
                currentWaitFrameIndex = 0;
                alert.setVisible(false);
            }
            lastUpdateTime = now; // Reset the last update time for timing
        }
    }

    public void ActiveAnimation(){
        if(currentWaitFrameIndex==0){instance.setImage(new Image("Component/GasStove/GasStove1.png"));}
        if(holdAction >= 20) {
            playEffect(1);
            currentActiveFrameIndex++;
            instance.setImage(new Image("Component/GasStove/GasStove2.png"));
            holdAction = 0;
        }
        alert.setImage(new Image(waitBlueImage[currentActiveFrameIndex % 17]));
        if (currentActiveFrameIndex >= 17) {
            instance.setImage(new Image("Component/GasStove/GasStove1.png"));
            currentActiveFrameIndex = 0;
            currentWaitFrameIndex = 0;
            alert.setVisible(false);
        }
    }

    @Override
    public void Active() {
        isAlert = false;
        holdAction++;
    }
    public void playEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
    public void stopEffect(){
        sound.stop();
    }
}