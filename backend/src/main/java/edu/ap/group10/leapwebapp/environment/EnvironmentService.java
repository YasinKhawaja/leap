
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    // To GET all environments
    public List<Environment> getEnvironments() {
        return environmentRepository.findAll();
    }

    // To CREATE an environment
    public Environment createEnvironment(Environment environment) {
        return environmentRepository.save(environment);
    }

    // To UPDATE an environment
    public Environment updateEnvironment(Long environmentId, Environment environment) {
        Environment environmentToUpdate = environmentRepository.findById(environmentId)
                .orElseThrow(ResourceNotFoundException::new);

        environmentToUpdate.setName(environment.getName());

        return environmentRepository.save(environmentToUpdate);
    }

    // To DELETE an environment
    public ResponseEntity<?> deleteEnvironment(Long environmentId) {
        Environment environmentToDelete = environmentRepository.findById(environmentId)
                .orElseThrow(ResourceNotFoundException::new);

        environmentRepository.delete(environmentToDelete);

        return ResponseEntity.ok().body(environmentToDelete);
    }

}
