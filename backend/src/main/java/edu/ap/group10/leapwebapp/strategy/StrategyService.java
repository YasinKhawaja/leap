package edu.ap.group10.leapwebapp.strategy;

import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private EnvironmentService environmentService;



 public List<Strategy> getStrategies(String environmentId){
    List<Strategy> strategies = new ArrayList<Strategy>();
    for (Strategy strategy : strategyRepository.findAll()) {
        if(strategy.getEnvironment().getId().equals(Long.parseLong(environmentId))){
            strategies.add(strategy);
        }
    }
    return strategies;
}


    public Strategy findStrategy(Long id){
        return strategyRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new);
    }
    
    public Boolean isPresent(Long id) {
        return strategyRepository.findById(id).isPresent();
    }
    
    //create strategy
    public Strategy createStrategy(Strategy strategy){
        
        return strategyRepository.save(strategy);
    }

  public Strategy updateStrategy(Long strId, Strategy strategy){
    strategy.setId(strId);
    
    Strategy oldStrategy = strategyRepository.findById(strId)
    .orElseThrow(ResourceNotFoundException::new);

    strategyRepository.delete(oldStrategy);
    return strategyRepository.save(strategy);
}

public void deleteStrategy(Long strId){
   // System.out.println(strId);
    Strategy oldStrategy = strategyRepository.findById(strId)
    .orElseThrow(ResourceNotFoundException::new);

    strategyRepository.delete(oldStrategy);
}


}
