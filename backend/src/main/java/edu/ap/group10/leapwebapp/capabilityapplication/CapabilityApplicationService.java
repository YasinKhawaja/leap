package edu.ap.group10.leapwebapp.capabilityapplication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CapabilityApplicationService {
    
    @Autowired
    private CapabilityApplicationRepository capabilityApplicationRepository;

    public List<CapabilityApplication> getCapabilityApplications(String capabilityId){
        List<CapabilityApplication> capabilityApplications = new ArrayList<CapabilityApplication>();
        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getCapability().getId().equals(Long.parseLong(capabilityId))){
                capabilityApplications.add(capabilityApplication);
            }
        }
        return capabilityApplications;
    }

    public CapabilityApplication findCapabilityApplication(Long id){
        return capabilityApplicationRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    }

    public CapabilityApplication createCapabilityApplication(CapabilityApplication capabilityApplication){
        if(capabilityApplicationRepository.findById(capabilityApplication.getId()).isPresent()){
            return null;
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
