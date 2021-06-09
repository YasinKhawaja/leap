package edu.ap.group10.leapwebapp.program;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private EnvironmentService environmentService;

    @GetMapping("/programs/{environmentid}")
    public List<Program> getAllPrograms(@PathVariable String environmentid){
        return programService.getAllPrograms(environmentid);
    }

    @GetMapping("/program/{programid}")
    public Program getProgram(@PathVariable String programid){
        return programService.getProgram(programid);
    }
    
    @PostMapping("/program/{environmentid}")
    public void addProgram(@PathVariable String environmentid, @RequestParam String name, @RequestParam String description){
        Environment environment = environmentService.getEnvironment(Long.parseLong(environmentid));
        Program program = new Program (name, description, environment);
        programService.addProgram(program);
    }
    
    @PutMapping("/program/{programid}")
    public void editProgram(@PathVariable String programid, @RequestParam String name, @RequestParam String description){
        programService.updateProgram(programid, name, description);
    }

    @DeleteMapping("/program/{programid}")
    public void deleteProgram(@PathVariable String programid){
        programService.deleteProgram(programid);
    }
    
}
