package item.component;

import Interface.activeAble;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WashingMachine extends GroupObjectActivable implements activeAble {
    private ImageView wait;
    private long lastUpdateTime;
    private int currentWaitFrameIndex = 0;
    boolean active = false;
    private final String[] waitBlueImage = {"UI/Wait/WaitBlue/WaitBlue1.png", "UI/Wait/WaitBlue/WaitBlue2.png", "UI/Wait/WaitBlue/WaitBlue3.png", "UI/Wait/WaitBlue/WaitBlue4.png", "UI/Wait/WaitBlue/WaitBlue5.png", "UI/Wait/WaitBlue/WaitBlue6.png", "UI/Wait/WaitBlue/WaitBlue7.png", "UI/Wait/WaitBlue/WaitBlue8.png", "UI/Wait/WaitBlue/WaitBlue9.png", "UI/Wait/WaitBlue/WaitBlue10.png", "UI/Wait/WaitBlue/WaitBlue11.png", "UI/Wait/WaitBlue/WaitBlue12.png", "UI/Wait/WaitBlue/WaitBlue13.png", "UI/Wait/WaitBlue/WaitBlue14.png", "UI/Wait/WaitBlue/WaitBlue15.png", "UI/Wait/WaitBlue/WaitBlue16.png", "UI/Wait/WaitBlue/WaitBlue17.png"};

    public WashingMachine() {
        super("Component/WashingMachine/WashingMachine.png");
        blueWait();
        getWait().setVisible(false);
        setLastUpdateTime(System.nanoTime());
    }

    public void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - getLastUpdateTime();
                if (elapsedTime >= 100_000_000) {
                    getWait().setImage(new Image(getWaitBlueImage()[getCurrentWaitFrameIndex()%17]));
                    setCurrentWaitFrameIndex(getCurrentWaitFrameIndex()+1);
                    if (getCurrentWaitFrameIndex() >= 17) {
                        setActive(false);
                        setCurrentWaitFrameIndex(0);
                        getWait().setVisible(false);
                        stop();
                    }
                    setLastUpdateTime(now);
                }
            }
        };
        timer.start();
    }
    
    public void blueWait(){
        setWait(new ImageView(new Image(getWaitBlueImage()[0])));
        getWait().setFitWidth(250);
        getWait().setFitHeight(250);
        getWait().setTranslateX(200);
        getWait().setTranslateY(-100);
        getChildren().add(getWait());    
    }

    @Override
    public void Active() {
        if(!isActive()) {
            setActive(true);
            getWait().setVisible(true);
            startAnimation();
        }
    }

    public ImageView getWait() {
        return wait;
    }
    
    public void setWait(ImageView wait) {
        this.wait = wait;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String[] getWaitBlueImage() {
        return waitBlueImage;
    }
}


