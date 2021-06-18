package edu.ap.group10.leapwebapp.information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private EnvironmentService environmentService;

    @GetMapping("/informations/{environmentid}")
    public List<Information> getInformationList(@PathVariable Long environmentid) {
        return informationService.getAllInformation(environmentid);
    }

    @GetMapping("/information/{informationid}")
    public Information getInformation(@PathVariable Long informationid) {
        return informationService.getInformation(informationid);
    }

    @PostMapping("/information/{environmentid}")
    public void addInformation(@PathVariable Long environmentid, @RequestBody Information information) {
        Environment environment = environmentService.getEnvironment(environmentid);
        information.setEnvironment(environment);
        informationService.addInformation(information);
    }

    @PutMapping("/information/{informationid}")
    public void updateInformation(@PathVariable Long informationid, @RequestBody Information information) {
        informationService.updateInformation(informationid, information);
    }

    @DeleteMapping("/information/{informationid}")
    public void deleteInformation(@PathVariable Long informationid) {
        informationService.deleteInformation(informationid);
    }
}
