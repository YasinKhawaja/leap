package edu.ap.group10.leapwebapp.strategy;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.databind.ObjectWriter;


import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyService;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class StrategyControllerIntegrationTest {

    @Autowired
   private MockMvc mvc;

    @Autowired
    private StrategyRepository strategyRepository;

   @Autowired
    private StrategyService strategyService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private CompanyService companyService;


    @AfterEach
    void tearDown(){
        strategyRepository.deleteAll();
        environmentRepository.deleteAll();
    }
    
    @Test
    @WithMockUser
    public void givenStrategies_whenGetAllStrategies_returnsAllStrategies() throws Exception {
    
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    companyService.addCompany(company);

    String name = "Siemens";
    Environment env = new Environment(name, company);
    environmentRepository.save(env);

    String strategyName = "Jan";
    Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
    Strategy strategy2 = new Strategy("strategyName","2020-02-01","2021-02-03",env);
    
    strategyService.createStrategy(strategy);
    strategyService.createStrategy(strategy2);
   

    mvc.perform(get("/strategies"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json("[{'name':'Jan'}, {'name':'strategyName'}]"));

    }

    @Test
    @WithMockUser
   
   public void givenEnvironmentId_whenGetAllStrategiesInEnvironment_returnsAllStrategies() throws Exception {
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    companyService.addCompany(company);

    String name = "Siemens";
    Environment env = new Environment(name, company);
    Long envId = env.getId();
    environmentRepository.save(env);

    String strategyName = "Jan";
    Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
    Strategy strategy2 = new Strategy("strategyName","2020-02-01","2021-02-03",env);

    strategyService.createStrategy(strategy);
    strategyService.createStrategy(strategy2);
    

    mvc.perform(get("/strategies/{envId}",envId))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json("[{'name':'Jan'}, {'name':'strategyName'}]"));
  }
	

  @Test
    @WithMockUser
    public void givenStrategyToCreate_whenCreateStrategy_returnsStrategyCreated() throws Exception {

        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
    
       Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();
    
        String strategyName = "Jan";
        Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",environment);
        Strategy strategy1=strategyService.createStrategy(strategy);
        Long strId =strategy.getId();
        strategy.setId(strId);
     
        
       ObjectMapper mapper = new ObjectMapper();
       mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
       ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(strategy1);

       mvc.perform(post("/strategies/{envId}",environmentId).with(csrf()).param("name", strategyName).param("timeframeFrom" ,strategy1.getTimeframeFrom()).param("timeframeTo", strategy1.getTimeframeTo())
            .contentType(MediaType.APPLICATION_JSON)
           .accept(MediaType.APPLICATION_JSON)
           .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Jan'}"));
    }

    @Test
    @WithMockUser
    public void givenStrategyIdUpdatedStrategy_whenUpdateStrategy_returnsUpdatedStrategy() throws Exception {

     Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
    Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
      
      String strategyName = "Jan";
      String date1 = "2020-02-01";
      String date2 = "2021-02-03";
      Strategy strategy = new Strategy(strategyName,date1,date2,environment);

      Strategy strategy1=strategyService.createStrategy(strategy);
      Long strId =strategy1.getId();
      
      strategy1.setName("HalloTezst");

      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
      String requestJson = ow.writeValueAsString(strategy1);

      
      mvc.perform(put("/strategies/{strId}", strId).with(csrf())     
        .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
          .content(requestJson))
          .andExpect(status().isOk())
         .andDo(print())
          .andExpect(content().json("{'name':'HalloTezst'}"));
    }

    @Test
    @WithMockUser
    public void givenStrategyId_whenDeleteStrategy_returnsStrategyDeleted() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
    Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
      
      String strategyName = "Jan";
      String date1 = "2020-02-01";
      String date2 = "2021-02-03";
      Strategy strategy = new Strategy(strategyName,date1,date2,environment);

      Strategy strategy1=strategyService.createStrategy(strategy);
      Long strId =strategy1.getId();

        mvc.perform(delete("/strategies/{strId}", strId).with(csrf()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }
    

}
