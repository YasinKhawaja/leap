
package edu.ap.group10.leapwebapp.capability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CapabilityController {
	@Autowired
	private CapabilityRepository capabilityRepository;

	@PostMapping("/capabilities/add")
	public @ResponseBody String addNewCapability(@RequestParam("name") String name) {
		Capability capability = new Capability(name);

		capabilityRepository.save(capability);
		
		return "Saved";
	}

	@GetMapping("/capabilities")
	public @ResponseBody Iterable<Capability> getAllCapabilities() {
		return capabilityRepository.findAll();
	}

}
