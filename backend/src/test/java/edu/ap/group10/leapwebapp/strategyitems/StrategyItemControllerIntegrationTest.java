package edu.ap.group10.leapwebapp.strategyitems;

import org.junit.jupiter.api.AfterEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;

import edu.ap.group10.leapwebapp.strategy.StrategyRepository;
//import edu.ap.group10.leapwebapp.strategy.StrategyService;

import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class StrategyItemControllerIntegrationTest {

  //  @Autowired
    //private MockMvc mvc;

    @Autowired
    private StrategyRepository strategyRepository;

  //  @Autowired
  //  private StrategyService strategyService;

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    //@Autowired
   // private StrategyItemService strategyItemService;



    @AfterEach
    void tearDown(){
        strategyItemRepository.deleteAll();
        strategyRepository.deleteAll();
      
    }    
}
