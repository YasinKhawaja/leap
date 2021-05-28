
package edu.ap.group10.leapwebapp.resource;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    // To GET all resources
    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // To GET a resource
    @GetMapping("/resources/{id}")
    public Resource getResource(@PathVariable Long id) throws NoSuchElementException {
        return resourceService.getResource(id);
    }

    // To CREATE a resource
    @PostMapping("/resources")
    public Resource createResource(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    // To UPDATE a resource
    @PutMapping("/resources/{id}")
    public Resource updateResource(@PathVariable Long id, @RequestBody Resource resource)
            throws NoSuchElementException {
        return resourceService.updateResource(id, resource);
    }

    // To DELETE a resource
    @DeleteMapping("/resources/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}
