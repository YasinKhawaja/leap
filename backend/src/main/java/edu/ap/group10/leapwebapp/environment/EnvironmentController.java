
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
	@GetMapping("/envs")
	public List<Environment> getAllEnvironments() {
		return environmentService.getAllEnvironments();
	}

	// To GET an environment by name
	@GetMapping("/environments/{name}")
	public Environment getEnvironmentByName(@PathVariable String name) {
		return environmentService.getEnvironmentByName(name);
	}

	// To CREATE an environment
	@PostMapping("/envs")
	public Environment createEnvironment(@RequestParam String name) {
		return environmentService.createEnvironment(name);
	}

	// To UPDATE an environment
	@PutMapping("/envs/{id}")
	public Environment updateEnvironment(@PathVariable Long id, @RequestParam String name) {
		Environment updatedEnv = null;
		try {
			updatedEnv = environmentService.updateEnvironment(id, name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedEnv;
	}

	// To DELETE an environment
	@DeleteMapping("/envs/{id}")
	public void deleteEnvironment(@PathVariable Long id) {
		environmentService.deleteEnvironment(id);
	}

}
