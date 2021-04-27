package edu.ap.group10.leapwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.jpa.Capability;
import edu.ap.group10.leapwebapp.jpa.CapabilityRepository;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CapabilityController {
    @Autowired
    private CapabilityRepository repository;

    @PostMapping("/capabilities/add")
    public @ResponseBody String saveCapability(@RequestParam("name") String name){
        repository.save(new Capability(name));
        return "saved!!";
    }

    @GetMapping("/capabilities")
    public @ResponseBody Iterable<Capability> getAllCapabilities(){
        return repository.findAll();
    }

}
