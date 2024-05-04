package item.component;

import Game.Player;
import Interface.activeAble;
import Interface.taskAble;
import Sound.SoundW;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.TimerManager;

public class Sink extends GroupObjectActivable implements taskAble, activeAble {
    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int holdAction = 0;
    private int currentWaitFrameIndex = 0;
    private int currentActiveFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    private final String[] waitBlueImage = {"UI/Wait/WaitBlue/WaitBlue1.png", "UI/Wait/WaitBlue/WaitBlue2.png", "UI/Wait/WaitBlue/WaitBlue3.png", "UI/Wait/WaitBlue/WaitBlue4.png", "UI/Wait/WaitBlue/WaitBlue5.png", "UI/Wait/WaitBlue/WaitBlue6.png", "UI/Wait/WaitBlue/WaitBlue7.png", "UI/Wait/WaitBlue/WaitBlue8.png", "UI/Wait/WaitBlue/WaitBlue9.png", "UI/Wait/WaitBlue/WaitBlue10.png", "UI/Wait/WaitBlue/WaitBlue11.png", "UI/Wait/WaitBlue/WaitBlue12.png", "UI/Wait/WaitBlue/WaitBlue13.png", "UI/Wait/WaitBlue/WaitBlue14.png", "UI/Wait/WaitBlue/WaitBlue15.png", "UI/Wait/WaitBlue/WaitBlue16.png", "UI/Wait/WaitBlue/WaitBlue17.png"};
    private SoundW soundW = new SoundW();
    public Sink() {
        super("Component/Sink/sink_withoutbowl.png");
        alert();
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
        setIsAlert(true);
        this.getInstance().setImage(new Image("Component/Sink/sink_withoutbowl.png"));
        getAlert().setVisible(false);
        getAlert().setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(isAlert()){AlertAnimation(now);}
                else {ActiveAnimation();}
            }
        };
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5 + Math.random()*10), event -> {
            setIsAlert(true);
            timer.start();
            playEffect(1);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        TimerManager.getInstance().addTimer(timer);
        TimerManager.getInstance().addTimeline(timeline);
    }

    public void AlertAnimation(long now){

        getAlert().setVisible(true);
        getInstance().setImage(new Image("Component/Sink/sink_withbowl.png"));
        long elapsedTime = now - getLastUpdateTime();
        if (elapsedTime >= 500_000_000) {
            getAlert().setImage(new Image(getWaitRedImage()[getCurrentWaitFrameIndex() % 17]));
            setCurrentWaitFrameIndex(getCurrentWaitFrameIndex() + 1);
            if (getCurrentWaitFrameIndex() >= 17) {
                Player.getInstance().decreaseHearts();
                setCurrentWaitFrameIndex(0);
                getAlert().setVisible(false);
            }
            setLastUpdateTime(now);
        }

    }

    public void ActiveAnimation(){
            if(getHoldAction() >= 20){
                setCurrentActiveFrameIndex(getCurrentActiveFrameIndex() + 1);
                setHoldAction(0);
            }
           getAlert().setImage(new Image(getWaitBlueImage()[getCurrentActiveFrameIndex() % 17]));
            if (getCurrentActiveFrameIndex() >= 17) {
                setCurrentActiveFrameIndex(0);
                setCurrentWaitFrameIndex(0);
                getAlert().setVisible(false);
                getInstance().setImage(new Image("Component/Sink/sink_withoutbowl.png"));
            }
    }

    @Override
    public void Active() {
        setIsAlert(false);
        setHoldAction(getHoldAction() + 1);
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

    public int getHoldAction() {
        return holdAction;
    }

    public void setHoldAction(int holdAction) {
        this.holdAction = holdAction;
    }

    public int getCurrentWaitFrameIndex() {
        return currentWaitFrameIndex;
    }

    public void setCurrentWaitFrameIndex(int currentWaitFrameIndex) {
        this.currentWaitFrameIndex = currentWaitFrameIndex;
    }

    public int getCurrentActiveFrameIndex() {
        return currentActiveFrameIndex;
    }

    public void setCurrentActiveFrameIndex(int currentActiveFrameIndex) {
        this.currentActiveFrameIndex = currentActiveFrameIndex;
    }

    public String[] getWaitRedImage() {
        return waitRedImage;
    }

    public String[] getWaitBlueImage() {
        return waitBlueImage;
    }
}
