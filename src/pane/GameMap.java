package pane;

import item.GroupObjectActivable;
import item.Object;
import item.component.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import Game.Player;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import utils.Goto;
import utils.TimerManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

    public class GameMap extends StackPane {
        private final int tileSize = 48;
    final int screenWidth = tileSize * 16;
    final int screenHeight = tileSize * 12;
    public Player player = Player.getInstance();
    private long lastPressedTime = 0;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private int currentFrameIndex = 0;
    private GroupObjectActivable clothBucket;
    private ImageView buttonE;
    private WashingMachine washingMachine;
    private Bin bin;
    private Sink sink;
    private WaterOnTheFloor waterOnTheFloor;
    private Rider rider;
    private GasStove gasStove;
    private boolean isPressE = false;
    private int scoreTime = 0;
    private Timeline gameTimer;
    private List<ImageView> hearts;
    private final Text timeText = new Text();
    private final double minX = -285.0;
    private final double maxX = 280.0;
    private final double minY = -80.0;
    private final double maxY = 140.0;
    private static GameMap instance;
    public GameMap() {
        HouseFloor();
        WallBack();
        window();
        curtain();
        startGameTimer();
        washingMachine();
        ClothBucket();
        bin();
        sink();
        waterOnFloor();
        gasStove();
        initialHearts();
        updateHearts();
        setScreen();
        setKeyHandlers();
        startAnimation();
        playerStartPosition();
        WallFront();
        rider();
        ButtonE();
        score();
        shadows();
    }


    public static GameMap getInstance() {
        if (instance == null) {
            setInstance(new GameMap());
        }
        return instance;
    }

    public void updateHearts() {
        getChildren().removeAll(getHearts());
        getHearts().clear();
        Image heartImage = new Image("Component/Heart/heart.png");
        for (int i = 0; i < getPlayer().getHearts(); i++) {
            ImageView heart = new ImageView(heartImage);
            heart.setFitWidth(60);
            heart.setFitHeight(60);
            heart.setTranslateX(-700 + i * 60);
            heart.setTranslateY(-350);
            getHearts().add(heart);
        }
        getChildren().addAll(getHearts());
    }

    public void setKeyHandlers() {
        this.setOnKeyPressed(event -> {
            long currentTime = System.nanoTime();
            if (event.getCode() == KeyCode.W) {
                setMovingUp(true);
                setLastPressedTime(currentTime);
            } else if (event.getCode() == KeyCode.A) {
                setMovingLeft(true);
                setLastPressedTime(currentTime);
            } else if (event.getCode() == KeyCode.S) {
                setMovingDown(true);
                setLastPressedTime(currentTime);
            } else if (event.getCode() == KeyCode.D) {
                setMovingRight(true);
                setLastPressedTime(currentTime);
            } else if (event.getCode() == KeyCode.E){
                getButtonE().setImage(new Image("UI/ebutton/E_Button2.png"));
                setPressE(true);
        }
        });

        this.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.W) {
                setMovingUp(false);
                setCurrentFrameIndex(0);
            } else if (event.getCode() == KeyCode.A) {
                setMovingLeft(false);
                setCurrentFrameIndex(0);
            } else if (event.getCode() == KeyCode.S) {
                setMovingDown(false);
                setCurrentFrameIndex(0);
            } else if (event.getCode() == KeyCode.D) {
                setMovingRight(false);
                setCurrentFrameIndex(0);
            } else if (event.getCode() == KeyCode.E){
                getButtonE().setImage(new Image("UI/ebutton/E_Button1.png"));
                setPressE(false);
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
                showE();
                if (Player.getInstance().getHearts() < getHearts().size()) {
                    updateHearts();
                }
                if(Player.getInstance().getHearts()==0){
                    TimerManager.getInstance().stopAll();
                    Goto.gameOverPage(getScoreTime());
                }
            }
        };
        timer.start();

        TimerManager.getInstance().addTimer(timer);
    }

    private void startGameTimer() {
        setGameTimer(new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            setScoreTime(getScoreTime()+10);
            getTimeText().setText("Score: " + getScoreTime());
        })));
        getGameTimer().setCycleCount(Timeline.INDEFINITE);
        getGameTimer().play();
        TimerManager.getInstance().addTimeline(getGameTimer());
    }

    public int getScoreTime() {
        return scoreTime;
    }

    public void resetScore() {
        scoreTime = 0;
    }

    public void showE(){
        if(getClothBucket().Canselect(getPlayer()) || getWashingMachine().Canselect(getPlayer()) || getBin().Canselect(getPlayer()) || getSink().Canselect(getPlayer()) || getWaterOnTheFloor().Canselect(getPlayer()) || getGasStove().Canselect(getPlayer()) || getRider().Canselect(getPlayer())){
            getButtonE().setVisible(true);
            if(isPressE()){
                if(getWashingMachine().Canselect(getPlayer())){getWashingMachine().Active();}
                else if (getBin().Canselect(getPlayer())) { getBin().Active(); }
                else if (getSink().Canselect(getPlayer())) { getSink().Active(); }
                else if (getGasStove().Canselect(getPlayer())) { getGasStove().Active(); }
                else if (getRider().Canselect(getPlayer())) { getRider().Active(); }
                else if (getWaterOnTheFloor().Canselect(getPlayer())) {
                    getWaterOnTheFloor().setTranslateX(getMinX() + (Math.random() * (getMaxX() - getMinX())));
                    getWaterOnTheFloor().setTranslateY(getMinY() + (Math.random() * (getMaxY() - getMinY())));
                    getWaterOnTheFloor().Active();
                }
            }
        }else {
            getButtonE().setVisible(false);
        }
    }

    public  void ifAnimationFrontDown(long now){
        if (isMovingUp() || isMovingDown()) {
            long elapsedTime = now - getLastPressedTime();
            if (elapsedTime >= 20_000_000) {
                getPlayer().setImage(new Image(getPlayer().CR_front[getCurrentFrameIndex() % 8]));
                setCurrentFrameIndex(getCurrentFrameIndex()+1);
                setLastPressedTime(now);
            }
        }
    }

    public  void ifAnimationSideRight(long now){
        if ( isMovingRight() ) {
            long elapsedTime = now - getLastPressedTime();
            if (elapsedTime >= 20_000_000) {
                getPlayer().setImage(new Image(getPlayer().CR_side_right[getCurrentFrameIndex() % 10]));
                setCurrentFrameIndex(getCurrentFrameIndex()+1);
                setLastPressedTime(now);
            }
        }
    }
    public  void ifAnimationSideLeft(long now){
        if (isMovingLeft()) {
            long elapsedTime = now - getLastPressedTime();
            if (elapsedTime >= 20_000_000) {
                getPlayer().setImage(new Image((getPlayer().CR_side_left[getCurrentFrameIndex() % 10])));
                setCurrentFrameIndex(getCurrentFrameIndex()+1);
                setLastPressedTime(now);
            }
        }
    }
    public  void ifAnimationIdle(long now){
        if ( !(isMovingLeft() || isMovingRight() || isMovingUp() || isMovingDown()) ) {
            long elapsedTime = now - getLastPressedTime();
            if (elapsedTime >= 500_000_000) {
                getPlayer().setImage(new Image(getPlayer().CIdle[getCurrentFrameIndex() % 2]));
                setCurrentFrameIndex(getCurrentFrameIndex()+1);
                setLastPressedTime(now);
            }
        }
    }

    public void movePlayer() {
        if (isMovingUp() && (((getPlayer().getTranslateY() >= -140 && getPlayer().getTranslateX() <= 360) || ((getPlayer().getTranslateY()>=-30) && (getPlayer().getTranslateX()>=360 && getPlayer().getTranslateX()<=560))) || (getPlayer().getTranslateX()>=560)
                )){
            getPlayer().setTranslateY(getPlayer().getTranslateY() - 5);
        }
        if (isMovingDown() && ((getPlayer().getTranslateY() < 365) && !(getPlayer().getTranslateX()>450 && getPlayer().getTranslateX()<560) || (getPlayer().getTranslateY()>-40 && getPlayer().getTranslateY()<25))) {
            getPlayer().setTranslateY(getPlayer().getTranslateY() + 5);
        }
        if (isMovingLeft() && (getPlayer().getTranslateX()>-360) && (getPlayer().getTranslateX()!=560 || (getPlayer().getTranslateY()>-40 && getPlayer().getTranslateY()<25))){
            getPlayer().setTranslateX(getPlayer().getTranslateX() - 5);
        }
        if (isMovingRight() && ((getPlayer().getTranslateX()<325) || (!(getPlayer().getTranslateY()>=-145 && getPlayer().getTranslateY()<-40)) && ((getPlayer().getTranslateX()!=450)||(getPlayer().getTranslateY()>-40 && getPlayer().getTranslateY()<25)))) {
            getPlayer().setTranslateX(getPlayer().getTranslateX() + 5);
        }

    }

    public void WallBack(){
        place("Component/Wall/wall_noborder.png",1000,250,0,-250);
        place("Component/Wall/wall_side.png",200,250,500,-250);
        place("Component/Wall/wall_side_border.png",200,200,500,-100);
        place("Component/Wall/door.png",200,250,430,-75);
    }

    public void WallFront(){
        place("Component/Wall/wall_noborder.png",1000,250,0,400);
        place("Component/Wall/wall_side.png",200,400,500,200);
        place("Component/Wall/wall_side.png",200,850,-500,50);

    }
    public  void HouseFloor(){
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-400,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-200,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,0,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,200,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,400,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-350);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,-150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-150);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,-50);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,-50);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,-50);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,150);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,150);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,150);

        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-800,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,-600,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-400,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,-200,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,0,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,200,350);
        place("Component/Floor/HouseFloor/HouseFloor.png",200,200,400,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,600,350);
        place("Component/Floor/GrassFloor/GrassFloor.png",200,200,800,350);
    }

    public void shadows(){
        place("Component/Shadow/Shadow01.png",345,211,-310,-231,0.4);
        place("Component/Shadow/Shadow01.png",345,211,310,-231,0.4);
        place("Component/Shadow/Shadow02.png",60,80,-107,-165,0.4);
        place("Component/Shadow/Shadow05.png",60,80,107,-165,0.4);
        place("Component/Shadow/Shadow02.png",210,280,-240,14.2,0.3);
        place("Component/Shadow/Shadow01.png",136,280,-413.5,14.2,0.3);
        place("Component/Shadow/Shadow05.png",210,280,243,14.2,0.3);
        place("Component/Shadow/Shadow01.png",136,280,415,14.2,0.3);
        place("Component/Shadow/Shadow01.png",966,130,2,220,0.2);
        place("Component/Shadow/Shadow04.png",300,800,670,200,0.2);
    }

    public void place(String s,double w,double h,double x,double y){
//        ImageView obj = new ImageView(s);
        Object obj = new Object(s);
        obj.setFitWidth(w);
        obj.setFitHeight(h);
        obj.setTranslateX(x);
        obj.setTranslateY(y);

        getChildren().add(obj);

    }

    public void place(String s,double w,double h,double x,double y,double o){
        Object obj = new Object(s);
        obj.setFitWidth(w);
        obj.setFitHeight(h);
        obj.setTranslateX(x);
        obj.setTranslateY(y);
        obj.setOpacity(o);
        getChildren().add(obj);
    }

    public void window(){
        ImageView window = new ImageView("Component/Wall/Window.png");
        window.setFitWidth(280);
        window.setFitHeight(140);
        window.setTranslateY(-270);
        getChildren().add(window);
    }

    public void curtain(){
        ImageView curtain = new ImageView("Component/Wall/curtain.png");
        curtain.setFitWidth(280);
        curtain.setFitHeight(140);
        curtain.setTranslateY(-270);
        getChildren().add(curtain);
    }

    public void washingMachine(){
        setWashingMachine(new WashingMachine());
        getWashingMachine().setScaleX(0.2);
        getWashingMachine().setScaleY(0.2);
        getWashingMachine().setTranslateY(-150);
        getWashingMachine().setTranslateX(300);
        getChildren().add(getWashingMachine());
    }

    public void bin(){
        setBin(new Bin());
        getBin().setScaleX(0.2);
        getBin().setScaleY(0.2);
        getBin().setTranslateX(-125);
        getBin().setTranslateY(-150);
        getChildren().add(getBin());
        getBin().taskAlert();
    }

    public void sink(){
        setSink(new Sink());
        getSink().setScaleX(0.3);
        getSink().setScaleY(0.3);
        getSink().setTranslateX(-250);
        getSink().setTranslateY(-175);
        getChildren().add(getSink());
        getSink().taskAlert();
    }

    public void waterOnFloor(){
        setWaterOnTheFloor(new WaterOnTheFloor());
        getWaterOnTheFloor().setScaleX(0.2);
        getWaterOnTheFloor().setScaleY(0.2);
        getWaterOnTheFloor().setTranslateX(getMinX() + (Math.random() * (getMaxX() - getMinX())));
        getWaterOnTheFloor().setTranslateY(getMinY() + (Math.random() * (getMaxY() - getMinY())));
        getChildren().add(getWaterOnTheFloor());
        getWaterOnTheFloor().taskAlert();
    }

   public void gasStove(){
       setGasStove(new GasStove());
       getGasStove().setScaleX(0.3);
       getGasStove().setScaleY(0.3);
       getGasStove().setTranslateX(-440);
       getGasStove().setTranslateY(0);
       getChildren().add(getGasStove());
       getGasStove().taskAlert();
   }

   public void ClothBucket(){
       setClothBucket(new GroupObjectActivable("Component/WashingMachine/ClothBucket.png"));
       getClothBucket().setTranslateX(150);
       getClothBucket().setTranslateY(-145);
       getClothBucket().setScaleX(0.2);
       getClothBucket().setScaleY(0.2);
       getChildren().add(getClothBucket());
   }

   public void rider(){
       setRider(new Rider());
       getRider().setScaleX(0.4);
       getRider().setScaleY(0.4);
       getRider().setTranslateX(650);
       getRider().setTranslateY(150);
       getChildren().add(getRider());
       getRider().taskAlert();
   }

   public void ButtonE(){
       setButtonE(new ImageView("UI/ebutton/E_Button1.png"));
       getButtonE().setFitWidth(100);
       getButtonE().setFitHeight(100);
       getButtonE().setTranslateX(0);
       getButtonE().setTranslateY(300);
       getChildren().add(getButtonE());
       getButtonE().setVisible(false);
   }

   public void score(){
       getChildren().add(getTimeText());
       getTimeText().setTranslateX(630);
       getTimeText().setTranslateY(-350);
       getTimeText().setScaleX(2);
       getTimeText().setScaleY(2);
       getTimeText().setText("Score: " + 0);
       getTimeText().setFont(Font.font("Arial", FontWeight.BOLD, 16));
   }

   public void playerStartPosition(){
       getPlayer().setTranslateX(0);
       getPlayer().setTranslateY(0);
       getChildren().add(getPlayer());
   }

   public void setScreen(){
       this.setWidth(getScreenWidth());
       this.setHeight(getScreenHeight());
       this.setFocusTraversable(true);
   }

   public void initialHearts(){
       Player.getInstance().setHearts(3);
       setHearts(new ArrayList<>());
   }


    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Player getPlayer() {
        return player;
    }

    public long getLastPressedTime() {
        return lastPressedTime;
    }

    public void setLastPressedTime(long lastPressedTime) {
        this.lastPressedTime = lastPressedTime;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public void setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
    }

    public GroupObjectActivable getClothBucket() {
        return clothBucket;
    }

    public void setClothBucket(GroupObjectActivable clothBucket) {
        this.clothBucket = clothBucket;
    }

    public ImageView getButtonE() {
        return buttonE;
    }

    public void setButtonE(ImageView buttonE) {
        this.buttonE = buttonE;
    }

    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public Sink getSink() {
        return sink;
    }

    public void setSink(Sink sink) {
        this.sink = sink;
    }

    public WaterOnTheFloor getWaterOnTheFloor() {
        return waterOnTheFloor;
    }

    public void setWaterOnTheFloor(WaterOnTheFloor waterOnTheFloor) {
        this.waterOnTheFloor = waterOnTheFloor;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public GasStove getGasStove() {
        return gasStove;
    }

    public void setGasStove(GasStove gasStove) {
        this.gasStove = gasStove;
    }

    public boolean isPressE() {
        return isPressE;
    }

    public void setPressE(boolean pressE) {
        isPressE = pressE;
    }

    public void setScoreTime(int scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Timeline getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(Timeline gameTimer) {
        this.gameTimer = gameTimer;
    }

    public List<ImageView> getHearts() {
        return hearts;
    }

    public void setHearts(List<ImageView> hearts) {
        this.hearts = hearts;
    }


    public Text getTimeText() {
        return timeText;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public static void setInstance(GameMap instance) {
        GameMap.instance = instance;
    }
}
