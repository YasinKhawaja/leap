package edu.ap.group10.leapwebapp.businessprocesses;

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
public class BusinessProcessController {

    @Autowired
    private BusinessProcessService businessProcessService;

    @Autowired
    private EnvironmentService environmentService;

    @GetMapping("/businessprocesses/{environmentid}")
    public List<BusinessProcess> getAllBussinessProcesses(@PathVariable String environmentid) {
        return businessProcessService.getAllBusinessProcessesEnvironment(environmentid);
    }

    @GetMapping("/businessprocess/{businessprocessid}")
    public BusinessProcess getBusinessProcess(@PathVariable String businessprocessid) {
        return businessProcessService.getBusinessProcess(Long.parseLong(businessprocessid));
    }

    @PostMapping("/businessprocess/{environmentid}")
    public void addBusinessProcess(@PathVariable String environmentid, @RequestBody BusinessProcess businessprocess) {
        Environment environment = environmentService.getEnvironment(Long.parseLong(environmentid));
        businessprocess.setEnvironment(environment);
        businessProcessService.addBusinessProcess(businessprocess);
    }

    @PutMapping("/businessprocess/{businessprocessid}")
    public void updateBusinessProcess(@PathVariable String businessprocessid,
            @RequestBody BusinessProcess businessprocess) {
        businessProcessService.updateBusinessProcess(Long.parseLong(businessprocessid), businessprocess);
    }

    @DeleteMapping("/businessprocess/{businessprocessid}")
    public void deleteBusinessProcess(@PathVariable String businessprocessid) {
        businessProcessService.deleteBusinessProcess(Long.parseLong(businessprocessid));
    }

}
