package digitalskillsserver.domain;

import java.time.LocalTime;

public class Logging {

    private String id;
    private int clickCount;
    private Double time;

    public Logging () {
        this.clickCount = 1;
    }

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
        return id + ": " + clickCount + ", " + time;
    }

}