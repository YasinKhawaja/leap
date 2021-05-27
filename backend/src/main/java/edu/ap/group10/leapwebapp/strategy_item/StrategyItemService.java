package edu.ap.group10.leapwebapp.strategy_item;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.ap.group10.leapwebapp.strategy.Strategy;
import edu.ap.group10.leapwebapp.strategy.StrategyRepository;

@Service
public class StrategyItemService {

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    @Autowired
    private StrategyRepository strategyRepository;

     public List<StrategyItem> getAllStrategyItems() {
      return strategyItemRepository.findAll();
  }

  public List<StrategyItem> getAllStrategiesInStrategy(Long strId) {
    Strategy strategyToFindBy = strategyRepository.findById(strId).orElseThrow();

    return strategyItemRepository.findByStrategy(strategyToFindBy);
}

    public StrategyItem getStrategyItem(Long strId, Long strItemId) {
   
      List<StrategyItem> strItemFound = this.getAllStrategiesInStrategy(strId).stream()
              .filter(strI -> strI.getId().equals(strItemId)).collect(Collectors.toList());
     
      return strItemFound.get(0);
  }

 public StrategyItem createStrategyItem(Long strId, StrategyItem strItem) {
  Strategy strategyToLinkWith = strategyRepository.findById(strId).orElseThrow();

  strItem.setStrategy(strategyToLinkWith);
  
  return strategyItemRepository.save(strItem);
}


 public StrategyItem updateStrategyItem(Long strId, Long strItemId, StrategyItem strItems) {
  
  List<StrategyItem> strItemFound = this.getAllStrategiesInStrategy(strId).stream()
          .filter(strItem -> strItem.getId().equals(strItemId)).collect(Collectors.toList());
  
  StrategyItem strategyItemToUpdate = strItemFound.get(0);
 
  strategyItemToUpdate.setName(strItems.getName());
  strategyItemToUpdate.setDescription(strItems.getDescription());
  
  
  return strategyItemRepository.save(strategyItemToUpdate);
}

public void deleteStrategyItem(Long strId, Long strItemId) {

  List<StrategyItem> strItemFound = this.getAllStrategiesInStrategy(strId).stream()
          .filter(strItem -> strItem.getId().equals(strItemId)).collect(Collectors.toList());
  
  strategyItemRepository.delete(strItemFound.get(0));
}

}
