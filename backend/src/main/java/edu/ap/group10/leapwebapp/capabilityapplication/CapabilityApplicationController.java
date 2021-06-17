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

        @GetMapping("/capitapp/{capabilityId}")
        public List<CapabilityApplication> getAllCapabilityApplications(@PathVariable String capabilityId) {
                capabilityApplicationService.calculateCapabilityAppFitAndInfoQuality(capabilityId);
                return capabilityApplicationService.getCapabilityApplications(capabilityId);
        }

        @GetMapping("/capitapp/linked/{itApplicationName}")
        public List<Capability> getAllCapabilitiesLinkedToITApplication(@PathVariable String itApplicationName) {
                return capabilityApplicationService.getAllCapabilitiesLinkedToITApplication(itApplicationName);
        }

        // change to requestbody
        @PostMapping("/capitapp/{capabilityId}")
        public void addCapabilityApplication(@RequestParam Integer businessEfficiencySupport,
                        @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness,
                        @RequestParam Integer businessFuturePotential, @RequestParam Integer informationCompleteness,
                        @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
                        @PathVariable String capabilityId, @RequestParam String application, @RequestParam String importanceFactor) {


                Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
                ITApplication itApplication = itApplicationService.findITApplicationByName(application);
                Double importance = Double.parseDouble(importanceFactor);


                CapabilityApplication capabilityApplication = new CapabilityApplication(importance,
                                businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness,
                                businessFuturePotential, informationCompleteness, informationCorrectness,
                                informationAvailability, capability, itApplication);
                capabilityApplicationService.createCapabilityApplication(capabilityApplication);
        }

        // change to requestbody
        @PutMapping("/capitapp/{capitappId}")
        public void updateCapabilityApplication(@RequestParam Integer businessEfficiencySupport,
                        @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness,
                        @RequestParam Integer businessFuturePotential, @RequestParam Integer informationCompleteness,
                        @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
                        @PathVariable String capitappId, @RequestParam String importanceFactor) {
                

                CapabilityApplication capabilityApplication = capabilityApplicationService
                                .findCapabilityApplication(Long.parseLong(capitappId));
                
                Double importance = Double.parseDouble(importanceFactor);

                CapabilityApplication newCapabilityApplication = new CapabilityApplication(importance,
                                businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness,
                                businessFuturePotential, informationCompleteness, informationCorrectness,
                                informationAvailability, capabilityApplication.getCapability(),
                                capabilityApplication.getItApplication());
                capabilityApplicationService.updateCapabilityApplication(Long.parseLong(capitappId),
                                newCapabilityApplication);
        }

        @DeleteMapping("/capitapp/{capitappId}")
        public void deleteCapabilityApplication(@PathVariable String capitappId) {
                Long capitappID = Long.parseLong(capitappId);
                capabilityApplicationService.deleteCapabilityApplication(capitappID);
        }

        @GetMapping("/capitapp/searchOne/{capitappId}")
        public CapabilityApplication getCapabilityApplication(@PathVariable String capitappId) {
                return capabilityApplicationService.findCapabilityApplication(Long.parseLong(capitappId));
        }
}
