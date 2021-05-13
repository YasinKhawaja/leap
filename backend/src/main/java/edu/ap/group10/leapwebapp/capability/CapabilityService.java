
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
    public List<Capability> getCapabilities() {
        return capabilityRepository.findAll();
    }

    // To GET all capabilities in an environment
    public List<Capability> getCapabilitiesInEnvironment(Long envId) {
        Environment environmentToFindBy = environmentRepository.findById(envId)
                .orElseThrow(ResourceNotFoundException::new);

        return capabilityRepository.findByEnvironment(environmentToFindBy);
    }

    // To GET a capability in its environment by name
    public Capability getCapabilityInEnvironmentByName(String envName, String capName) {
        Environment envToFindBy = environmentRepository.findByName(envName);

        List<Capability> capsFound = this.getCapabilitiesInEnvironment(envToFindBy.getId()).stream()
                .filter(capability -> capability.getName().equals(capName)).collect(Collectors.toList());

        return capsFound.get(0);
    }

    // To CREATE a capability in an environment
    public Capability createCapabilityInEnvironment(Long envId, String name, PaceOfChange paceOfChange, Tom tom,
            Integer resourcesQuality) {

        Environment envToLinkWith = environmentRepository.findById(envId).orElseThrow(ResourceNotFoundException::new);

        // Create the new cap and set its foreign key
        Capability capability = new Capability(name, paceOfChange, tom, resourcesQuality);
        capability.setEnvironment(envToLinkWith);

        return capabilityRepository.save(capability);
    }

    // To UPDATE a capability in an environment
    public Capability updateCapabilityInEnvironment(Long envId, Long capId, String name, PaceOfChange paceOfChange,
            Tom tom, Integer resourcesQuality) {
        // Find the cap in its env to update
        List<Capability> capsFound = this.getCapabilitiesInEnvironment(envId).stream()
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

    // To DELETE a capability in an environment
    public void deleteCapabilityFromEnvironment(Long envId, Long capId) {
        // Find the cap in its env to delete
        List<Capability> capsFound = this.getCapabilitiesInEnvironment(envId).stream()
                .filter(capability -> capability.getId().equals(capId)).collect(Collectors.toList());
        // Get the found cap to delete
        Capability capabilityToDelete = capsFound.get(0);
        // Delete the cap
        capabilityRepository.delete(capabilityToDelete);
    }

}
