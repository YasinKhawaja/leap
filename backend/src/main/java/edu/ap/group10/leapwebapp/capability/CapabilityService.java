
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

    // To GET all capabilities
    public List<Capability> getAllCapabilities() {
        return capabilityRepository.findAll();
    }

    // To GET all capabilities in an environment
    public List<Capability> getAllCapabilitiesInEnvironment(Long envId) {
        Environment environmentToFindBy = environmentRepository.findById(envId).orElseThrow();

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

    //Get for capability by capabilityId for capability-application link
    public Capability getCapabilityById(Long capId) {
        return capabilityRepository.findById(capId)
        .orElseThrow(ResourceNotFoundException::new);
    }

    // To CREATE a capability in an environment
    public Capability createCapability(Long envId, Long parentCapId, Capability cap) {
        // Find the env to link the cap with
        Environment envToLinkWith = environmentRepository.findById(envId).orElseThrow();
        // Find the parent cap in the found env to link the cap with
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envToLinkWith.getId()).stream()
                .filter(c -> c.getId().equals(parentCapId)).collect(Collectors.toList());
        // Get the found parent cap to link the cap with
        Capability parentCapToLinkWith;
        if (!capsFound.isEmpty()) {
            parentCapToLinkWith = capsFound.get(0);
        } else {
            parentCapToLinkWith = null;
        }
        // if level < 3 then level + 1
        // Define the cap level (1, 2 or 3)
        if (parentCapToLinkWith == null) {
            cap.setLevel(1);
        } else if (parentCapToLinkWith.getLevel() == 1) {
            cap.setLevel(2);
        } else if (parentCapToLinkWith.getLevel() == 2) {
            cap.setLevel(3);
        } else {
            // error is niet juist omdat het niet echt een berekening is, maak een nieuwe eigen capabilityexception
            throw new ArithmeticException("Cannot make sub capability, max niveau 3.");
        }
        // Set the foreign keys
        cap.setEnvironment(envToLinkWith);
        cap.setParent(parentCapToLinkWith);
        // Save in DB
        return capabilityRepository.save(cap);
    }

    // To UPDATE a capability in its environment
    public Capability updateCapability(Long envId, Long capId, Capability cap) {
        // Find the cap in its env to update
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(capability -> capability.getId().equals(capId)).collect(Collectors.toList());
        // Get the found cap to update
        Capability capabilityToUpdate = capsFound.get(0);
        // Update the cap
        capabilityToUpdate.setName(cap.getName());
        capabilityToUpdate.setPaceOfChange(cap.getPaceOfChange());
        capabilityToUpdate.setTargetOperationModel(cap.getTargetOperationModel());
        capabilityToUpdate.setResourcesQuality(cap.getResourcesQuality());
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

}
