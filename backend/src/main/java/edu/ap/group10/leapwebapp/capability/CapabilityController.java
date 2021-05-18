
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CapabilityController {

	@Autowired
	private CapabilityService capabilityService;

	// To GET all capabilities
	@GetMapping("/capabilities/all")
	public List<Capability> getAllCapabilities() {
		return capabilityService.getAllCapabilities();
	}

	// To GET all capabilities in an environment
	@GetMapping("/capabilities")
	public List<Capability> getAllCapabilitiesInEnvironment(@RequestParam Long envId) {
		return capabilityService.getAllCapabilitiesInEnvironment(envId);
	}

	// To GET a capability in an environment
	@GetMapping("/capabilities/{capId}")
	public Capability getCapability(@RequestParam Long envId, @PathVariable Long capId) {
		return capabilityService.getCapability(envId, capId);
	}

	// To CREATE a capability in an environment
	@PostMapping("/capabilities")
	public Capability createCapability(@RequestParam Long envId, @RequestParam Long parentCapId,
			@RequestBody Capability cap) {
		return capabilityService.createCapability(envId, parentCapId, cap);
	}

	// To UPDATE a capability in its environment
	@PutMapping("/capabilities/{capId}")
	public Capability updateCapability(@RequestParam Long envId, @PathVariable Long capId,
			@RequestBody Capability cap) {
		return capabilityService.updateCapability(envId, capId, cap);
	}

	// To DELETE a capability in its environment
	@DeleteMapping("/capabilities/{capId}")
	public void deleteCapability(@RequestParam Long envId, @PathVariable Long capId) {
		capabilityService.deleteCapability(envId, capId);
	}

}
