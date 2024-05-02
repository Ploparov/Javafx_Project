package pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundW {
   Clip clip;

    URL[] soundURL = new URL[10];
    public SoundW(){
        soundURL[0] = getClass().getResource("/SFX/Cat.wav");
        soundURL[1] = getClass().getResource("/SFX/HotPan.wav");
        soundURL[2] = getClass().getResource("/SFX/Plate.wav");
        soundURL[3] = getClass().getResource("/SFX/Rider.wav");
        soundURL[4] = getClass().getResource("/SFX/Water.wav");
        soundURL[5] = getClass().getResource("/SFX/Bin.m4a");
            }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip =AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
}
