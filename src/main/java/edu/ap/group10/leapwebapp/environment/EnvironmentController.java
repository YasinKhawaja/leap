
package edu.ap.group10.leapwebapp.environment;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	// To GET all environments
	@GetMapping("/environments")
	public @ResponseBody Iterable<Environment> getAllEnvironments() {
		return environmentRepository.findAll();
	}

	// To CREATE an environment
	@PostMapping("environments/add")
	public ResponseEntity<Environment> addNewEnvironment(@RequestParam String name) {
		ResponseEntity<Environment> response;
		Environment environment = new Environment();

		if (!name.isEmpty()) {
			environment.setName(name);

			environmentRepository.save(environment);

			response = ResponseEntity.ok().body(environment);
		} else {
			response = ResponseEntity.badRequest().body(environment);
		}

		return response;
	}

	// To UPDATE an environment
	@PutMapping("environments/{id}")
	public ResponseEntity<Environment> updateEnvironment(@PathVariable Integer id, @RequestParam String name) {
		ResponseEntity<Environment> response;
		Environment environment = null;

		try {
			if (!name.isEmpty()) {
				environment = environmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

				environment.setName(name);

				environmentRepository.save(environment);

				response = ResponseEntity.ok().body(environment);
			} else {
				response = ResponseEntity.badRequest().body(environment);
			}
		} catch (Exception e) {
			response = ResponseEntity.notFound().build();
		}

		return response;
	}

	// To DELETE an environment
	@DeleteMapping("environments/{id}")
	public ResponseEntity<Environment> deleteEnvironment(@PathVariable Integer id) {
		ResponseEntity<Environment> response;
		Environment environment = null;

		try {
			environment = environmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);

			environmentRepository.delete(environment);

			response = ResponseEntity.ok().body(environment);
		} catch (Exception e) {
			response = ResponseEntity.notFound().build();
		}

		return response;
	}

}
