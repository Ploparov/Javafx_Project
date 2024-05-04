package item.component;

import Game.Player;
import Interface.activeAble;
import Interface.taskAble;
import item.GroupObjectActivable;
import Sound.SoundW;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.TimerManager;

public class WaterOnTheFloor extends GroupObjectActivable implements taskAble, activeAble {
    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    private SoundW soundW = new SoundW();
    public WaterOnTheFloor() {
        super("Component/WaterOnTheFloor/waterOnthefloor.png");
        alert();
        this.setVisible(true);
        setLastUpdateTime(System.nanoTime());
    }

    public void alert(){
        setAlert(new ImageView("UI/Wait/WaitRed/WaitRed1.png"));
        getAlert().setFitWidth(200);
        getAlert().setFitHeight(200);
        getAlert().setTranslateX(200);
        getAlert().setTranslateY(-100);
        getChildren().add(getAlert());
        getAlert().setVisible(false);
    }
    @Override
    public void taskAlert() {
        setCurrentWaitFrameIndex(0);
        setIsAlert(true);
        getAlert().setVisible(false);
        this.setVisible(false);
        getAlert().setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - getLastUpdateTime();
                if (elapsedTime >= 500_000_000) {
                    getAlert().setImage(new Image(getWaitRedImage()[getCurrentWaitFrameIndex() % 17]));
                    setCurrentWaitFrameIndex(getCurrentWaitFrameIndex() + 1);
                    if (getCurrentWaitFrameIndex() >= 17) {
                        Player.getInstance().decreaseHearts();
                        setCurrentWaitFrameIndex(0);
                        getAlert().setVisible(true);
                        stop();
                    }
                    else if(!isAlert()){
                        setCurrentWaitFrameIndex(0);
                        setIsAlert(true);
                        stop();
                    }
                    setLastUpdateTime(now);
                }
            }
        };
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3 + Math.random()), event -> {
            this.setVisible(true);
            timer.start();
            getAlert().setVisible(true);
            getInstance().setImage(new Image("Component/WaterOnTheFloor/waterOnthefloor.png"));
            playEffect(2);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    @Override
    public void Active() {
        setIsAlert(false);
        getAlert().setVisible(false);
        this.setVisible(false);
    }
    public void playEffect(int i) {
        soundW.setFile(i);
        soundW.play();
    }


    public ImageView getAlert() {
        return alert;
    }

    public void setAlert(ImageView alert) {
        this.alert = alert;
    }

    public boolean isAlert() {
        return isAlert;
    }

    public void setIsAlert(boolean alert) {
        isAlert = alert;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getCurrentWaitFrameIndex() {
        return currentWaitFrameIndex;
    }

    public void setCurrentWaitFrameIndex(int currentWaitFrameIndex) {
        this.currentWaitFrameIndex = currentWaitFrameIndex;
    }

    public String[] getWaitRedImage() {
        return waitRedImage;
    }
}