
package edu.ap.group10.leapwebapp.capability;

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
public class CapabilityController {

	@Autowired
	private CapabilityService capabilityService;

	// To GET all capabilities
	@GetMapping("/caps")
	public List<Capability> getCapabilities() {
		return capabilityService.getCapabilities();
	}

	// To GET all capabilities in an environment
	@GetMapping("/envs/{envId}/caps")
	public List<Capability> getCapabilitiesInEnvironment(@PathVariable Long envId) {
		return capabilityService.getCapabilitiesInEnvironment(envId);
	}

	// To GET a capability in its environment by name
	@GetMapping("/envs/{envName}/caps/{capName}")
	public Capability getCapabilityInEnvironmentByName(@PathVariable String envName, @PathVariable String capName) {
		return capabilityService.getCapabilityInEnvironmentByName(envName, capName);
	}

	// To CREATE a capability in its environment
	@PostMapping("/envs/{envId}/caps")
	public Capability createCapabilityInEnvironment(@PathVariable Long envId, @RequestParam String name,
			@RequestParam PaceOfChange paceOfChange, @RequestParam Tom tom, @RequestParam Integer resourcesQuality) {
		return capabilityService.createCapabilityInEnvironment(envId, name, paceOfChange, tom, resourcesQuality);
	}

	// To UPDATE a capability in its environment
	@PutMapping("/envs/{envId}/caps/{capId}")
	public Capability updateCapabilityInEnvironment(@PathVariable Long envId, @PathVariable Long capId,
			@RequestParam String name, @RequestParam PaceOfChange paceOfChange, @RequestParam Tom tom,
			@RequestParam Integer resourcesQuality) {
		return capabilityService.updateCapabilityInEnvironment(envId, capId, name, paceOfChange, tom, resourcesQuality);
	}

	// To DELETE a capability in its environment
	@DeleteMapping("/envs/{envId}/caps/{capId}")
	public void deleteCapabilityFromEnvironment(@PathVariable Long envId, @PathVariable Long capId) {
		capabilityService.deleteCapabilityFromEnvironment(envId, capId);
	}

}
