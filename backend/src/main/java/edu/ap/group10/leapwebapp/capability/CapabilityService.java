
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
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
    public List<Capability> getCapabilitiesInEnvironment(Long environmentId) {
        Environment environmentToFindBy = environmentRepository.findById(environmentId)
                .orElseThrow(ResourceNotFoundException::new);

        return capabilityRepository.findByEnvironment(environmentToFindBy);
    }

    // To CREATE a capability in an environment
    public Capability createCapabilityInEnvironment(Long environmentId, Capability capabilityReq) {
        Environment environmentToLinkWith = environmentRepository.findById(environmentId)
                .orElseThrow(ResourceNotFoundException::new);

        capabilityReq.setEnvironment(environmentToLinkWith);

        return capabilityRepository.save(capabilityReq);
    }

    // To UPDATE a capability in an environment
    public Capability updateCapabilityInEnvironment(Long environmentId, Long capabilityId, Capability capabilityReq) {
        if (!environmentRepository.existsById(environmentId)) {
            throw new ResourceNotFoundException();
        }

        Capability capabilityToUpdate = capabilityRepository.findById(capabilityId)
                .orElseThrow(ResourceNotFoundException::new);

        capabilityToUpdate.setName(capabilityReq.getName());

        return capabilityRepository.save(capabilityToUpdate);
    }

    // To DELETE a capability in an environment
    public ResponseEntity<?> deleteCapabilityFromEnvironment(Long environmentId, Long capabilityId) {
        if (!environmentRepository.existsById(environmentId)) {
            throw new ResourceNotFoundException();
        }

        Capability capabilityToDelete = capabilityRepository.findById(capabilityId)
                .orElseThrow(ResourceNotFoundException::new);

        capabilityRepository.delete(capabilityToDelete);

        return ResponseEntity.ok().build();
    }

}
