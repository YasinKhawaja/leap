
package edu.ap.group10.leapwebapp.capability;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

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

    public List<Capability> getAllCapabilities() {
        return capabilityRepository.findAll();
    }

    public List<Capability> getAllCapabilitiesInEnvironment(Long envId) {
        Environment environmentToFindBy = environmentRepository.findById(envId).orElseThrow();

        return capabilityRepository.findByEnvironment(environmentToFindBy);
    }

    public List<String> getParentLink(Long envid) {
        Environment environmentToFindBy = environmentRepository.findById(envid).orElseThrow();

        List<String> parents = new ArrayList<>();
        for (Capability capability : capabilityRepository.findByEnvironment(environmentToFindBy)) {
            if (capability.getParent() != null) {
                parents.add(capability.getName() + ";" + capability.getParent().getName());
            }
        }
        return parents;
    }

    public Capability getCapability(Long envId, Long capId) {
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(cap -> cap.getId().equals(capId)).collect(Collectors.toList());
        return capsFound.get(0);
    }

    public Capability getCapabilityByNameInEnvironment(String name, Long envid) {
        Environment envToLinkWith = environmentRepository.findById(envid).orElseThrow();
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envToLinkWith.getId()).stream()
                .filter(c -> c.getName().equals(name)).collect(Collectors.toList());
        return capsFound.get(capsFound.size() - 1);
    }

    public Capability getCapabilityById(Long capId) {
        return capabilityRepository.findById(capId).orElseThrow(ResourceNotFoundException::new);
    }

    public Capability createCapability(Long envId, Long parentCapId, Capability cap) {
        Environment envToLinkWith = environmentRepository.findById(envId).orElseThrow();
            List<Capability> capsNameCheck = this.getAllCapabilitiesInEnvironment(envToLinkWith.getId()).stream()
                    .filter(c -> c.getName().equals(cap.getName())).collect(Collectors.toList());
        if (!capsNameCheck.isEmpty()) {
            throw new EntityExistsException("Capability already exists within this environment");
        } else {
            List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envToLinkWith.getId()).stream()
                    .filter(c -> c.getId().equals(parentCapId)).collect(Collectors.toList());
            Capability parentCapToLinkWith;
            if (!capsFound.isEmpty()) {
                parentCapToLinkWith = capsFound.get(0);
            } else {
                parentCapToLinkWith = null;
            }
            if (parentCapToLinkWith == null) {
                cap.setLevel(1);
            } else if (parentCapToLinkWith.getLevel() == 1) {
                cap.setLevel(2);
            } else if (parentCapToLinkWith.getLevel() == 2) {
                cap.setLevel(3);
            } else {
                throw new ArithmeticException("Cannot make sub capability, max niveau 3.");
            }
            cap.setEnvironment(envToLinkWith);
            cap.setParent(parentCapToLinkWith);
            return capabilityRepository.save(cap);
        }
    }

    public Capability updateCapability(Long envId, Long capId, Capability cap) {
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(capability -> capability.getId().equals(capId)).collect(Collectors.toList());
        Capability capabilityToUpdate = capsFound.get(0);
        capabilityToUpdate.setName(cap.getName());
        capabilityToUpdate.setPaceOfChange(cap.getPaceOfChange());
        capabilityToUpdate.setTargetOperationModel(cap.getTargetOperationModel());
        capabilityToUpdate.setResourcesQuality(cap.getResourcesQuality());
        return capabilityRepository.save(capabilityToUpdate);
    }

    public void deleteCapability(Long envId, Long capId) {
        List<Capability> capsFound = this.getAllCapabilitiesInEnvironment(envId).stream()
                .filter(cap -> cap.getId().equals(capId)).collect(Collectors.toList());
        capabilityRepository.delete(capsFound.get(0));
    }

}
