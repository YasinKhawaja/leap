
package edu.ap.group10.leapwebapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResource(Long id) {
        return resourceRepository.findById(id).orElseThrow();
    }

    public Resource createResource(Resource resource) {
        if (resourceRepository.existsByName(resource.getName())) {
            throw new ResourceException(
                    String.format("Resource <strong>%s</strong> already exists!", resource.getName()));
        }

        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource resource) {
        Resource resToUpdate = resourceRepository.findById(id).orElseThrow();
        if (resToUpdate.getName().equals(resource.getName())) {
            resToUpdate.setName(resource.getName());
        } else if (resourceRepository.existsByName(resource.getName())) {
            throw new ResourceException(
                    String.format("Resource <strong>%s</strong> already exists!", resource.getName()));
        }
        resToUpdate.setDescription(resource.getDescription());
        resToUpdate.setFullTimeEquivalentYearlyValue(resource.getFullTimeEquivalentYearlyValue());
        return resourceRepository.save(resToUpdate);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

}
