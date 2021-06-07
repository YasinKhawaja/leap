package edu.ap.group10.leapwebapp.cabilitybusinessprocess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcessRepository;
import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;

@Service
public class CapabilityBusinessProcessService {

    @Autowired
    private CapabilityBusinessProcessRepository capabilityBusinessProcessRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;
    
    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    public List<CapabilityBusinessProcess> getCapabilityBusinessProcesses(String capabilityid) {
        List<CapabilityBusinessProcess> capabilityBusinessProcesses = new ArrayList<>();
        for (CapabilityBusinessProcess capabilityBusinessProcess : capabilityBusinessProcessRepository.findAll()){
            if(capabilityBusinessProcess.getCapability().getId().equals(Long.parseLong(capabilityid))){
                capabilityBusinessProcesses.add(capabilityBusinessProcess);
            }
        }
        return capabilityBusinessProcesses;
    }

    public CapabilityBusinessProcess addCapabilityBusinessProcess(String capabilityid, String businessprocess) {
        Capability capability = capabilityRepository.findById(Long.parseLong(capabilityid))
        .orElseThrow(ResourceNotFoundException::new);
        BusinessProcess businessProcess = businessProcessRepository.findByName(businessprocess);
        
        CapabilityBusinessProcess capabilityBusinessProcess = new CapabilityBusinessProcess(capability,businessProcess);
        if(capabilityBusinessProcessRepository.findById(capabilityBusinessProcess.getId()).isPresent()){
            throw new EntityExistsException("Link already exists");
        } else {
            return capabilityBusinessProcessRepository.save(capabilityBusinessProcess);
        }
    }

    public void deleteCapabilityBusinessProcess(String businessprocessid) {
        CapabilityBusinessProcess oldCapBusProcess = capabilityBusinessProcessRepository.findById(Long.parseLong(businessprocessid))
        .orElseThrow(ResourceNotFoundException::new);

        capabilityBusinessProcessRepository.delete(oldCapBusProcess);
    }
    
}
