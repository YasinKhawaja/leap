package edu.ap.group10.leapwebapp.strategy_item;

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
public class StrategyItemController {

    @Autowired
	private StrategyItemService strategyItemService;


	@GetMapping("/strategyItems/all")
	public List<StrategyItem> getAllCapabilities() {
		return strategyItemService.getAllStrategyItems();
	}

	@GetMapping("/strategyItems")
	public List<StrategyItem> getAllStrategiesInStrategy(@RequestParam Long strId) {
		return strategyItemService.getAllStrategiesInStrategy(strId);
	}

	@GetMapping("/strategyItems/strategy")
	public List<StrategyItem> getAllStrategiesInStrategyByName(@RequestParam String strategyName) {
		return strategyItemService.getAllStrategiesInStrategyByName(strategyName);
	}

	@GetMapping("/strategyItems/{strItemId}")
	public StrategyItem getStrategyItem(@RequestParam Long strId, @PathVariable Long strItemId) {
		return strategyItemService.getStrategyItem(strId, strItemId);
	}

	@PostMapping("/strategyItems")
	public StrategyItem createStrategyItem(@RequestParam Long strId,
			@RequestBody StrategyItem strItem) {
		return strategyItemService.createStrategyItem(strId, strItem);
	}


	@PutMapping("/strategyItems/{strItemId}")
	public StrategyItem updateStrategyItem(@RequestParam Long strId, @PathVariable Long strItemId,
			@RequestBody StrategyItem strategyItem) {
		return strategyItemService.updateStrategyItem(strId, strItemId, strategyItem);
	}

	@DeleteMapping("/strategyItems/{strItemId}")
	public void deleteStrategyItem(@RequestParam Long strId, @PathVariable Long strItemId) {
		strategyItemService.deleteStrategyItem(strId, strItemId);
	}


}
