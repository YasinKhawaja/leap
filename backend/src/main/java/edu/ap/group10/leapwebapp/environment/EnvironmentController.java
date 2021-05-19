
package edu.ap.group10.leapwebapp.environment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EnvironmentController {

	@Autowired
	private EnvironmentService environmentService;

	// To GET all environments
	@GetMapping("/environments")
	public List<Environment> getAllEnvironments() {
		return environmentService.getAllEnvironments();
	}

	// To GET an environment
	@GetMapping("/environments/{id}")
	public Environment getEnvironment(@PathVariable Long id) {
		Environment env = null;
		try {
			env = environmentService.getEnvironment(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return env;
	}

	// To GET if an environment name already exists
	@GetMapping("/environments/exists")
	public Boolean existsByName(@RequestParam String name) {
		return environmentService.existsByName(name);
	}

	// To CREATE an environment
	@PostMapping("/environments")
	public Environment createEnvironment(@RequestParam String name) {
		Environment createdEnv = null;
		try {
			createdEnv = environmentService.createEnvironment(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return createdEnv;
	}

	// To UPDATE an environment
	@PutMapping("/environments/{id}")
	public Environment updateEnvironment(@PathVariable Long id, @RequestParam String name) {
		Environment updatedEnv = null;
		try {
			updatedEnv = environmentService.updateEnvironment(id, name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return updatedEnv;
	}

	// To DELETE an environment
	@DeleteMapping("/environments/{id}")
	public void deleteEnvironment(@PathVariable Long id) {
		environmentService.deleteEnvironment(id);
	}

}
