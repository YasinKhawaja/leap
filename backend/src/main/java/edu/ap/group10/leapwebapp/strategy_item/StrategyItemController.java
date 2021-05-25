package edu.ap.group10.leapwebapp.strategy_item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StrategyItemController {

    @Autowired
	private StrategyItemService strategyItemService;


	@GetMapping("/strsItems/all")
	public List<StrategyItem> getAllCapabilities() {
		return strategyItemService.getAllStrategyItems();
	}

	@GetMapping("/strsItems")
	public List<StrategyItem> getAllStrategiesInStrategy(@RequestParam Long strId) {
		return strategyItemService.getAllStrategiesInStrategy(strId);
	}


	@GetMapping("/strsItems/{strItemId}")
	public StrategyItem getStrategyItem(@RequestParam Long strId, @PathVariable Long strItemId) {
		return strategyItemService.getStrategyItem(strId, strItemId);
	}

//	@GetMapping("/strategies/{strId}/strsItems/{environmentId}")
	//public List<StrategyItem> getCapabilitiesInEnvironment(@PathVariable Long environmentId,@PathVariable Long strId) {
//		return strategyItemService.getStrategyItem(environmentId, strId);
		
//	}

//	@GetMapping("/strategies/{environmentId}")
  //  public @ResponseBody List<Strategy> getAllStrategies(@PathVariable String environmentId){
  //      return strategyService.getStrategies(environmentId);
  //  }

}
