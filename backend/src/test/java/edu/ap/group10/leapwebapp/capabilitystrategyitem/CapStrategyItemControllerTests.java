package edu.ap.group10.leapwebapp.capabilitystrategyitem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;

//import edu.ap.group10.leapwebapp.capability.Capability;
//import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
//import edu.ap.group10.leapwebapp.capability.CapabilityService;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItemsRepository;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItemsService;
//import edu.ap.group10.leapwebapp.capabilitystrategyitems.StrategicEmphasis;
//import edu.ap.group10.leapwebapp.company.Company;
//import edu.ap.group10.leapwebapp.company.CompanyService;
//import edu.ap.group10.leapwebapp.environment.Environment;
//import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
//import edu.ap.group10.leapwebapp.strategy.Strategy;
//import edu.ap.group10.leapwebapp.strategy.StrategyRepository;
//import edu.ap.group10.leapwebapp.strategy.StrategyService;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class CapStrategyItemControllerTests {

    @Mock
    private CapStrategyItemsRepository capStrategyItemsRepository;

    @InjectMocks
    private CapStrategyItemsService capStrategyItemsService;

    //@Autowired
   // private MockMvc mvc;

  //  @Autowired
   // private StrategyRepository strategyRepository;

  // @Autowired
  // private StrategyService strategyService;

  //  @Autowired
  //  private StrategyItemRepository strategyItemRepository;

  //  @Autowired
  //  private StrategyItemService strategyItemService;

  //  @Autowired
  //  private EnvironmentRepository environmentRepository;

  //  @Autowired
  //  private CompanyService companyService;

   // @Autowired
  //  private CapabilityRepository capabilityRepository;

  //  @Autowired
  //  private CapabilityService capabilityService;
    
    @AfterEach
    void tearDown(){
      //  strategyItemRepository.deleteAll();
      //  strategyRepository.deleteAll();
      //  environmentRepository.deleteAll();
      
    }    

    @Test
    @WithMockUser
    public void getCapStrategyItemsByCapability() throws Exception {
    

    }

    
}
