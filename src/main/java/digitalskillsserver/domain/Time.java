package digitalskillsserver.domain;

import java.util.Date;

public class Time {
    private Date startTime;

    private Date endTime;

    public void setStartTime(long startTime) {
        this.startTime = new Date(startTime);
    }

    public Date getStartTime() { return startTime; }

    public void setEndTime(long endTime) {
        this.endTime = new Date(endTime);
    }

    public Date getEndTime() { return endTime; }

    public long getTotalTime() {
        return endTime.getTime() - startTime.getTime();
    }
}
