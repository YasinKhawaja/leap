
package edu.ap.group10.leapwebapp.capability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	// BUG : When there are capabilites with the same name , and you try to delete one of them
	//       you get the following error ' query did not return a unique result' --> fixed by saying that 
	//		 column 'name' of the capability has to be unique --> still need to catch this exception when trying
	//		 to add an existing capability + It doesnt take in account the capital letters , so 'a' and 'A' are
	//		 considered the same 'name'
	@DeleteMapping("/capabilities/delete/{name}")
	public @ResponseBody String deleteCapability(@PathVariable String name) {
		Capability capability = repository.findByName(name);
		
		repository.delete(capability);

		return "Deleted";
	}
	
}
