package edu.ap.group10.leapwebapp.capabilityproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityProjectController {
    
    @Autowired
    private CapabilityProjectService capabilityProjectService;

    @GetMapping("/capabilityproject/{capabilityid}")
    public List<CapabilityProject> getCapabilityProject(@PathVariable String capabilityid){
        return capabilityProjectService.getCapabilityProject(capabilityid);
    }

    @PostMapping("/capabilityproject/{capabilityid}")
    public void addCapabilityProject(@PathVariable String capabilityid, @RequestParam String projectname){
        capabilityProjectService.addCapabilityProject(capabilityid, projectname);
    }

    @DeleteMapping("/capabilityproject/{capabilityprojectid}")
    public void deleteCapabilityProject(@PathVariable String capabilityprojectid){
        capabilityProjectService.deleteCapabilityProject(capabilityprojectid);
    }
}
