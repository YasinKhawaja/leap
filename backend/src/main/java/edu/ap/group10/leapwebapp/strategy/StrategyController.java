package edu.ap.group10.leapwebapp.strategy;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class StrategyController {
	@Autowired
	private StrategyService strategyService;

	@Autowired
    private EnvironmentService environmentService;

    @Autowired
private StrategyRepository temp;

    @GetMapping("/strategies")
    public List<Strategy> getAllStrategies(){
        return temp.findAll();
    }

    @GetMapping("/strategies/{environmentId}")
    public List<Strategy> getStrategy(@PathVariable Long environmentId){
        return strategyService.getStrategies(environmentId);
    }
	
  @PostMapping("/strategies/{environmentId}")
  public Strategy addStrategy(@PathVariable Long environmentId, @RequestBody Strategy strategy){

    Environment environment = environmentService.getEnvironment(environmentId);
    strategy.setEnvironment(environment);

    return  strategyService.createStrategy(strategy);
  }

   @PutMapping("/strategies/{strId}")
   public Strategy updateStrategy(@PathVariable Long strId ,@RequestBody Strategy strategy ) {
	   Strategy oldStrategy = strategyService.findStrategy(strId);
       oldStrategy.setEnvironment(oldStrategy.getEnvironment());

	   return strategyService.updateStrategy(oldStrategy.getId(), strategy);
   }


   @DeleteMapping("/strategies/{strId}")
   public void deleteStrategy(@PathVariable Long strId){        
	  
	   strategyService.deleteStrategy(strId);
   }
}
