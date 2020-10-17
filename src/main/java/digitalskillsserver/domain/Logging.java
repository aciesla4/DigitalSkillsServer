package digitalskillsserver.domain;

import java.time.LocalTime;

public class Logging {

    private int level;
    private String id;
    private int clickCount;
    private Double time;

    public Logging () {
        this.clickCount = 1;
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public String getId() {
        return id;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void increaseClickCount() {
        this.clickCount++;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getTime() {
        return time;
    }

    @Override
    public String toString(){
        return "Level" + level + ", ID: " + id + ", Clicks: " + clickCount + ", Time: " + time;
    }

}