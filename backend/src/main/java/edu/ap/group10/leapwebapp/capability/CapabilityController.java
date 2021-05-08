
package edu.ap.group10.leapwebapp.capability;

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

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CapabilityController {

	@Autowired
	private EnvironmentRepository environmentRepository;

	@Autowired
	private CapabilityRepository capabilityRepository;

	// Added error handling when adding a capability with the same name of an
	// already existing capability
	/*
	 * @PostMapping("/capabilities/add") public @ResponseBody String
	 * saveCapability(@RequestParam("name") String name,
	 * 
	 * @RequestParam("paceOfChange") PaceOfChange paceOfChange, @RequestParam("tom")
	 * Tom tom,
	 * 
	 * @RequestParam("resourcesQuality") Integer resourcesQuality,
	 * HttpServletResponse response) { try { Capability capability = new
	 * Capability(name, paceOfChange, tom, resourcesQuality);
	 * 
	 * capabilityRepository.save(capability);
	 * 
	 * return "Saved"; } catch (DataIntegrityViolationException exception) { throw
	 * new ResponseStatusException(HttpStatus.CONFLICT,
	 * "Capability with this name already exists.", exception); } }
	 */

	// Added error handling when trying to delete a capability that doesn't exist
	/*
	 * @DeleteMapping("/capabilities/delete/{name}") public @ResponseBody String
	 * deleteCapability(@PathVariable String name, HttpServletResponse response) {
	 * try { Capability capability = capabilityRepository.findByName(name);
	 * 
	 * capabilityRepository.delete(capability);
	 * 
	 * return "Deleted"; } catch (InvalidDataAccessApiUsageException exception) {
	 * throw new ResponseStatusException(HttpStatus.CONFLICT,
	 * "Capability you tried to delete does not exist.", exception); } }
	 */

	/*
	 * @PostMapping("/capabilities/searchOne") public @ResponseBody List<Capability>
	 * searchOneCapabilityByName(@RequestParam("name") String name) { return
	 * capabilityRepository.searchOneByName(name); }
	 */

	/*
	 * @PostMapping("/capabilities/edit/{originalName}") public @ResponseBody String
	 * editCapability(@RequestParam("name") String newName,
	 * 
	 * @RequestParam("paceOfChange") PaceOfChange paceOfChange, @RequestParam("tom")
	 * Tom tom,
	 * 
	 * @RequestParam("resourcesQuality") Integer resourcesQuality, @PathVariable
	 * String originalName) {
	 * 
	 * Capability editedCapability = capabilityRepository.findByName(originalName);
	 * 
	 * editedCapability.setName(newName);
	 * editedCapability.setPaceOfChange(paceOfChange); editedCapability.setTom(tom);
	 * editedCapability.setResourcesQuality(resourcesQuality);
	 * 
	 * capabilityRepository.save(editedCapability);
	 * 
	 * return "Edited"; }
	 */

	// To GET all capabilities
	/*
	 * @GetMapping("/environments/capabilities") public Iterable<Capability>
	 * getAllCapabilities() { return capabilityRepository.findAll(); }
	 */

	// To GET all capabilities from an environment
	/*
	 * @GetMapping("/environments/{environmentId}/capabilities") public
	 * Iterable<Capability> getAllCapabilitiesByEnvironmentId(@PathVariable Integer
	 * environmentId) { return
	 * capabilityRepository.findAllByEnvironmentId(environmentId); }
	 */

	// To GET all capabilities
	@GetMapping("/capabilities")
	public List<Capability> getAllCapabilities() {
		return capabilityRepository.findAll();
	}

	// To GET all capabilities in an environment
	@GetMapping("/environments/{environmentName}/capabilities")
	public List<Capability> getCapabilitiesInEnvironment(@PathVariable String environmentName) {
		Environment environment = environmentRepository.findByName(environmentName);
		return capabilityRepository.findByEnvironment(environment);
	}

	// To CREATE a capability
	@PostMapping("/environments/{environmentName}/capabilities")
	public Capability createCapabilityInEnvironment(@PathVariable String environmentName,
			@RequestBody Capability capability) {
		if (environmentRepository.existsByName(environmentName)) {
			Environment environmentToLink = environmentRepository.findByName(environmentName);

			capability.setEnvironment(environmentToLink);

			return capabilityRepository.save(capability);
		} else {
			throw new ResourceNotFoundException(
					"Environment with name [" + environmentName + "] not found, please try again.");
		}
	}

	// To UPDATE a capability
	@PutMapping("/environments/{environmentName}/capabilities/{capabilityName}")
	public Capability updateCapabilityInEnvironment(@PathVariable String environmentName,
			@PathVariable String capabilityName, @RequestBody Capability capabilityRequest) {

		if (!environmentRepository.existsByName(environmentName)) {
			throw new ResourceNotFoundException(
					"Environment with name [" + environmentName + "] not found, please try again.");
		}

		if (capabilityRepository.existsByName(capabilityName)) {
			Capability capabilityToRename = capabilityRepository.findByName(capabilityName);
			capabilityToRename.setName(capabilityRequest.getName());

			return capabilityRepository.save(capabilityToRename);
		} else {
			throw new ResourceNotFoundException(
					"Capability with name [" + capabilityName + "] not found, please try again.");
		}
	}

	// To DELETE a capability
	@DeleteMapping("/environments/{environmentName}/capabilities/{capabilityName}")
	public ResponseEntity<?> deleteCapabilityFromEnvironment(@PathVariable String environmentName,
			@PathVariable String capabilityName) {

		if (!environmentRepository.existsByName(environmentName)) {
			throw new ResourceNotFoundException(
					"Environment with name [" + environmentName + "] not found, please try again.");
		}

		if (capabilityRepository.existsByName(capabilityName)) {
			Capability capabilityToDelete = capabilityRepository.findByName(capabilityName);

			capabilityRepository.delete(capabilityToDelete);

			return ResponseEntity.ok().build();
		} else {
			throw new ResourceNotFoundException(
					"Capability with name [" + capabilityName + "] not found, please try again.");
		}
	}

}
