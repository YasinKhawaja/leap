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

    public List<ITApplication> getITApplications(String environmentId){
        List<ITApplication> itApplications = new ArrayList<>();
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

    public ITApplication createITApplication(ITApplication itApplication){
        return itApplicationRepository.save(itApplication);
    }

    public ITApplication updateITApplication(Long itApplicationId, ITApplication itApplication){
        
        ITApplication oITApp = itApplicationRepository.findById(itApplicationId)
        .orElseThrow(ResourceNotFoundException::new);
        oITApp.setName(itApplication.getName());
        oITApp.setTechnology(itApplication.getTechnology());
        oITApp.setVersion(itApplication.getVersion());
        oITApp.setAcquisitionDate(itApplication.getAcquisitionDate());
        oITApp.setEndOfLife(itApplication.getEndOfLife());
        oITApp.setCurrentScalability(itApplication.getCurrentScalability());
        oITApp.setExpectedScalability(itApplication.getExpectedScalability());
        oITApp.setCurrentPerformance(itApplication.getCurrentPerformance());
        oITApp.setExpectedPerformance(itApplication.getExpectedPerformance());
        oITApp.setCurrentSecurityLevel(itApplication.getCurrentSecurityLevel());
        oITApp.setExpectedSecurityLevel(itApplication.getExpectedSecurityLevel());
        oITApp.setCurrentStability(itApplication.getCurrentStability());
        oITApp.setExpectedStability(itApplication.getExpectedStability());
        oITApp.setCostCurrency(itApplication.getCostCurrency());
        oITApp.setCurrentValueForMoney(itApplication.getCurrentValueForMoney());
        oITApp.setCurrentTotalCostPerYear(itApplication.getCurrentTotalCostPerYear());
        oITApp.setToleratedTotalCostPerYear(itApplication.getToleratedTotalCostPerYear());
        oITApp.setTimeValue(itApplication.getTimeValue());

        return itApplicationRepository.save(oITApp);
    }

    public boolean deleteITApplication(Long itApplicationId){
        Boolean validator = false;
        ITApplication oldITApplication = itApplicationRepository.findById(itApplicationId)
        .orElseThrow(ResourceNotFoundException::new);

        itApplicationRepository.delete(oldITApplication);
        if(itApplicationRepository.existsById(itApplicationId)){
            validator = true;
        }
        return validator;
    }
    
    public ITApplication getITApplication(Long applicationId){
        return itApplicationRepository.findById(applicationId)
        .orElseThrow(ResourceNotFoundException::new);
    }
}
