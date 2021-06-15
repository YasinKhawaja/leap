package edu.ap.group10.leapwebapp.itapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.capabilityapplication.CapabilityApplication;
import edu.ap.group10.leapwebapp.capabilityapplication.CapabilityApplicationRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class ITApplicationController {

    @Autowired
    private ITApplicationService itApplicationService;
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private CapabilityApplicationRepository capabilityApplicationRepository;

    @GetMapping("/itapplications/{environmentId}")
    public List<ITApplication> getAllITApplications(@PathVariable String environmentId) {
        return itApplicationService.getITApplications(environmentId);
    }

    @PostMapping("/itapplications/{environmentId}")
    public void addITApplication(@PathVariable String environmentId, @RequestBody ITApplication itapplication) {

        Environment environment = environmentService.getEnvironment(Long.parseLong(environmentId));
        itapplication.setEnvironment(environment);

        itApplicationService.createITApplication(itapplication);
    }

    @PutMapping("/itapplications/{applicationId}")
    public void updateITApplication(@PathVariable String applicationId, @RequestBody ITApplication itapplication) {
        ITApplication oldITApplication = itApplicationService.findITApplication(Long.parseLong(applicationId));

        itapplication.setEnvironment(oldITApplication.getEnvironment());

        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getItApplication().getId().equals(oldITApplication.getId())){
                capabilityApplication.setImportance(itapplication.getImportanceFactor()/100);
            }
        }
        
        itApplicationService.updateITApplication(oldITApplication.getId(), itapplication);
    }

    @DeleteMapping("/itapplications/{applicationId}")
    public void deleteITApplication(@PathVariable String applicationId) {
        Long applicationID = Long.parseLong(applicationId);
        itApplicationService.deleteITApplication(applicationID);
    }

    @GetMapping("itapplication/{applicationId}")
    public ITApplication getITApplication(@PathVariable String applicationId) {
        return itApplicationService.getITApplication(Long.parseLong(applicationId));
    }
}
