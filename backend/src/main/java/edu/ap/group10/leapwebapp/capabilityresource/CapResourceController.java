
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CapResourceController {

    @Autowired
    private CapResourceService capResourceService;

    @GetMapping("/capresources")
    public List<CapResource> getAllCapResources() {
        return capResourceService.getAllCapResources();
    }

    @GetMapping("/capresources/capability/{id}")
    public List<CapResource> getCapResourceByCapability(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByCapability(id);
    }

    @GetMapping("/capresources/resource/{id}")
    public List<CapResource> getAllCapResourcesByResourceId(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByResourceId(id);
    }

    @PostMapping("/capresources")
    public CapResource createCapResource(@RequestParam Long capId, @RequestParam Long resId) {
        try {
            return capResourceService.createCapResource(capId, resId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    @PutMapping("/capresources/{id}")
    public CapResource updateCapResource(@PathVariable Long id, @RequestParam Integer numberOfResources) {
        try {
            return capResourceService.updateCapResource(id, numberOfResources);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/capresources/{id}")
    public void deleteCapResource(@PathVariable Long id) {
        try {
            capResourceService.deleteCapResource(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
