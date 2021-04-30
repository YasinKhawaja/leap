
package edu.ap.group10.leapwebapp.environment;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class EnvironmentController {

	@Autowired
	private EnvironmentRepository environmentRepository;

	// To CREATE an environment
	@PostMapping("environments/add")
	public @ResponseBody String addNewEnvironment(@RequestParam String name) {
		Environment environment = new Environment(name);

		environmentRepository.save(environment);

		return String.format("Environment with ID:%s saved", environment.getId());
	}

	// To READ all the environments
	@GetMapping("/environments")
	public @ResponseBody Iterable<Environment> getAllEnvironments() {
		return environmentRepository.findAll();
	}

	// To UPDATE an environment
	@PutMapping("environments/{id}")
	public @ResponseBody String updateEnvironment(@PathVariable(value = "id") Integer environmentId, @RequestParam String name) {
		Environment environment;

		try {
			environment = environmentRepository.findById(environmentId).orElseThrow(() -> new EntityNotFoundException(
					String.format("Environment with ID:%s not found", environmentId)));

			environment.setName(name);

			environmentRepository.save(environment);
		} catch (Exception e) {
			return e.getMessage();
		}

		return String.format("Environment with ID:%s updated", environmentId);
	}

	// To DELETE an environment
	@DeleteMapping("environments/{id}")
	public @ResponseBody String deleteEnvironment(@PathVariable(value = "id") Integer environmentId) {
		Environment environment;

		try {
			environment = environmentRepository.findById(environmentId).orElseThrow(() -> new EntityNotFoundException(
					String.format("Environment with ID:%s not found", environmentId)));

			environmentRepository.delete(environment);
		} catch (Exception e) {
			return e.getMessage();
		}

		return String.format("Environment with ID:%s deleted", environment.getId());
	}

}
