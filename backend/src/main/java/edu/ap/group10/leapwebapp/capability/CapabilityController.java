package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapabilityController {

	@Autowired
	private CapabilityService capabilityService;

	@GetMapping("/capabilities/all")
	public List<Capability> getAllCapabilities() {
		return capabilityService.getAllCapabilities();
	}

	@GetMapping("/capabilities")
	public List<Capability> getAllCapabilitiesInEnvironment(@RequestParam Long envId) {
		return capabilityService.getAllCapabilitiesInEnvironment(envId);
	}

	@GetMapping("/capabilities/{capId}")
	public Capability getCapability(@RequestParam Long envId, @PathVariable Long capId) {
		return capabilityService.getCapability(envId, capId);
	}

	@PostMapping("/capabilities")
	public Capability createCapability(@RequestParam Long envId, @RequestParam Long parentCapId,
			@RequestBody Capability cap) {
		return capabilityService.createCapability(envId, parentCapId, cap);
	}

	@PutMapping("/capabilities/{capId}")
	public Capability updateCapability(@RequestParam Long envId, @PathVariable Long capId,
			@RequestBody Capability cap) {
		return capabilityService.updateCapability(envId, capId, cap);
	}

	@DeleteMapping("/capabilities/{capId}")
	public void deleteCapability(@RequestParam Long envId, @PathVariable Long capId) {
		capabilityService.deleteCapability(envId, capId);
	}

}
