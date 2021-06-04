
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

    // To GET all cap res links
    public List<CapResource> getAllCapResources() {
        return capResourceRepository.findAll();
    }

    // To GET all cap res links by cap id
    public List<CapResource> getAllCapResourcesByCapability(Long id) {
        return capResourceRepository.findByCapabilityId(id);
    }

    // To GET all cap res links by res id
    public List<CapResource> getAllCapResourcesByResourceId(Long id) {
        return capResourceRepository.findByResourceId(id);
    }

    // To CREATE a cap res link
    public CapResource createCapResource(Long capId, Long resId) {
        // Find the cap & res to link together
        Capability cap = capabilityRepository.findById(capId).orElseThrow();
        Resource res = resourceRepository.findById(resId).orElseThrow();
        // Instantiate the link
        CapResource capresLink = new CapResource(cap, res);
        // Save in DB
        return capResourceRepository.save(capresLink);
    }

    // To UPDATE a cap res link props
    public CapResource updateCapResource(Long id, Integer numberOfResources) {
        CapResource capresToUp = capResourceRepository.findById(id).orElseThrow();

        capresToUp.setNumberOfResources(numberOfResources);

        return capResourceRepository.save(capresToUp);
    }

    // To DELETE a cap res link
    public void deleteCapResource(Long id) {
        capResourceRepository.deleteById(id);
    }

}
