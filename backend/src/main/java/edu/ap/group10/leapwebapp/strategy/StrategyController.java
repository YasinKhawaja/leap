package edu.ap.group10.leapwebapp.strategy;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StrategyController {
	@Autowired
	private StrategyService strategyService;

	@Autowired
    private EnvironmentService environmentService;


    @GetMapping("/strategies/{environmentId}")
    public @ResponseBody List<Strategy> getAllStrategies(@PathVariable String environmentId){
        return strategyService.getStrategies(environmentId);
    }
	

  @PostMapping("/strategies/{environmentId}")
  public ResponseEntity<Strategy> addStrategy(@PathVariable String environmentId, @RequestParam("name") String name, @RequestParam("timeframeFrom") String timeframeFrom,
  @RequestParam("timeframeTo") String timeframeTo){

	  Environment environment = environmentService.getEnvironmentById(Long.parseLong(environmentId));
	  Strategy strategy = new Strategy(name,timeframeFrom ,timeframeTo,environment);
	  strategyService.createStrategy(strategy);
	  
	  ResponseEntity<Strategy> respEntity;

	  Boolean present = strategyService.isPresent(strategy.getId());

	  //null check and true check
	  if(present != null && present){
		  respEntity = ResponseEntity.ok().body(strategy);
	  }
	  else {
		  respEntity = ResponseEntity.badRequest().body(strategy);
	  }
	  
	  return respEntity;
  }



   @PutMapping("/strategies/{strId}")
   public Strategy updateStrategy(@PathVariable String strId ,@RequestParam("name") String name, @RequestParam("timeframeFrom") String timeframeFrom,
   @RequestParam("timeframeTo") String timeframeTo) {
	   Strategy oldStrategy = strategyService.findStrategy(Long.parseLong(strId));
	  
	   Strategy newStrategy = new Strategy(name,timeframeFrom ,timeframeTo,oldStrategy.getEnvironment());

	   return strategyService.updateStrategy(oldStrategy.getId(), newStrategy);
   }


   @DeleteMapping("/strategies/{strId}")
   public void deleteStrategy(@PathVariable String strId){        
	   Long strategyId = Long.parseLong(strId);
	   strategyService.deleteStrategy(strategyId);
   }



}
