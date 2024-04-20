package utils;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import java.util.ArrayList;
import java.util.List;

public class TimerManager {
    private static TimerManager instance;
    private List<AnimationTimer> timers;
    private List<Timeline> timelines;

    private TimerManager() {
        timers = new ArrayList<>();
        timelines = new ArrayList<>();
    }

    public static TimerManager getInstance() {
        if (instance == null) {
            instance = new TimerManager();
        }
        return instance;
    }

    public void addTimer(AnimationTimer timer) {
        timers.add(timer);
    }

    public void addTimeline(Timeline timeline) {
        timelines.add(timeline);
    }

    public void stopAllTimers() {
        for (AnimationTimer timer : timers) {
            timer.stop();
        }
    }

    public void stopAllTimelines() {
        for (Timeline timeline : timelines) {
            timeline.stop();
        }
    }

    public void stopAll() {
        stopAllTimers();
        stopAllTimelines();
    }
}