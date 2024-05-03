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
        setTimers(new ArrayList<>());
        setTimelines(new ArrayList<>());
    }

    public static TimerManager getInstance() {
        if (instance == null) {
            setInstance(new TimerManager());
        }
        return instance;
    }

    public void addTimer(AnimationTimer timer) {
        getTimers().add(timer);
    }

    public void addTimeline(Timeline timeline) {
        getTimelines().add(timeline);
    }

    public void stopAllTimers() {
        for (AnimationTimer timer : getTimers()) {
            timer.stop();
        }
    }

    public void stopAllTimelines() {
        for (Timeline timeline : getTimelines()) {
            timeline.stop();
        }
    }

    public void stopAll() {
        stopAllTimers();
        stopAllTimelines();
    }

    public static void setInstance(TimerManager instance) {
        TimerManager.instance = instance;
    }

    public List<AnimationTimer> getTimers() {
        return timers;
    }

    public void setTimers(List<AnimationTimer> timers) {
        this.timers = timers;
    }

    public List<Timeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<Timeline> timelines) {
        this.timelines = timelines;
    }
}