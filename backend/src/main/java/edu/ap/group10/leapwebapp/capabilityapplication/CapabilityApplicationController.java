package edu.ap.group10.leapwebapp.capabilityapplication;

import java.util.Arrays;
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
import edu.ap.group10.leapwebapp.environment.Environment;
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
        List<CapabilityApplication> capabilityApplicationsList = capabilityApplicationService
                .getCapabilityApplications(capabilityId);
        Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
        capability.setInformationQuality(0.0);
        capability.setApplicationFit(0.0);
        Environment environment = capability.getEnvironment();
        Long environmentId = environment.getId();

        for (CapabilityApplication capabilityApplication : capabilityApplicationsList) {
            Integer[] importanceCalc = { capabilityApplication.getBusinessEfficiencySupport(),
                    capabilityApplication.getBusinessFunctionalCoverage(),
                    capabilityApplication.getBusinessCorrectness(), capabilityApplication.getBusinessFuturePotential(),
                    capabilityApplication.getInformationCompleteness(),
                    capabilityApplication.getInformationCorrectness(),
                    capabilityApplication.getInformationAvailability() };
            Double importance = (double) Arrays.stream(importanceCalc).mapToInt(Integer::intValue).sum()
                    / (importanceCalc.length * 5);
            capabilityApplication.setImportance(importance);
            System.out.println("Importance factor " + capabilityApplication.getImportance());
            capability.setCalculatedInformationQuality(capabilityApplication.getInformationCompleteness(),
                    capabilityApplication.getInformationCorrectness(),
                    capabilityApplication.getInformationAvailability(), capabilityApplication.getImportance());
            System.out.println("Information Quality" + capability.getInformationQuality());

            capability.setCalculatedApplicationFit(capabilityApplication.getBusinessEfficiencySupport(),
                    capabilityApplication.getBusinessFunctionalCoverage(),
                    capabilityApplication.getBusinessCorrectness(), capabilityApplication.getBusinessFuturePotential(),
                    capabilityApplication.getImportance());
            System.out.println("Application Fit" + capability.getApplicationFit());

            capabilityService.updateCapability(environmentId, capability.getId(), capability);

        }
        System.out.println("Information Quality at the end" + capability.getInformationQuality());
        System.out.println("Application Fit at the end" + capability.getApplicationFit());
        return capabilityApplicationService.getCapabilityApplications(capabilityId);
    }

    @PostMapping("/capitapp/{capabilityId}")
    public CapabilityApplication addCapabilityApplication(@RequestParam Integer businessEfficiencySupport,
            @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness,
            @RequestParam Integer businessFuturePotential, @RequestParam Integer informationCompleteness,
            @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
            @PathVariable String capabilityId, @RequestParam String application) {

        Integer[] importanceCalc = { businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness,
                businessFuturePotential, informationCompleteness, informationCorrectness, informationAvailability };
        Double importance = (double) Arrays.stream(importanceCalc).mapToInt(Integer::intValue).sum()
                / (importanceCalc.length * 5);

        Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
        // capability.setCalculatedInformationQuality(informationCompleteness,
        // informationCorrectness, informationAvailability, importance);
        ITApplication itApplication = itApplicationService.findITApplicationByName(application);

        CapabilityApplication capabilityApplication = new CapabilityApplication(importance, businessEfficiencySupport,
                businessFunctionalCoverage, businessCorrectness, businessFuturePotential, informationCompleteness,
                informationCorrectness, informationAvailability, capability, itApplication);
        return capabilityApplicationService.createCapabilityApplication(capabilityApplication);
    }

    @PutMapping("/capitapp/{capitappId}")
    public CapabilityApplication updateCapabilityApplication(@RequestParam Integer businessEfficiencySupport,
            @RequestParam Integer businessFunctionalCoverage, @RequestParam Integer businessCorrectness,
            @RequestParam Integer businessFuturePotential, @RequestParam Integer informationCompleteness,
            @RequestParam Integer informationCorrectness, @RequestParam Integer informationAvailability,
            @PathVariable String capitappId) {
        Integer[] importanceCalc = { businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness,
                businessFuturePotential, informationCompleteness, informationCorrectness, informationAvailability };
        Double importance = (double) Arrays.stream(importanceCalc).mapToInt(Integer::intValue).sum()
                / (importanceCalc.length * 5);

        CapabilityApplication capabilityApplication = capabilityApplicationService
                .findCapabilityApplication(Long.parseLong(capitappId));

        CapabilityApplication newCapabilityApplication = new CapabilityApplication(importance,
                businessEfficiencySupport, businessFunctionalCoverage, businessCorrectness, businessFuturePotential,
                informationCompleteness, informationCorrectness, informationAvailability,
                capabilityApplication.getCapability(), capabilityApplication.getItApplication());
        return capabilityApplicationService.updateCapabilityApplication(Long.parseLong(capitappId),
                newCapabilityApplication);
    }

    @DeleteMapping("/capitapp/{capitappId}")
    public void deleteCapabilityApplication(@PathVariable String capitappId) {
        Long capitappID = Long.parseLong(capitappId);
        capabilityApplicationService.deleteCapabilityApplication(capitappID);
    }
}
