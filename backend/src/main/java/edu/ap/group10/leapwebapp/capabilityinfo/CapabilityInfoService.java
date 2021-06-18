package edu.ap.group10.leapwebapp.capabilityinfo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.information.Information;
import edu.ap.group10.leapwebapp.information.InformationRepository;

@Service
public class CapabilityInfoService {

    @Autowired
    private CapabilityInfoRepository capabilityInfoRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Autowired
    private InformationRepository informationRepository;

    public List<CapabilityInfo> getAllCapabilityInfo(Long capabilityid) {
        List<CapabilityInfo> capabilityInfoList = new ArrayList<>();
        for (CapabilityInfo capinfo : capabilityInfoRepository.findAll()) {
            if (capinfo.getCapability().getId().equals(capabilityid)) {
                capabilityInfoList.add(capinfo);
            }
        }
        return capabilityInfoList;
    }

    public void addCapabilityInfo(Long capabilityid, Long informationid, Criticality criticality) {
        Capability capability = capabilityRepository.findById(capabilityid).orElseThrow(ResourceNotFoundException::new);
        Information information = informationRepository.findById(informationid)
                .orElseThrow(ResourceNotFoundException::new);

        CapabilityInfo capinfo = new CapabilityInfo(capability, information, criticality);
        if (capabilityInfoRepository.findById(capinfo.getId()).isPresent()) {
            throw new EntityExistsException("Link already exists");
        } else {
            capabilityInfoRepository.save(capinfo);
        }
    }

    public void deleteCapabilityInfo(Long capinfoid) {
        CapabilityInfo oldCapInfo = capabilityInfoRepository.findById(capinfoid)
                .orElseThrow(ResourceNotFoundException::new);
        capabilityInfoRepository.delete(oldCapInfo);
    }

    public List<CapabilityInfo> getAllCapabilityInfoInformation(Long informationid) {
        List<CapabilityInfo> capabilityInfoList = new ArrayList<>();
        for (CapabilityInfo capinfo : capabilityInfoRepository.findAll()) {
            if (capinfo.getInformation().getId().equals(informationid)) {
                capabilityInfoList.add(capinfo);
            }
        }
        return capabilityInfoList;
    }

    public void updateCapabilityInfo(Long capinfoid, Criticality criticality) {
        CapabilityInfo capinfo = capabilityInfoRepository.findById(capinfoid)
                .orElseThrow(ResourceNotFoundException::new);

        capinfo.setCriticality(criticality);
        capabilityInfoRepository.save(capinfo);
    }

}
