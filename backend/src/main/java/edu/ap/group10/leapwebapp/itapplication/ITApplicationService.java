package edu.ap.group10.leapwebapp.itapplication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ITApplicationService {

    @Autowired
    private ITApplicationRepository itApplicationRepository;

    //find all itapplications from the current environment
    public List<ITApplication> getITApplications(String environmentId){
        List<ITApplication> itApplications = new ArrayList<ITApplication>();
        for (ITApplication itApplication : itApplicationRepository.findAll()) {
            if(itApplication.getEnvironment().getId().equals(Long.parseLong(environmentId))){
                itApplications.add(itApplication);
            }
        }
        return itApplications;
    }

    public ITApplication findITApplicationByName(String application){
        return itApplicationRepository.findByName(application);
    }

    public ITApplication findITApplication(Long id){
        return itApplicationRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    }

    //create itapplication
    public ITApplication createITApplication(ITApplication itApplication){
        return itApplicationRepository.save(itApplication);
    }

    //update it application -> needs to be fully overwritten so send full object
    public ITApplication updateITApplication(Long itApplicationId, ITApplication itApplication){
        itApplication.setId(itApplicationId);
        
        ITApplication oldITApplication = itApplicationRepository.findById(itApplicationId)
        .orElseThrow(ResourceNotFoundException::new);

        itApplicationRepository.delete(oldITApplication);
        return itApplicationRepository.save(itApplication);
    }

    //delete it application
    public void deleteITApplication(Long itApplicationId){
        ITApplication oldITApplication = itApplicationRepository.findById(itApplicationId)
        .orElseThrow(ResourceNotFoundException::new);

        itApplicationRepository.delete(oldITApplication);
    }
    
}
