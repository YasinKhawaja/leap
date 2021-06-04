
package edu.ap.group10.leapwebapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    // To GET all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // To GET a resource
    public Resource getResource(Long id) {
        return resourceRepository.findById(id).orElseThrow();
    }

    // To CREATE a resource
    public Resource createResource(Resource resource) {
        if (resourceRepository.existsByName(resource.getName())) {
            throw new ResourceException(
                    String.format("Resource <strong>%s</strong> already exists!", resource.getName()));
        }

        return resourceRepository.save(resource);
    }

    // To UPDATE a resource
    public Resource updateResource(Long id, Resource resource) {
        // Find the res to update & resave
        Resource resToUpdate = resourceRepository.findById(id).orElseThrow();
        // Update the found res
        if (resToUpdate.getName().equals(resource.getName())) {
            resToUpdate.setName(resource.getName());
        } else if (resourceRepository.existsByName(resource.getName())) {
            throw new ResourceException(
                    String.format("Resource <strong>%s</strong> already exists!", resource.getName()));
        }
        resToUpdate.setDescription(resource.getDescription());
        resToUpdate.setFullTimeEquivalentYearlyValue(resource.getFullTimeEquivalentYearlyValue());
        // Save the updated res
        return resourceRepository.save(resToUpdate);
    }

    // To DELETE a resource
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

}
