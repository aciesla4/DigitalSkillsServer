package digitalskillsserver.domain;

import java.util.Date;

public class Logging {

    private int level;
    private String id;
    private int clickCount;
    private Date timestamp;
    private double time;

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

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTime(double time) { this.time = time; }

    public double getTime() { return time; }

    @Override
    public String toString(){
        return "Level" + level + ", ID: " + id + ", Clicks: " + clickCount + ", Time: " + timestamp;
    }

}