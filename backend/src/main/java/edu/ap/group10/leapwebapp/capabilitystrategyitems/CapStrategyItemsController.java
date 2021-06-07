
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityService;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

@RestController
public class CapStrategyItemsController {

    @Autowired
    private CapabilityService capabilityService;

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    @Autowired
    private StrategyItemService strategyItemService;

    @Autowired
    private CapStrategyItemsService capStrategyItemService;

    //werkt
    @GetMapping("/capstrategyitems/{capabilityId}")
    public List<CapStrategyItems> getCapStrategyItemsByCapability(@PathVariable Long capabilityId) {
        return capStrategyItemService.getCapabilityStrategyItems(capabilityId);
    }


    @PostMapping("/capstrategyitems/{capabilityId}") //{capabilityId}"
    public void  createCapStrategyItems(@PathVariable Long capabilityId,@RequestParam String strategyItemName,@RequestParam StrategicEmphasis strategicEmphasis)
     {
         capStrategyItemService.createCapStrategyItems(capabilityId,strategyItemName,strategicEmphasis);
    }


    @PutMapping("/capstrategyitems/{id}")
    public CapStrategyItems updateCapStrategyItems(@PathVariable Long id, @RequestParam StrategicEmphasis strategicEmphasis)
     {
                
                return capStrategyItemService.updateCapStrategyItems(id,strategicEmphasis);
    }

    @DeleteMapping("/capstrategyitems/{id}")
    public void deleteCapStrategyItems(@PathVariable Long id) {
        capStrategyItemService.deleteCapStrategyItems(id);
    }

}
