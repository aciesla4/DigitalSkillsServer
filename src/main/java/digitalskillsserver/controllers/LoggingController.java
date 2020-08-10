package digitalskillsserver.controllers;

import digitalskillsserver.domain.Logging;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LoggingController {

    HashMap<String, Logging> logs = new HashMap<String, Logging>();

    @RequestMapping(method = RequestMethod.GET, value = "/logging")
    @CrossOrigin(origins = "https://jolly-fermat-db4c86.netlify.app/")
    public Logging clickCount(@RequestParam(value = "id") String id) {
        return new Logging();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logging")
    @CrossOrigin(origins = "https://jolly-fermat-db4c86.netlify.app/")
    public void logClick(@RequestBody Logging newLogging) {
        if (logs.containsKey(newLogging.getId())) {
            logs.get(newLogging.getId()).increaseClickCount();
            logs.get(newLogging.getId()).setTime(newLogging.getTime());
            System.out.println("Old: " + logs.get(newLogging.getId()));
        }
        else {
            logs.put(newLogging.getId(), newLogging);
            System.out.println("New: " + newLogging);
        }
    }
}