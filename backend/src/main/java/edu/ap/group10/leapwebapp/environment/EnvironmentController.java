
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EnvironmentController {

	@Autowired
	private EnvironmentRepository environmentRepository;

	// To GET all environments
	@GetMapping("/environments")
	public List<Environment> getEnvironments() {
		return environmentRepository.findAll();
	}

	// To CREATE an environment
	@PostMapping("/environments")
	public Environment createEnvironment(@RequestBody Environment environment) {
		return environmentRepository.save(environment);
	}

	// To UPDATE an environment
	@PutMapping("/environments/{environmentName}")
	public Environment updateEnvironment(@PathVariable String environmentName,
			@RequestBody Environment environmentRequest) {
		if (environmentRepository.existsByName(environmentName)) {
			Environment environmentToUpdate = environmentRepository.findByName(environmentName);
			environmentToUpdate.setName(environmentRequest.getName());

			return environmentRepository.save(environmentToUpdate);
		} else {
			throw new ResourceNotFoundException(
					"Environment with name [" + environmentName + "] not found, please try again.");
		}
	}

	// To DELETE an environment
	@DeleteMapping("/environments/{environmentName}")
	public ResponseEntity<?> deleteEnvironment(@PathVariable String environmentName) {
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
