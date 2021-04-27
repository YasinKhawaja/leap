package edu.ap.group10.leapwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.group10.leapwebapp.jpa.Capability;
import edu.ap.group10.leapwebapp.jpa.CapabilityRepository;

@Controller
public class CapabilityController {
    @Autowired
    private CapabilityRepository repository;

    @PostMapping("/capabilities/add")
    public String saveCapability(@RequestParam("name") String name){
        repository.save(new Capability(name));
        return "saved!!";
    }
}
