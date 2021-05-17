
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    // To GET all environments
    public List<Environment> getAllEnvironments() {
        return environmentRepository.findAll();
    }

    // To GET an environment
    public Environment getEnvironment(Long id) {
        return environmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    // To GET an environment by name
    public Environment getEnvironmentByName(String name) {
        return environmentRepository.findByName(name);
    }

    // To CREATE an environment
    public Environment createEnvironment(String name) {
        Environment envToSave = new Environment(name);

        return environmentRepository.save(envToSave);
    }

    // To UPDATE an environment
    public Environment updateEnvironment(Long id, String name) {
        Environment envToUpdate = environmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        envToUpdate.setName(name);

        return environmentRepository.save(envToUpdate);
    }

    // To DELETE an environment
    public void deleteEnvironment(Long id) {
        environmentRepository.deleteById(id);
    }

}
