package edu.ap.group10.leapwebapp.strategy;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@RestController
public class StrategyController {
	@Autowired
	private StrategyService strategyService;

	@Autowired
    private EnvironmentService environmentService;


    @GetMapping("/strategies/{environmentId}")
    public List<Strategy> getAllStrategies(@PathVariable Long environmentId){
        return strategyService.getStrategies(environmentId);
    }
	

  @PostMapping("/strategies/{environmentId}")
  public Strategy addStrategy(@PathVariable Long environmentId, @RequestParam("name") String name, @RequestParam("timeframeFrom") String timeframeFrom,
  @RequestParam("timeframeTo") String timeframeTo){

    Environment environment = environmentService.getEnvironment(environmentId);
    Strategy strategy = new Strategy(name,timeframeFrom ,timeframeTo,environment);
    return  strategyService.createStrategy(strategy);
  }

   @PutMapping("/strategies/{strId}")
   public Strategy updateStrategy(@PathVariable Long strId ,@RequestParam("name") String name, @RequestParam("timeframeFrom") String timeframeFrom,
   @RequestParam("timeframeTo") String timeframeTo) {
	   Strategy oldStrategy = strategyService.findStrategy(strId);
	  
	   Strategy newStrategy = new Strategy(name,timeframeFrom ,timeframeTo,oldStrategy.getEnvironment());

	   return strategyService.updateStrategy(oldStrategy.getId(), newStrategy);
   }


   @DeleteMapping("/strategies/{strId}")
   public void deleteStrategy(@PathVariable Long strId){        
	  
	   strategyService.deleteStrategy(strId);
   }
}
