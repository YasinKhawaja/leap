package edu.ap.group10.leapwebapp.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.program.Program;
import edu.ap.group10.leapwebapp.program.ProgramService;

@RestController
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProgramService programService;

    @GetMapping("/projects/{programid}")
    public List<Project> getAllProject(@PathVariable String programid){
        return projectService.getAllProjects(programid);
    }

    @GetMapping("/projects-environment/{environmentid}")
    public List<Project> getAllProjectsFromEnvironment(@PathVariable String environmentid){
        return projectService.getAllProjectsOfEnvironment(environmentid);
    }

    @GetMapping("/project/{projectid}")
    public Project getProject(@PathVariable String projectid){
        return projectService.getProject(projectid);
    }

    @PostMapping("/project/{programid}")
    public void addProject(@PathVariable String programid, @RequestParam String name, @RequestParam String description){
        Program program = programService.getProgram(programid);
        Project project = new Project(name, description, program);
        projectService.addProject(project);
    }

    @PutMapping("/project/{projectid}")
    public void editProject(@PathVariable String projectid, @RequestParam String name, @RequestParam String description){
        projectService.updateProject(projectid, name, description);
    }

    @DeleteMapping ("/project/{projectid}")
    public void deleteProject(@PathVariable String projectid){
        projectService.deleteProject(projectid);
    }
}
