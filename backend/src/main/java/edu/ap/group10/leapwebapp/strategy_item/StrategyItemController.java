package edu.ap.group10.leapwebapp.strategy_item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StrategyItemController {

    @Autowired
	private StrategyItemService strategyItemService;

  
//	@GetMapping("/strategies/{strId}/strsItems/{environmentId}")
	//public List<StrategyItem> getCapabilitiesInEnvironment(@PathVariable String environmentId,@PathVariable Long strsId) {
		
//	}

}
