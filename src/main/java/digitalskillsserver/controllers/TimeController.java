package digitalskillsserver.controllers;

import digitalskillsserver.domain.Time;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class TimeController {

    Time totalTime = new Time();
    HashMap<Integer, Time> levelTimes = new HashMap<Integer, Time>();

    @RequestMapping(method = RequestMethod.POST, value="/time/gameStartTime")
    @CrossOrigin
    public void logGameStartTime(@RequestBody Time startTime) {
        totalTime.setStartTime(startTime.getStartTime().getTime());
        System.out.println("---START TIME: " + startTime.getStartTime() + "---");
    }

    @RequestMapping(method = RequestMethod.POST, value="/time/gameEndTime")
    @CrossOrigin
    public void logGameEndTime(@RequestBody Time endTime) {
        totalTime.setEndTime(endTime.getEndTime().getTime());
        System.out.println("---END TIME: " + endTime.getEndTime() + "---");
    }

    @RequestMapping(method = RequestMethod.GET, value="/time/gameTotalTime")
    @CrossOrigin()
    public long getTotalGameTime() {
        System.out.println("TOTAL TIME: " + totalTime.getTotalTime());
        return totalTime.getTotalTime();
    }

    @RequestMapping(method = RequestMethod.POST, value="/time/levelStartTime")
    @CrossOrigin()
    public void logLevelStartTime(@RequestParam int level, @RequestBody Time startTime) {
        levelTimes.put(level, startTime);
    }

    @RequestMapping(method = RequestMethod.POST, value="/time/levelEndTime")
    @CrossOrigin()
    public void logLevelEndTime(@RequestParam int level, @RequestBody Time endTime) {
        levelTimes.get(level).setEndTime(endTime.getEndTime().getTime());
    }

    @RequestMapping(method = RequestMethod.GET, value="/time/levelTotalTime")
    @CrossOrigin()
    public long getTotalLevelTime(@RequestParam int level) {
        try {
            System.out.println("TOTAL LEVEL TIME: " + levelTimes.get(level).getTotalTime());
            return levelTimes.get(level).getTotalTime();
        } catch(NullPointerException e) {
            return 0;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/time")
    @CrossOrigin()
    public void clearTime() {
        System.out.println("********** CLEARING TOTAL TIME **********");
        totalTime = new Time();
        levelTimes = new HashMap<Integer, Time>();
    }
}
