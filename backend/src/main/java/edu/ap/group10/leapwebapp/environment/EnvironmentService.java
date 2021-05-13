
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
    public List<Environment> getEnvironments() {
        return environmentRepository.findAll();
    }

    // To GET an environment by name
    public Environment getEnvironment(String name) {
        return environmentRepository.findByName(name);
    }

    // To CREATE an environment
    public Environment createEnvironment(String name) {
        Environment environment = new Environment(name);

        return environmentRepository.save(environment);
    }

    // To UPDATE an environment
    public Environment updateEnvironment(Long id, String name) {
        Environment environment = environmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        environment.setName(name);

        return environmentRepository.save(environment);
    }

    // To DELETE an environment
    public void deleteEnvironment(Long id) {
        environmentRepository.deleteById(id);
    }

}
