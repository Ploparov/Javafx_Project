
package item.component;

import Game.Player;
import Sound.SoundMP3;
import item.GroupObjectActivable;
import Interface.taskAble;
import Interface.activeAble;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.TimerManager;

public class Bin extends GroupObjectActivable implements taskAble,activeAble{
    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    private double multiply = 0.9;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};

    private SoundMP3 sound = new SoundMP3();
    public Bin() {
        super("Component/bin/bin1.png");
        alert();
        getAlert().setVisible(false);
        setLastUpdateTime(System.nanoTime());
    }

    @Override
    public void taskAlert() {
        setCurrentWaitFrameIndex(0);
        setIsAlert(true);
        this.getInstance().setImage(new Image("Component/bin/bin1.png"));
        getAlert().setVisible(false);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - getLastUpdateTime();
                if (elapsedTime >= 500_000_000 * getMultiply()) {
                    setCurrentWaitFrameIndex(getCurrentWaitFrameIndex()+1);
                    getAlert().setImage(new Image(getWaitRedImage()[getCurrentWaitFrameIndex() % 17]));
                    if (getCurrentWaitFrameIndex() >= 17) {
                        Player.getInstance().decreaseHearts();
                        setCurrentWaitFrameIndex(0);
                        getAlert().setVisible(true);
                        getInstance().setImage(new Image("Component/bin/bin2.png"));
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
                timer.start();
                getAlert().setVisible(true);
                getInstance().setImage(new Image("Component/bin/bin2.png"));
                playEffect(2);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    public void alert(){
        setAlert(new ImageView("UI/Wait/WaitRed/WaitRed1.png"));
        getAlert().setFitWidth(250);
        getAlert().setFitHeight(250);
        getAlert().setTranslateX(200);
        getAlert().setTranslateY(-100);
        getChildren().add(getAlert());
    }

    @Override
    public void Active() {
        if(isAlert()) {
            setMultiply(getMultiply()*0.9);
            setIsAlert(false);
            getAlert().setVisible(false);
            this.getInstance().setImage(new Image("Component/bin/bin1.png"));
        }
    }

    public void playEffect(int i) {
        sound.setFile(i);
        sound.play();
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

    public double getMultiply() {
        return multiply;
    }

    public void setMultiply(double multiply) {
        this.multiply = multiply;
    }

    public String[] getWaitRedImage() {
        return waitRedImage;
    }

}
