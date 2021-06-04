package edu.ap.group10.leapwebapp.capabilityapplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;
import edu.ap.group10.leapwebapp.itapplication.ITApplicationRepository;

@Service
public class CapabilityApplicationService {
    
    @Autowired
    private CapabilityApplicationRepository capabilityApplicationRepository;

    @Autowired
    private ITApplicationRepository itApplicationRepository;

    public List<CapabilityApplication> getCapabilityApplications(String capabilityId){
        List<CapabilityApplication> capabilityApplications = new ArrayList<CapabilityApplication>();
        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getCapability().getId().equals(Long.parseLong(capabilityId))){
                capabilityApplications.add(capabilityApplication);
            }
        }
        return capabilityApplications;
    }

    public List<Capability> getAllCapabilitiesLinkedToITApplication(String itApplicationName) {
        ITApplication itApplication = itApplicationRepository.findByName(itApplicationName);
        List<Capability> capabilities = new ArrayList<Capability>();
        for (CapabilityApplication capabilityApplication : capabilityApplicationRepository.findAll()) {
            if(capabilityApplication.getItApplication().getId().equals(itApplication.getId())){
                capabilities.add(capabilityApplication.getCapability());
            }
        }
        return capabilities;
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
