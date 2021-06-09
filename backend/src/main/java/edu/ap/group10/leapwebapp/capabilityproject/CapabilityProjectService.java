package edu.ap.group10.leapwebapp.capabilityproject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.project.Project;
import edu.ap.group10.leapwebapp.project.ProjectRepository;

@Service
public class CapabilityProjectService {

    @Autowired
    private CapabilityProjectRepository capabilityProjectRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<CapabilityProject> getCapabilityProject(String capabilityid) {
        List<CapabilityProject> capabilityProjects = new ArrayList<>();
        for (CapabilityProject capabilityProject : capabilityProjectRepository.findAll()){
            if(capabilityProject.getCapability().getId().equals(Long.parseLong(capabilityid))){
                capabilityProjects.add(capabilityProject);
            }
        }
        return capabilityProjects;
    }

    public void addCapabilityProject(String capabilityid, String projectname) {
        Capability capability = capabilityRepository.findById(Long.parseLong(capabilityid))
        .orElseThrow();
        Project project = projectRepository.findByName(projectname);

        CapabilityProject capabilityProject = new CapabilityProject(capability, project);
        if(capabilityProjectRepository.findById(capabilityProject.getId()).isPresent()){
            throw new EntityExistsException("Link already exists");
        } else {
            capabilityProjectRepository.save(capabilityProject);
        }
    }

    public void deleteCapabilityProject(String capabilityprojectid) {
        CapabilityProject oldCapProject = capabilityProjectRepository.findById(Long.parseLong(capabilityprojectid))
        .orElseThrow();
        capabilityProjectRepository.delete(oldCapProject);
    }
    
}
