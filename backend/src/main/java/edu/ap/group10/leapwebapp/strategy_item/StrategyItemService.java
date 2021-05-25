package edu.ap.group10.leapwebapp.strategy_item;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.strategy.Strategy;
import edu.ap.group10.leapwebapp.strategy.StrategyRepository;

@Service
public class StrategyItemService {

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    @Autowired
    private StrategyRepository strategyRepository;

     // To GET all strategies
     public List<StrategyItem> getAllStrategyItems() {
      return strategyItemRepository.findAll();
  }

  // To GET all strategyItem in an strategy
  public List<StrategyItem> getAllStrategiesInStrategy(Long strId) {
    Strategy strategyToFindBy = strategyRepository.findById(strId).orElseThrow();

    return strategyItemRepository.findByStrategy(strategyToFindBy);
}


    // To GET a strategyItem in its Strategy
    public StrategyItem getStrategyItem(Long strId, Long strItemId) {
   
      List<StrategyItem> strItemFound = this.getAllStrategiesInStrategy(strId).stream()
              .filter(strI -> strI.getId().equals(strItemId)).collect(Collectors.toList());
     
      return strItemFound.get(0);
  }


    
    
}
