package edu.ap.group10.leapwebapp.itapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class ITApplicationController {

    @Autowired
    private ITApplicationService itApplicationService;
    @Autowired
    private EnvironmentService environmentService;

    @GetMapping("/itapplications/{environmentId}")
    public List<ITApplication> getAllITApplications(@PathVariable String environmentId){
        return itApplicationService.getITApplications(environmentId);
    }

    @PostMapping("/itapplications/{environmentId}")
    public ITApplication addITApplication(@PathVariable String environmentId, @RequestParam("name") String applicationName, @RequestParam("technology") String technology,
    @RequestParam("version") String version, @RequestParam("acquisitionDate") String acquisitionDate, @RequestParam("endOfLife") String endOfLife, 
    @RequestParam("currentScalability") Integer currentScalability, @RequestParam("expectedScalability") Integer expectedScalability, @RequestParam("currentPerformance") Integer currentPerformance,
    @RequestParam("expectedPerformance") Integer expectedPerformance, @RequestParam("currentSecurityLevel") Integer currentSecurityLevel, @RequestParam("expectedSecurityLevel") Integer expectedSecurityLevel,
    @RequestParam("currentStability") Integer currentStability, @RequestParam("expectedStability") Integer expectedStability, @RequestParam("costCurrency") String costCurrency,
    @RequestParam("currentValueForMoney") Integer currentValueForMoney, @RequestParam("currentTotalCostPerYear") Double currentTotalCostPerYear, @RequestParam("toleratedTotalCostPerYear") Double toleratedTotalCostPerYear,
    @RequestParam("timeValue") TimeValue timeValue){

        Environment environment = environmentService.getEnvironment(Long.parseLong(environmentId));
        ITApplication itApplication = new ITApplication(applicationName, technology, version, acquisitionDate, endOfLife, currentScalability, expectedScalability, currentPerformance, expectedPerformance,
        currentSecurityLevel, expectedSecurityLevel, currentStability, expectedStability, costCurrency, currentValueForMoney, currentTotalCostPerYear, toleratedTotalCostPerYear, timeValue, environment);
        return itApplicationService.createITApplication(itApplication);
    }

    @PutMapping("/itapplications/{applicationId}")
    public ITApplication updateITApplication(@PathVariable String applicationId, @RequestParam("name") String applicationName, @RequestParam("technology") String technology,
    @RequestParam("version") String version, @RequestParam("acquisitionDate") String acquisitionDate, @RequestParam("endOfLife") String endOfLife, 
    @RequestParam("currentScalability") Integer currentScalability, @RequestParam("expectedScalability") Integer expectedScalability, @RequestParam("currentPerformance") Integer currentPerformance,
    @RequestParam("expectedPerformance") Integer expectedPerformance, @RequestParam("currentSecurityLevel") Integer currentSecurityLevel, @RequestParam("expectedSecurityLevel") Integer expectedSecurityLevel,
    @RequestParam("currentStability") Integer currentStability, @RequestParam("expectedStability") Integer expectedStability, @RequestParam("costCurrency") String costCurrency,
    @RequestParam("currentValueForMoney") Integer currentValueForMoney, @RequestParam("currentTotalCostPerYear") Double currentTotalCostPerYear, @RequestParam("toleratedTotalCostPerYear") Double toleratedTotalCostPerYear,
    @RequestParam("timeValue") TimeValue timeValue){
        ITApplication oldITApplication = itApplicationService.findITApplication(Long.parseLong(applicationId));
       
        ITApplication newITApplication = new ITApplication(applicationName, technology, version, acquisitionDate, endOfLife, currentScalability, expectedScalability, currentPerformance, expectedPerformance,
        currentSecurityLevel, expectedSecurityLevel, currentStability, expectedStability, costCurrency, currentValueForMoney, currentTotalCostPerYear, toleratedTotalCostPerYear, timeValue, oldITApplication.getEnvironment());

        return itApplicationService.updateITApplication(oldITApplication.getId(), newITApplication);
    }

    @DeleteMapping("/itapplications/{applicationId}")
    public void deleteITApplication(@PathVariable String applicationId){        
        Long applicationID = Long.parseLong(applicationId);
        itApplicationService.deleteITApplication(applicationID);
    }
}
