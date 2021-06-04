
package edu.ap.group10.leapwebapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public Resource getResource(@PathVariable Long id) {
        try {
            return resourceService.getResource(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // To CREATE a resource
    @PostMapping("/resources")
    public Resource createResource(@RequestBody Resource resource) {
        try {
            return resourceService.createResource(resource);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    // To UPDATE a resource
    @PutMapping("/resources/{id}")
    public Resource updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        try {
            return resourceService.updateResource(id, resource);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    // To DELETE a resource
    @DeleteMapping("/resources/{id}")
    public void deleteResource(@PathVariable Long id) {
        try {
            resourceService.deleteResource(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
