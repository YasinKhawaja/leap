
package edu.ap.group10.leapwebapp.environment;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {

	@Autowired
	private EnvironmentService environmentService;

	// To GET all environments
	@GetMapping("/environments")
	public List<Environment> getAllEnvironments(@RequestParam String companyid) {
		return environmentService.getAllEnvironments(companyid);
	}

	// To GET an environment
	@GetMapping("/environments/{id}")
	public Environment getEnvironment(@PathVariable Long id) throws NoSuchElementException {
		return environmentService.getEnvironment(id);
	}

	// To CREATE an environment
	@PostMapping("/environments")
	public Environment createEnvironment(@RequestParam String name, @RequestParam String companyid) throws ArithmeticException {
		return environmentService.createEnvironment(name, companyid);
	}

	// To UPDATE an environment
	@PutMapping("/environments/{id}")
	public Environment updateEnvironment(@PathVariable Long id, @RequestParam String name)
			throws NoSuchElementException, ArithmeticException {
		return environmentService.updateEnvironment(id, name);
	}

	// To DELETE an environment
	@DeleteMapping("/environments/{id}")
	public void deleteEnvironment(@PathVariable Long id) throws IllegalArgumentException {
		environmentService.deleteEnvironment(id);
	}

}
