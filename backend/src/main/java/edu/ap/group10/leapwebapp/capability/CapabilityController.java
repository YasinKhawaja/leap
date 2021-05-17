
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

	/////// CAPABILITIES LEVEL 1 ///////
	// To GET all capabilities
	@GetMapping("/caps")
	public List<Capability> getAllCapabilities() {
		return capabilityService.getAllCapabilities();
	}

	// To GET all capabilities in an environment
	@GetMapping("/envs/{envId}/caps")
	public List<Capability> getAllCapabilitiesInEnvironment(@PathVariable Long envId) {
		return capabilityService.getAllCapabilitiesInEnvironment(envId);
	}

	// To GET a capability in its environment
	@GetMapping("/envs/{envId}/caps/{capId}")
	public Capability getCapability(@PathVariable Long envId, @PathVariable Long capId) {
		return capabilityService.getCapability(envId, capId);
	}

	// To CREATE a capability in its environment
	@PostMapping("/envs/{envId}/caps")
	public Capability createCapability(@PathVariable Long envId, @RequestParam String name,
			@RequestParam PaceOfChange paceOfChange, @RequestParam Tom tom, @RequestParam Integer resourcesQuality) {
		return capabilityService.createCapability(envId, name, paceOfChange, tom, resourcesQuality);
	}

	// To UPDATE a capability in its environment
	@PutMapping("/envs/{envId}/caps/{capId}")
	public Capability updateCapability(@PathVariable Long envId, @PathVariable Long capId, @RequestParam String name,
			@RequestParam PaceOfChange paceOfChange, @RequestParam Tom tom, @RequestParam Integer resourcesQuality) {
		return capabilityService.updateCapability(envId, capId, name, paceOfChange, tom, resourcesQuality);
	}

	// To DELETE a capability in its environment
	@DeleteMapping("/envs/{envId}/caps/{capId}")
	public void deleteCapability(@PathVariable Long envId, @PathVariable Long capId) {
		capabilityService.deleteCapability(envId, capId);
	}

	/////// SUB CAPABILITIES LEVEL 2-3 ///////
	// To CREATE a subcapability in a capability
	@PostMapping("/envs/{envId}/caps/{capId}/subcaps")
	public Capability createSubcapability(@PathVariable Long envId, @PathVariable Long capId,
			@RequestBody Capability cap) {
		return capabilityService.createSubcapability(envId, capId, cap);
	}

	// To UPDATE a subcapability in a capability
	@PutMapping("/envs/{envId}/caps/{capId}/subcaps/{subcapId}")
	public Capability updateSubcapability(@PathVariable Long envId, @PathVariable Long capId,
			@PathVariable Long subcapId, @RequestBody Capability cap) {
		return capabilityService.updateSubcapability(envId, capId, subcapId, cap);
	}

	// To DELETE a subcapability in a capability
	@DeleteMapping("/envs/{envId}/caps/{capId}/subcaps/{subcapId}")
	public void deleteSubcapability(@PathVariable Long envId, @PathVariable Long capId, @PathVariable Long subcapId) {
		capabilityService.deleteSubcapability(envId, capId, subcapId);
	}

}
