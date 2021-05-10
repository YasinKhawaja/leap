
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private EnvironmentService environmentService;

	// To GET all environments
	@GetMapping("/environments")
	public List<Environment> getEnvironments() {
		return environmentService.getEnvironments();
	}

	// To CREATE an environment
	@PostMapping("/environments")
	public Environment createEnvironment(@RequestBody Environment environment) {
		return environmentService.createEnvironment(environment);
	}

	// To UPDATE an environment
	@PutMapping("/environments/{environmentName}")
	public Environment updateEnvironment(@PathVariable String environmentName, @RequestBody Environment environment) {
		Environment updatedEnvironment = null;
		try {
			updatedEnvironment = environmentService.updateEnvironment(environmentName, environment);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedEnvironment;
	}

	// To DELETE an environment
	@DeleteMapping("/environments/{environmentName}")
	public ResponseEntity<?> deleteEnvironment(@PathVariable String environmentName) {
		ResponseEntity<?> response = null;
		try {
			response = environmentService.deleteEnvironment(environmentName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

}
