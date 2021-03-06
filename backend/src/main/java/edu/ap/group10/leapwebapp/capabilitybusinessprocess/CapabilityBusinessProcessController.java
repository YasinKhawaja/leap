package edu.ap.group10.leapwebapp.capabilitybusinessprocess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityBusinessProcessController {

    @Autowired
    private CapabilityBusinessProcessService capabilityBusinessProcessService;

    @GetMapping("/capabilitybusinessprocess/{capabilityid}")
    public List<CapabilityBusinessProcess> getCabilityBusinessProcesses(@PathVariable String capabilityid) {
        return capabilityBusinessProcessService.getCapabilityBusinessProcesses(capabilityid);
    }

    @PostMapping("/capabilitybusinessprocess/{capabilityid}")
    public void addCapabilityBusinessProcess(@PathVariable String capabilityid, @RequestParam String businessprocess) {
        capabilityBusinessProcessService.addCapabilityBusinessProcess(capabilityid, businessprocess);
    }

    @DeleteMapping("/capabilitybusinessprocess/{businessprocessid}")
    public void deleteCapabilityBusinessProcess(@PathVariable String businessprocessid) {
        capabilityBusinessProcessService.deleteCapabilityBusinessProcess(businessprocessid);
    }
}
