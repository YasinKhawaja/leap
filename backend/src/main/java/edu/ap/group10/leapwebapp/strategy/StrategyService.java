package edu.ap.group10.leapwebapp.strategy;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

 public List<Strategy> getStrategies(Long environmentId){
    List<Strategy> strategies = new ArrayList<>();
    for (Strategy strategy : strategyRepository.findAll()) {
        if(strategy.getEnvironment().getId().equals(environmentId)){
            strategies.add(strategy);
        }
    }
    return strategies;
}

    public Strategy findStrategy(Long id){
        return strategyRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    }
    
    public Strategy createStrategy(Strategy strategy){
       
        return strategyRepository.save(strategy);
    }

  public Strategy updateStrategy(Long strId, Strategy strategy){
    strategy.setId(strId);
    
    Strategy oldStrategy = strategyRepository.findById(strId)
    .orElseThrow(ResourceNotFoundException::new);

    oldStrategy.setName(strategy.getName());
    oldStrategy.setTimeframeFrom(strategy.getTimeframeFrom());
    oldStrategy.setTimeframeTo(strategy.getTimeframeTo());

    
    return strategyRepository.save(oldStrategy);
}

public Boolean deleteStrategy(Long strId){
  
    Strategy oldStrategy = strategyRepository.findById(strId)
    .orElseThrow(ResourceNotFoundException::new);

   // strategyRepository.delete(oldStrategy);

    strategyRepository.delete(oldStrategy);
    if(strategyRepository.findById(strId).isPresent()){
        return true;
    } else {
        return false;
    }
}


}
