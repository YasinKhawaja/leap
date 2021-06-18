package edu.ap.group10.leapwebapp.informationfix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository;

    public List<Information> getAllInformation(Long environmentid) {
        List<Information> informationList = new ArrayList<>();
        for (Information information : informationRepository.findAll()) {
            if (information.getEnvironment().getId().equals(environmentid)) {
                informationList.add(information);
            }
        }
        return informationList;
    }

    public Information addInformation(Information information) {
        return informationRepository.save(information);
    }

    public void updateInformation(Long informationid, Information newObject) {
        Information oInfo = informationRepository.findById(informationid).orElseThrow(ResourceNotFoundException::new);
        oInfo.setDescription(newObject.getDescription());
        oInfo.setName(newObject.getName());
        informationRepository.save(oInfo);
    }

    public void deleteInformation(Long informationid) {
        Information information = informationRepository.findById(informationid)
                .orElseThrow(ResourceNotFoundException::new);
        informationRepository.delete(information);
    }

    public Information getInformation(Long informationid) {
        return informationRepository.findById(informationid).orElseThrow(ResourceNotFoundException::new);
    }
}
