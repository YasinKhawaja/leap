
package edu.ap.group10.leapwebapp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resources")
    public List<Resource> getAllResourcesInEnvironment(@RequestParam Long environmentId) {
        return resourceService.getAllResourcesInEnvironment(environmentId);
    }

    @PostMapping("/resources")
    public void createResource(@RequestParam Long environmentId, @RequestBody Resource resource) {
        try {
            resourceService.createResource(environmentId, resource);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PutMapping("/resources/{resourceId}")
    public void updateResource(@PathVariable Long resourceId, @RequestBody Resource resource) {
        try {
            resourceService.updateResource(resourceId, resource);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/resources/{resourceId}")
    public void deleteResource(@PathVariable Long resourceId) {
        try {
            resourceService.deleteResource(resourceId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
