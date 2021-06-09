package edu.ap.group10.leapwebapp.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects(String programid) {
        List<Project> projects = new ArrayList<>();
        for (Project project : projectRepository.findAll()){
            if (project.getProgram().getId().equals(Long.parseLong(programid))){
                projects.add(project);
            }
        }
        return projects;
    }

    public Project getProject(String projectid) {
        return projectRepository.findById(Long.parseLong(projectid))
        .orElseThrow();
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public void updateProject(String projectid, String name, String description) {
        Project oProject = projectRepository.findById(Long.parseLong(projectid))
        .orElseThrow();
        oProject.setName(name);
        oProject.setDescription(description);
        projectRepository.save(oProject);
    }

    public void deleteProject(String projectid) {
        Project project = projectRepository.findById(Long.parseLong(projectid))
        .orElseThrow();
        projectRepository.delete(project);
    }

    public List<Project> getAllProjectsOfEnvironment(String environmentid) {
        List<Project> projects = new ArrayList<>();
        for (Project project : projectRepository.findAll()){
            if (project.getProgram().getEnvironment().getId().equals(Long.parseLong(environmentid))){
                projects.add(project);
            }
        }
        return projects;
    }
    
}
