package edu.ap.group10.leapwebapp.capabilityapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityService;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;
import edu.ap.group10.leapwebapp.itapplication.ITApplicationService;

@RestController
public class CapabilityApplicationController {

    @Autowired
    private CapabilityApplicationService capabilityApplicationService;
    @Autowired
    private CapabilityService capabilityService;
    @Autowired
    private ITApplicationService itApplicationService;
    
    @GetMapping("/capitapp/{environmentId}")
    public List<CapabilityApplication> getAllCapabilityApplications(@PathVariable String environmentId){
        return capabilityApplicationService.getCapabilityApplications(environmentId);
    }

    @PostMapping("/capitapp/{environmentId}")
    public CapabilityApplication addCapabilityApplication(@PathVariable String environmentId, @RequestParam Double importance, @RequestParam Integer businessEfficiencySupport,
    @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness, @RequestParam Integer businessFuturePotential,
    @RequestParam Integer informationCompleteness, @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
    @RequestParam String capabilityId, @RequestParam String itApplicationId){

        Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
        ITApplication itApplication = itApplicationService.findITApplication(Long.parseLong(itApplicationId));

        return capabilityApplicationService.createCapabilityApplication(new CapabilityApplication(importance, businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness, businessFuturePotential, informationCompleteness, informationCorrectness, informationAvailability, capability, itApplication));
    }

    @PutMapping("/capitapp/{capitapp_id}")
    public CapabilityApplication updateCapabilityApplication(@PathVariable String capitappId, @RequestParam Double importance, @RequestParam Integer businessEfficiencySupport,
    @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness, @RequestParam Integer businessFuturePotential,
    @RequestParam Integer informationCompleteness, @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
    @RequestParam String capabilityId, @RequestParam String itApplicationId){

        Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
        ITApplication itApplication = itApplicationService.findITApplication(Long.parseLong(itApplicationId));

        return capabilityApplicationService.updateCapabilityApplication(Long.parseLong(capitappId), new CapabilityApplication(importance, businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness, businessFuturePotential, informationCompleteness, informationCorrectness, informationAvailability, capability, itApplication));
    }

    @DeleteMapping("/capitapp/{capitapp_id}")
    public void deleteCapabilityApplication(@PathVariable String capitappId){
        Long capitappID = Long.parseLong(capitappId);
        capabilityApplicationService.deleteCapabilityApplication(capitappID);
    }
}
