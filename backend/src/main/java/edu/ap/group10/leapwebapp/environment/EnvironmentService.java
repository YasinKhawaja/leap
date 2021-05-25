
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return environmentRepository.findById(id).orElseThrow();
    }

    // To CREATE an environment
    public Environment createEnvironment(String name) {
        if (environmentRepository.existsByName(name)) {
            throw new ArithmeticException("Environment already exists!");
        }

        Environment envToSave = new Environment(name);

        return environmentRepository.save(envToSave);
    }

    // To UPDATE an environment
    public Environment updateEnvironment(Long id, String name) {
        Environment envToUpdate = environmentRepository.findById(id).orElseThrow();

        if (environmentRepository.existsByName(name)) {
            throw new ArithmeticException("An environment with this name already exists!");
        }

        envToUpdate.setName(name);

        return environmentRepository.save(envToUpdate);
    }

    // To DELETE an environment
    public void deleteEnvironment(Long id) {
        environmentRepository.deleteById(id);
    }

}
