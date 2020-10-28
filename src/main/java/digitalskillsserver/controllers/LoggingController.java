package digitalskillsserver.controllers;

import digitalskillsserver.domain.Logging;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class LoggingController {

    HashMap<Integer, List<Logging>> logs = new HashMap<Integer, List<Logging>>();

    @RequestMapping(method = RequestMethod.GET, value = "/logging")
    @CrossOrigin()
    public List<String> getLogs(@RequestParam Integer level) {
        ArrayList<String> logList = new ArrayList<String>();
        for (Logging log: logs.get(level)) {
            logList.add(log.getId());
        }
        return logList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logging")
    // "https://digital-skills-assessment.netlify.app/"
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
    }
}