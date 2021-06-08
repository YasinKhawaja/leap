package edu.ap.group10.leapwebapp.businessprocesses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BusinessProcessService {

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    public List<BusinessProcess> getAllBusinessProcessesEnvironment(String environmentId) {
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        for (BusinessProcess businessProcess : businessProcessRepository.findAll()) {
            if(businessProcess.getEnvironment().getId().equals(Long.parseLong(environmentId))){
                businessProcesses.add(businessProcess);
            }
        }
        return businessProcesses;
    }

    public BusinessProcess addBusinessProcess(BusinessProcess businessProcess) {
        return businessProcessRepository.save(businessProcess);
    }

    public Object updateBusinessProcess(Long businessprocessid, String name, String description) {
        
        BusinessProcess oBusPro = businessProcessRepository.findById(businessprocessid)
        .orElseThrow(ResourceNotFoundException::new);
        oBusPro.setDescription(description);
        oBusPro.setName(name);

        return businessProcessRepository.save(oBusPro);
    }

    public void deleteBusinessProcess(Long businessprocessid) {
        BusinessProcess businessProcess = businessProcessRepository.findById(businessprocessid)
        .orElseThrow(ResourceNotFoundException::new);

        businessProcessRepository.delete(businessProcess);
    }

    public BusinessProcess getBusinessProcess(Long businessprocessid) {
        return businessProcessRepository.findById(businessprocessid)
        .orElseThrow(ResourceNotFoundException::new);
    }
    
}
