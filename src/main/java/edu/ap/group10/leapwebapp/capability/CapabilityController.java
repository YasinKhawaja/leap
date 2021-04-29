
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
	private CapabilityRepository repository;

	@PostMapping("/capabilities/add")
	public @ResponseBody String saveCapability(@RequestParam("name") String name, @RequestParam("level") Integer level,
	@RequestParam("paceOfChange") PaceOfChange paceOfChange, @RequestParam("tom") Tom tom, @RequestParam("resourcesQuality") 
	Integer resourcesQuality, @RequestParam("informationQuality") Double informationQuality,
	@RequestParam("applicationFit") Double applicationFit) {
		Capability capability = new Capability(level, name, paceOfChange, tom, resourcesQuality, informationQuality, applicationFit);

		repository.save(capability);
		
		return "Saved";
	}

	@GetMapping("/capabilities")
	public @ResponseBody Iterable<Capability> getAllCapabilities() {
		return repository.findAll();
	}

}
