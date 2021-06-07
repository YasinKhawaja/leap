
package edu.ap.group10.leapwebapp.capabilityresource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.resource.Resource;
import edu.ap.group10.leapwebapp.resource.ResourceRepository;

@Service
public class CapResourceService {

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Autowired
    private CapResourceRepository capResourceRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public List<CapResource> getAllCapResources() {
        return capResourceRepository.findAll();
    }

    public List<CapResource> getAllCapResourcesByCapability(Long id) {
        return capResourceRepository.findByCapabilityId(id);
    }

    public List<CapResource> getAllCapResourcesByResourceId(Long id) {
        return capResourceRepository.findByResourceId(id);
    }

    public CapResource createCapResource(Long capId, Long resId) {
        Capability cap = capabilityRepository.findById(capId).orElseThrow();
        Resource res = resourceRepository.findById(resId).orElseThrow();
        CapResource capresLink = new CapResource(cap, res);
        return capResourceRepository.save(capresLink);
    }

    public CapResource updateCapResource(Long id, Integer numberOfResources) {
        CapResource capresToUp = capResourceRepository.findById(id).orElseThrow();

        capresToUp.setNumberOfResources(numberOfResources);

        return capResourceRepository.save(capresToUp);
    }

    public void deleteCapResource(Long id) {
        capResourceRepository.deleteById(id);
    }

}
