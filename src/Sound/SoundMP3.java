package Sound;

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
        soundURL[3] = getClass().getResource("/SFX/ThemeSong.m4a");
        soundURL[4] = getClass().getResource("/SFX/beep.mp3");
    }

    public void setFile(int i) {
        Media sound = new Media(soundURL[i].toString());
        mediaPlayer = new MediaPlayer(sound);
    }
    public void play(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }
}
