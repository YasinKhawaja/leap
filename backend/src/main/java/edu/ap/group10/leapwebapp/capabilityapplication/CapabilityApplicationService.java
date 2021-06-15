package edu.ap.group10.leapwebapp.capabilityapplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityService;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;
import edu.ap.group10.leapwebapp.itapplication.ITApplicationRepository;

@Service
public class CapabilityApplicationService {
    
    @Autowired
    private CapabilityApplicationRepository capabilityApplicationRepository;

    @Autowired
    private ITApplicationRepository itApplicationRepository;

    @Autowired
    private CapabilityApplicationService capabilityApplicationService;

    @Autowired
    private CapabilityService capabilityService;

    public List<CapabilityApplication> getCapabilityApplications(String capabilityId){
        List<CapabilityApplication> capabilityApplications = new ArrayList<>();
        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getCapability().getId().equals(Long.parseLong(capabilityId))){
                capabilityApplications.add(capabilityApplication);
            }
        }
        return capabilityApplications;
    }

    public List<Capability> getAllCapabilitiesLinkedToITApplication(String itApplicationName) {
        ITApplication itApplication = itApplicationRepository.findByName(itApplicationName);
        List<Capability> capabilities = new ArrayList<>();
        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getItApplication().getId().equals(itApplication.getId())){
                capabilities.add(capabilityApplication.getCapability()); 
            }
        }
        return capabilities;
    }
    
    public void calculateCapabilityAppFitAndInfoQuality(String capabilityId) {
        List<CapabilityApplication> capabilityApplicationsList = capabilityApplicationService
                                .getCapabilityApplications(capabilityId);
                Capability capability = capabilityService.getCapabilityById(Long.parseLong(capabilityId));
                capability.setInformationQuality(0.0);
                capability.setApplicationFit(0.0);
                Environment environment = capability.getEnvironment();
                Long environmentId = environment.getId();

                for (CapabilityApplication capabilityApplication : capabilityApplicationsList) {

                        capability.setCalculatedInformationQuality(capabilityApplication.getInformationCompleteness(),
                                        capabilityApplication.getInformationCorrectness(),
                                        capabilityApplication.getInformationAvailability(),
                                        capabilityApplication.getImportance());

                        capability.setCalculatedApplicationFit(capabilityApplication.getBusinessEfficiencySupport(),
                                        capabilityApplication.getBusinessFunctionalCoverage(),
                                        capabilityApplication.getBusinessCorrectness(),
                                        capabilityApplication.getBusinessFuturePotential(),
                                        capabilityApplication.getImportance());

                        capabilityService.updateCapability(environmentId, capability.getId(), capability);

                }

    }

    public CapabilityApplication findCapabilityApplication(Long id){
        return capabilityApplicationRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    }

    public CapabilityApplication createCapabilityApplication(CapabilityApplication capabilityApplication){
        if(capabilityApplicationRepository.findById(capabilityApplication.getId()).isPresent()){
            throw new EntityExistsException("Link already exists");
        } else{
            return capabilityApplicationRepository.save(capabilityApplication);
        }
    }

    public CapabilityApplication updateCapabilityApplication(Long capabilityApplicationId, CapabilityApplication capabilityApplication){
        capabilityApplication.setId(capabilityApplicationId);

        CapabilityApplication oldCapabilityApplication = capabilityApplicationRepository.findById(capabilityApplicationId)
        .orElseThrow(ResourceNotFoundException::new);

        capabilityApplicationRepository.delete(oldCapabilityApplication);
        return capabilityApplicationRepository.save(capabilityApplication);
    }

    public void deleteCapabilityApplication(Long capabilityApplicationId){
        CapabilityApplication oldCapabilityApplication = capabilityApplicationRepository.findById(capabilityApplicationId)
        .orElseThrow(ResourceNotFoundException::new);

        capabilityApplicationRepository.delete(oldCapabilityApplication);
    }

    
}
