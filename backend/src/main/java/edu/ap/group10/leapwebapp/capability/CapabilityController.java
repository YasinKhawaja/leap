
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CapabilityController {

	@Autowired
	private CapabilityService capabilityService;

	// To GET all capabilities
	@GetMapping("/capabilities")
	public List<Capability> getCapabilities() {
		return capabilityService.getCapabilities();
	}

	// To GET all capabilities in an environment
	@GetMapping("/environments/{environmentId}/capabilities")
	public List<Capability> getCapabilitiesInEnvironment(@PathVariable Long environmentId) {
		return capabilityService.getCapabilitiesInEnvironment(environmentId);
	}

	// To CREATE a capability in an environment
	@PostMapping("/environments/{environmentId}/capabilities")
	public Capability createCapabilityInEnvironment(@PathVariable Long environmentId,
			@RequestBody Capability capabilityReq) {
		return capabilityService.createCapabilityInEnvironment(environmentId, capabilityReq);
	}

	// To UPDATE a capability in an environment
	@PutMapping("/environments/{environmentId}/capabilities/{capabilityId}")
	public Capability updateCapabilityInEnvironment(@PathVariable Long environmentId, @PathVariable Long capabilityId,
			@RequestBody Capability capabilityReq) {
		return capabilityService.updateCapabilityInEnvironment(environmentId, capabilityId, capabilityReq);
	}

	// To DELETE a capability in an environment
	@DeleteMapping("/environments/{environmentId}/capabilities/{capabilityId}")
	public ResponseEntity<?> deleteCapabilityFromEnvironment(@PathVariable Long environmentId,
			@PathVariable Long capabilityId) {
		return capabilityService.deleteCapabilityFromEnvironment(environmentId, capabilityId);
	}

}
