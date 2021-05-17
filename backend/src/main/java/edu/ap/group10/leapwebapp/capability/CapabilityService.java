
package edu.ap.group10.leapwebapp.capability;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@Service
public class CapabilityService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    /////// CAPABILITIES LEVEL 1 ///////
    // To GET all capabilities
    public List<Capability> getAllCapabilities() {
        return capabilityRepository.findAll();
    }

    // To GET all capabilities in an environment
    public List<Capability> getAllCapabilitiesInEnvironment(Long envId) {
        Environment environmentToFindBy = environmentRepository.findById(envId)
                .orElseThrow(ResourceNotFoundException::new);

        return capabilityRepository.findByEnvironment(environmentToFindBy);
    }

    // To GET a capability in its environment
    public Capability getCapability(Long envId, Long capId) {
        // Find the cap in its env to return
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(cap -> cap.getId().equals(capId)).collect(Collectors.toList());
        // Return the found cap
        return capsFound.get(0);
    }

    // To CREATE a capability in an environment
    public Capability createCapability(Long envId, String name, PaceOfChange paceOfChange, Tom tom,
            Integer resourcesQuality) {

        Environment envToLinkWith = environmentRepository.findById(envId).orElseThrow(ResourceNotFoundException::new);

        // Create the new cap and set its foreign key
        Capability capability = new Capability(name, paceOfChange, tom, resourcesQuality);
        capability.setEnvironment(envToLinkWith);

        return capabilityRepository.save(capability);
    }

    // To UPDATE a capability in its environment
    public Capability updateCapability(Long envId, Long capId, String name, PaceOfChange paceOfChange, Tom tom,
            Integer resourcesQuality) {
        // Find the cap in its env to update
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(capability -> capability.getId().equals(capId)).collect(Collectors.toList());
        // Get the found cap to update
        Capability capabilityToUpdate = capsFound.get(0);
        // Update the cap
        capabilityToUpdate.setName(name);
        capabilityToUpdate.setPaceOfChange(paceOfChange);
        capabilityToUpdate.setTom(tom);
        capabilityToUpdate.setResourcesQuality(resourcesQuality);
        // Resave the updated cap
        return capabilityRepository.save(capabilityToUpdate);
    }

    // To DELETE a capability in its environment
    public void deleteCapability(Long envId, Long capId) {
        // Find the cap in its env to delete
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(cap -> cap.getId().equals(capId)).collect(Collectors.toList());
        // Delete the found cap
        capabilityRepository.delete(capsFound.get(0));
    }

    /////// SUB CAPABILITIES LEVEL 2-3 ///////
    // To CREATE a subcapability in a capability
    public Capability createSubcapability(Long envId, Long capId, Capability capability) {

        List<Capability> capToLinkWith = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(cap -> cap.getId().equals(capId)).collect(Collectors.toList());

        capability.setEnvironment(capToLinkWith.get(0).getEnvironment());
        capability.setParent(capToLinkWith.get(0));

        return capabilityRepository.save(capability);
    }

    // To UPDATE a subcapability in a capability
    public Capability updateSubcapability(Long envId, Long capId, Long subcapId, Capability cap) {
        return null;
    }

    // To DELETE a subcapability in a capability
    public void deleteSubcapability(Long envId, Long capId, Long subcapId) {

    }
}
