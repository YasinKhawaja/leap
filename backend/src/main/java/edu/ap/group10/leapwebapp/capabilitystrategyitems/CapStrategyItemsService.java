
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;

@Service
public class CapStrategyItemsService {

    @Autowired
    private CapStrategyItemsRepository capStrategyItemsRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    public List<CapStrategyItems> getCapabilityStrategyItems(Long capabilityId) {
        List<CapStrategyItems> capStrategyItems = new ArrayList<>();
        for (CapStrategyItems capStrategyItem : capStrategyItemsRepository.findAll()) {
            if (capStrategyItem.getCapability().getId().equals(capabilityId)) {
                capStrategyItems.add(capStrategyItem);
            }
        }
        return capStrategyItems;
    }

    public CapStrategyItems findCapabilityStrategyItem(Long id) {
        return capStrategyItemsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public CapStrategyItems createCapStrategyItems(Long capabilityId, String strategyItemName,
            StrategicEmphasis strategicEmphasis) {

        Capability capability = capabilityRepository.findById(capabilityId).orElseThrow();

        List<StrategyItem> stratItemFound = strategyItemRepository.findAll().stream()
                .filter(strItem -> strItem.getName().equals(strategyItemName)).collect(Collectors.toList());

        StrategyItem stratItem = stratItemFound.get(0);

        if (capStrategyItemsRepository.findById(Long.parseLong(capabilityId.toString() + stratItem.getId().toString()))
                .isPresent()) {
            throw new EntityExistsException("This link already exists");
        } else {
            CapStrategyItems capStrategyItem = new CapStrategyItems(capability, stratItem, strategicEmphasis);
            return capStrategyItemsRepository.save(capStrategyItem);
        }
    }

    public CapStrategyItems updateCapStrategyItems(Long id, StrategicEmphasis strategicEmphasis) {
        CapStrategyItems capabilityStrategyItems = findCapabilityStrategyItem(id);
        capabilityStrategyItems.setStrategicEmphasis(strategicEmphasis);

        return capStrategyItemsRepository.save(capabilityStrategyItems);

    }

    public void deleteCapStrategyItems(Long id) {

        CapStrategyItems oldcapStrategyItem = capStrategyItemsRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        capStrategyItemsRepository.delete(oldcapStrategyItem);

    }

}
