
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CapabilityController {
	@Autowired
	private CapabilityRepository repository;

	// Added error handling, it also shows in the console in angular webpage. TO DO: Make it more user friendly
	// so that it shows on the webpage and not in the devtools under console
	@PostMapping("/capabilities/add")
	public @ResponseBody String saveCapability(@RequestParam("name") String name, @RequestParam("level") Integer level,
	@RequestParam("paceOfChange") PaceOfChange paceOfChange, @RequestParam("tom") Tom tom, @RequestParam("resourcesQuality") 
	Integer resourcesQuality, @RequestParam("informationQuality") Double informationQuality,
	@RequestParam("applicationFit") Double applicationFit, HttpServletResponse response) {
		try{
		Capability capability = new Capability(level, name, paceOfChange, tom, resourcesQuality, informationQuality, applicationFit);

		repository.save(capability);
		
		return "Saved";
		}
		catch(DataIntegrityViolationException exception){
			throw new ResponseStatusException( HttpStatus.CONFLICT, "Capability with this name already exists", exception);
		}
	}

	// first time after adding a capability, it doesnt show up in the list. You have to manually hard refresh. 
	// --> Database gets called too early ? 
	@GetMapping("/capabilities")
	public @ResponseBody Iterable<Capability> getAllCapabilities() {
		return repository.findAll();
	}

	@DeleteMapping("/capabilities/delete/{name}")
	public @ResponseBody String deleteCapability(@PathVariable String name) {
		Capability capability = repository.findByName(name);
		
		repository.delete(capability);

		return "Deleted";
	}

	@PostMapping("/capabilities/searchOne")
	public @ResponseBody List<Capability> searchOneCapabilityByName(@RequestParam("name") String name) {
		return repository.searchOneByName(name);
	}

	@PostMapping("/capabilities/edit/{originalName}")
	public @ResponseBody String editCapability(@RequestParam("name") String newName, @RequestParam("level") Integer level,
	@RequestParam("paceOfChange") PaceOfChange paceOfChange, @RequestParam("tom") Tom tom, @RequestParam("resourcesQuality") 
	Integer resourcesQuality, @RequestParam("informationQuality") Double informationQuality,
	@RequestParam("applicationFit") Double applicationFit, @PathVariable String originalName) {
		
		Capability editedCapability = repository.findByName(originalName);

		editedCapability.setName(newName);
		editedCapability.setLevel(level);
		editedCapability.setPaceOfChange(paceOfChange);
		editedCapability.setTom(tom);
		editedCapability.setResourcesQuality(resourcesQuality);
		editedCapability.setInformationQuality(informationQuality);
		editedCapability.setApplicationFit(applicationFit);

		repository.save(editedCapability);
	
		return "Edited";
	}
	
}
