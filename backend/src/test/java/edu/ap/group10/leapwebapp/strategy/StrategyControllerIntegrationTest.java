package edu.ap.group10.leapwebapp.strategy;

import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;



@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class StrategyControllerIntegrationTest {

  //  @Autowired
 //   private MockMvc mvc;

    @Autowired
    private StrategyRepository strategyRepository;

  //  @Autowired
    //private StrategyService strategyService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    //@Autowired
    //private EnvironmentService environmentService;

    @AfterEach
    void tearDown(){
        strategyRepository.deleteAll();
        environmentRepository.deleteAll();
    }
    



}
