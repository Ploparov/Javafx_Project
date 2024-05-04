package item.component;

import Game.Player;
import Interface.activeAble;
import Interface.taskAble;
import Sound.SoundMP3;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.TimerManager;

public class Rider extends GroupObjectActivable implements taskAble, activeAble {

    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    private SoundMP3 sound = new SoundMP3();
    public Rider() {
        super("Component/Rider/foodcat.png");
        alert();
        getAlert().setVisible(false);
        getInstance().setVisible(false);
        setLastUpdateTime(System.nanoTime());
    }

    @Override
    public void Active() {
        setIsAlert(false);
        getAlert().setVisible(false);
        this.setVisible(false);
    }

    @Override
    public void taskAlert() {
        setCurrentWaitFrameIndex(0);
        setIsAlert(true);
        this.setVisible(false);
        getAlert().setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - getLastUpdateTime();
                if (elapsedTime >= 500_000_000) {
                    getAlert().setImage(new Image(getWaitRedImage()[getCurrentWaitFrameIndex() % 17]));
                    setCurrentWaitFrameIndex(getCurrentWaitFrameIndex()+1);
                    getInstance().setVisible(true);
                    if (getCurrentWaitFrameIndex() >= 17) {
                        Player.getInstance().decreaseHearts();
                        setCurrentWaitFrameIndex(0);
                        getAlert().setVisible(false);
                        getInstance().setVisible(false);
                        stop();
                        stopEffect();
                    }
                    else if(!isAlert()){
                        setCurrentWaitFrameIndex(0);
                        setIsAlert(true);
                        stop();
                        stopEffect();
                    }
                    setLastUpdateTime(now);
                }
            }
        };
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6 + Math.random()), event -> {
            this.setVisible(true);
            timer.start();
            getAlert().setVisible(true);
            getInstance().setImage(new Image("Component/Rider/foodcat.png"));
            playEffect(1);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    public void alert(){
        setAlert(new ImageView("UI/Wait/WaitRed/WaitRed1.png"));
        getAlert().setFitWidth(200);
        getAlert().setFitHeight(200);
        getAlert().setTranslateX(200);
        getAlert().setTranslateY(-100);
        getChildren().add(getAlert());
    }
    public void playEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
    public void stopEffect(){
        sound.stop();
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
