
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

    public List<Environment> getEnvironments() {
        return environmentRepository.findAll();
    }

    public Environment createEnvironment(Environment environment) {
        return environmentRepository.save(environment);
    }

    public Environment updateEnvironment(String environmentName, Environment environment) {
        if (environmentRepository.existsByName(environmentName)) {
            Environment environmentToUpdate = environmentRepository.findByName(environmentName);
            environmentToUpdate.setName(environment.getName());

            return environmentRepository.save(environmentToUpdate);
        } else {
            throw new ResourceNotFoundException(
                    "Environment with name [" + environmentName + "] not found, please try again.");
        }
    }

    public ResponseEntity<?> deleteEnvironment(String environmentName) {
        if (environmentRepository.existsByName(environmentName)) {
            Environment environmentToDelete = environmentRepository.findByName(environmentName);
            environmentRepository.delete(environmentToDelete);

            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException(
                    "Environment with name [" + environmentName + "] not found, please try again.");
        }
    }

}
