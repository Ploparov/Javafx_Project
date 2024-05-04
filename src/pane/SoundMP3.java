package pane;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundMP3 {

    MediaPlayer mediaPlayer;

    URL[] soundURL = new URL[10];
    public SoundMP3(){
        soundURL[0] = getClass().getResource("/SFX/Cooking.mp3");
        soundURL[1] = getClass().getResource("/SFX/Motorcycle.mp3");
        soundURL[2] = getClass().getResource("/SFX/PaperBin.mp3");
    }

    public void setFile(int i) {
        try {
            Media sound = new Media(soundURL[i].toString());
            mediaPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            System.out.println("music file not found " + soundURL[i]);
            e.printStackTrace();
        }
    }
    public void play(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }
}
