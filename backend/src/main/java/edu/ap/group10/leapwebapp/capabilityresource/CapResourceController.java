
package edu.ap.group10.leapwebapp.capabilityresource;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<CapResource> getCapResourceByCapability(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByCapability(id);
    }

    // To GET all cap res links by res id
    @GetMapping("/capresources/resource/{id}")
    public List<CapResource> getCapResourceByResource(@PathVariable Long id) {
        return capResourceService.getAllCapResourcesByResource(id);
    }

    // To CREATE a cap res link
    @PostMapping("/capresources")
    public CapResource createCapResource(@RequestParam Long capId, @RequestParam Long resId)
            throws NoSuchElementException {
        return capResourceService.createCapResource(capId, resId);
    }

    // To UPDATE a cap res link props
    @PutMapping("/capresources/{id}")
    public CapResource updateCapResource(@PathVariable Long id, @RequestParam Integer numberOfResources)
            throws NoSuchElementException {
        return capResourceService.updateCapResource(id, numberOfResources);
    }

    // To DELETE a cap res link
    @DeleteMapping("/capresources/{id}")
    public void deleteCapResource(@PathVariable Long id) throws IllegalArgumentException {
        capResourceService.deleteCapResource(id);
    }

}
