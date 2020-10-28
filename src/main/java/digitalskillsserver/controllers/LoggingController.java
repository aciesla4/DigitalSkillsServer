package digitalskillsserver.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import digitalskillsserver.domain.Logging;
import digitalskillsserver.domain.Time;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class LoggingController {

    HashMap<Integer, List<Logging>> logs = new HashMap<Integer, List<Logging>>();
    Time time = new Time();

    @RequestMapping(method = RequestMethod.GET, value = "/logging")
    @CrossOrigin()
    public List<String> getLogs(@RequestParam Integer level) {
        ArrayList<String> logList = new ArrayList<String>();
        for (Logging log: logs.get(level)) {
            logList.add(log.getId());
        }
        return logList;
    }

    @RequestMapping(method = RequestMethod.POST, value="/logging/startTime")
    @CrossOrigin
    public void logStartTime(@RequestBody Time startTime) {
        time.setStartTime(startTime.getStartTime().getTime());
        System.out.println("---START TIME: " + startTime.getStartTime() + "---");
    }

    @RequestMapping(method = RequestMethod.POST, value="/logging/endTime")
    @CrossOrigin
    public void logEndTime(@RequestBody Time endTime) {
        time.setEndTime(endTime.getEndTime().getTime());
        System.out.println("---END TIME: " + endTime.getEndTime() + "---");
    }

    @RequestMapping(method = RequestMethod.GET, value="/logging/time")
    @CrossOrigin()
    public long getLevelTime() {
        System.out.println("TOTAL TIME: " + time.getTotalTime());
        return time.getTotalTime();
    }

    @RequestMapping(method = RequestMethod.GET, value="/logging/score")
    @CrossOrigin()
    public double getScore() {
        double firstLogTime = logs.get(1).get(0).getTimestamp().getTime();
        double lastLogTime = logs.get(16).get(logs.get(16).size() - 1).getTimestamp().getTime();
        double totalTime = (firstLogTime - lastLogTime) / 1000;
        return totalTime;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logging")
    // "https://jolly-fermat-db4c86.netlify.app/"
    @CrossOrigin()
    public void logClick(@RequestBody Logging newLogging) {
        System.out.println(newLogging.toString());
        if (logs.containsKey(newLogging.getLevel())) {
            if (!logs.get(newLogging.getLevel()).isEmpty()) {
                long oldTime = logs.get(newLogging.getLevel()).get(logs.get(newLogging.getLevel()).size() - 1).getTimestamp().getTime();
                double time = (double) (newLogging.getTimestamp().getTime() - oldTime) / 1000.0;
                System.out.println("----- " + time);
                newLogging.setTime(time);
            }
            logs.get(newLogging.getLevel()).add(newLogging);
        }
        else {
            ArrayList<Logging> newList = new ArrayList<Logging>();
            newLogging.setTime(0);
            newList.add(newLogging);
            logs.put(newLogging.getLevel(), newList);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/logging")
    @CrossOrigin()
    public void clearLogs() {
        System.out.println("********** CLEARING LOGS **********");
        logs = new HashMap<Integer, List<Logging>>();
        time = new Time();
    }
}