
package edu.ap.group10.leapwebapp.capabilityresource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.resource.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CapResourceController {

    @Autowired
    private CapResourceService capResourceService;

    // To GET all cap res links
    @GetMapping("/capresources")
    public List<CapResource> getAllCapResources() {
        return capResourceService.getAllCapResources();
    }

    // To GET all cap res links by cap id
    @GetMapping("/capresources/capability/{id}")
    public List<CapResource> getAllCapResourcesByCapabilityId(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByCapabilityId(id);
    }

    // To GET all cap res links by res id
    @GetMapping("/capresources/resource/{id}")
    public List<CapResource> getAllCapResourcesByResourceId(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByResourceId(id);
    }

    // To CREATE a cap res link
    @PostMapping("/capresources")
    public CapResource createCapResource(@RequestParam Long capId, @RequestParam Long resId,
            @RequestParam(required = false) Integer numberOfResources) {
        try {
            return capResourceService.createCapResource(capId, resId, numberOfResources);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    // To UPDATE a cap res link props
    @PutMapping("/capresources/{id}")
    public CapResource updateCapResource(@PathVariable Long id, @RequestParam Integer numberOfResources) {
        try {
            return capResourceService.updateCapResource(id, numberOfResources);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    // To DELETE a cap res link
    @DeleteMapping("/capresources/{id}")
    public void deleteCapResource(@PathVariable Long id) {
        try {
            capResourceService.deleteCapResource(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
