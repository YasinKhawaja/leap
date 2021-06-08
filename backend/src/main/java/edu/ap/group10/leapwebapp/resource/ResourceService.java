
package edu.ap.group10.leapwebapp.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@Service
public class ResourceService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAllResourcesInEnvironment(Long environmentId) {
        return resourceRepository.findAll().stream()
                .filter(resource -> resource.getEnvironment().getId().equals(environmentId))
                .collect(Collectors.toList());
    }

    public Resource getResourceInEnvironment(Long resourceId, Long environmentId) {
        List<Resource> resourcesFound = this.getAllResourcesInEnvironment(environmentId).stream()
                .filter(resource -> resource.getId().equals(resourceId)).collect(Collectors.toList());
        return resourcesFound.get(0);
    }

    public Resource createResource(Long environmentId, Resource resource) {
        Environment environmentToLinkWith = environmentRepository.findById(environmentId).orElseThrow();
        resource.setEnvironment(environmentToLinkWith);
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long resourceId, Resource resource) {
        Resource resourceToUpdate = resourceRepository.findById(resourceId).orElseThrow();
        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setDescription(resource.getDescription());
        resourceToUpdate.setFullTimeEquivalentYearlyValue(resource.getFullTimeEquivalentYearlyValue());
        return resourceRepository.save(resourceToUpdate);
    }

    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

}
