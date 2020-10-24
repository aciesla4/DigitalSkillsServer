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
        System.out.println(logs.get(level));
        ArrayList<String> logList = new ArrayList<String>();
        for (Logging log: logs.get(level)) {
            logList.add(log.getId());
        }
        return logList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logging")
    // "https://jolly-fermat-db4c86.netlify.app/"
    @CrossOrigin()
    public void logClick(@RequestBody Logging newLogging) {
        System.out.print("Log Click: " + newLogging.toString() + "\n");
        if (logs.containsKey(newLogging.getLevel())) {
            logs.get(newLogging.getLevel()).add(newLogging);
        }
        else {
            ArrayList<Logging> newList = new ArrayList<Logging>();
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