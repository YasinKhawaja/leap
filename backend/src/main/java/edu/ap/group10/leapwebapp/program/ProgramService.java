package edu.ap.group10.leapwebapp.program;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms(String environmentid) {
        List<Program> programs = new ArrayList<>();
        for (Program program : programRepository.findAll()){
            if(program.getEnvironment().getId().equals(Long.parseLong(environmentid))){
                programs.add(program);
            }
        }
        return programs;
    }

    public Program getProgram(String programid) {
        return programRepository.findById(Long.parseLong(programid))
        .orElseThrow();
    }

    public void updateProgram(String programid, String name, String description) {
        Program oProgram = programRepository.findById(Long.parseLong(programid))
        .orElseThrow();
        oProgram.setName(name);
        oProgram.setDescription(description);
        programRepository.save(oProgram);
    }

    public void deleteProgram(String programid) {
        Program program = programRepository.findById(Long.parseLong(programid))
        .orElseThrow();
        programRepository.delete(program);
    }

    public void addProgram(Program program) {
        programRepository.save(program);
    }
    
}
