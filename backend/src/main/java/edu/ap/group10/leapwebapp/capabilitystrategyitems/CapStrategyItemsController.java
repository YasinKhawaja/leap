
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CapStrategyItemsController {

    @Autowired
    private CapStrategyItemsService capStrategyItemService;

    @GetMapping("/capstrategyitems/{capabilityId}")
    public List<CapStrategyItems> getCapStrategyItemsByCapability(@PathVariable Long capabilityId) {
        return capStrategyItemService.getCapabilityStrategyItems(capabilityId);
    }

    @GetMapping("/capstrategyitems/linked/{strategyItemName}")
    public List<CapStrategyItems> getAllCapabilityStrategyItemsLinkedToStrategyItem(@PathVariable String strategyItemName) {
        return capStrategyItemService.getAllCapabilityStrategyItemsLinkedToStrategyItem(strategyItemName);
    }

    @PostMapping("/capstrategyitems/{capabilityId}") // {capabilityId}"
    public void createCapStrategyItems(@PathVariable Long capabilityId, @RequestParam String strategyItemName,
            @RequestParam StrategicEmphasis strategicEmphasis) {
        capStrategyItemService.createCapStrategyItems(capabilityId, strategyItemName, strategicEmphasis);
    }

    @PutMapping("/capstrategyitems/{id}")
    public CapStrategyItems updateCapStrategyItems(@PathVariable Long id,
            @RequestParam StrategicEmphasis strategicEmphasis) {

        return capStrategyItemService.updateCapStrategyItems(id, strategicEmphasis);
    }

    @DeleteMapping("/capstrategyitems/{id}")
    public void deleteCapStrategyItems(@PathVariable Long id) {
        capStrategyItemService.deleteCapStrategyItems(id);
    }

}
