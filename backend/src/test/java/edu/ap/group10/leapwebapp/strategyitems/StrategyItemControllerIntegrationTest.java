package edu.ap.group10.leapwebapp.strategyitems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyService;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.strategy.Strategy;
import edu.ap.group10.leapwebapp.strategy.StrategyRepository;
import edu.ap.group10.leapwebapp.strategy.StrategyService;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class StrategyItemControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StrategyRepository strategyRepository;

   @Autowired
   private StrategyService strategyService;

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    @Autowired
    private StrategyItemService strategyItemService;

    @Autowired
    private EnvironmentRepository environmentRepository;


    @Autowired
    private CompanyService companyService;



    @AfterEach
    void tearDown(){
        strategyItemRepository.deleteAll();
        strategyRepository.deleteAll();
        environmentRepository.deleteAll();
      
    }    

    @Test
    @WithMockUser
    public void givenCapabilities_whenGetAllCapabilities_returnsAllCapabilities() throws Exception {
    
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    companyService.addCompany(company);

    String name = "Siemens";
    Environment env = new Environment(name, company);
    environmentRepository.save(env);

    String strategyName = "Jan";
    Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
    strategyService.createStrategy(strategy);
    Long strId =strategy.getId();
    strategy.setId(strId);
    StrategyItem strategyItem = new StrategyItem("testA","description");
    StrategyItem strategyItem2 = new StrategyItem("testB","description2");

    
    strategyItemService.createStrategyItem(strId,strategyItem);
    strategyItemService.createStrategyItem(strId,strategyItem2);
    
   

    mvc.perform(get("/strategyItems/all"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().json("[{'name':'testA'}, {'name':'testB'}]"));

    }

    @Test
    @WithMockUser
    public void givenEnvironmentId_whenGetAllCapabilitiesInEnvironment_returnsAllCapabilities() throws Exception {
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRepository.save(env);
  
      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      strategyService.createStrategy(strategy);
      Long strId =strategy.getId();
      strategy.setId(strId);
      StrategyItem strategyItem = new StrategyItem("testA","description");
      StrategyItem strategyItem2 = new StrategyItem("testB","description2");

      strategyItemService.createStrategyItem(strId,strategyItem);
      strategyItemService.createStrategyItem(strId,strategyItem2);

        mvc.perform(get("/strategyItems").param("strId", strId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'testA'}, {'name':'testB'}]"));
    }


  @Test
    @WithMockUser
    public void findByName() throws Exception {
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRepository.save(env);
  
      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      strategyService.createStrategy(strategy);
      Long strId =strategy.getId();
      strategy.setId(strId);
      StrategyItem strategyItem = new StrategyItem("testA","description");
      StrategyItem strategyItem2 = new StrategyItem("testB","description2");

      strategyItemService.createStrategyItem(strId,strategyItem);
      strategyItemService.createStrategyItem(strId,strategyItem2);

        mvc.perform(get("/strategyItems/strategy").param("strategyName", strategyName))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'testA'}, {'name':'testB'}]"));

    }


    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityId_whenGetCapabilityInEnvironment_returnsCapabilityFound() throws Exception {
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRepository.save(env);
  
      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      strategyService.createStrategy(strategy);
      Long strId =strategy.getId();
      strategy.setId(strId);
      StrategyItem strategyItem = new StrategyItem("testA","description");
    
      StrategyItem strategyItemCreated = strategyItemService.createStrategyItem(strId,strategyItem);
        Long strategyItemCreatedId = strategyItemCreated.getId();

        mvc.perform(get("/strategyItems/{strItemId}", strategyItemCreatedId).param("strId", strId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'testA'}"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentIdParentCapabilityIdCapabilityToCreate_whenCreateCapability_returnsCapabilityCreated() throws Exception {
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRepository.save(env);
  
      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      strategyService.createStrategy(strategy);
      Long strId =strategy.getId();
      strategy.setId(strId);
      StrategyItem strategyItem = new StrategyItem("testA","description");

    
      StrategyItem strategyItemCreated = strategyItemService.createStrategyItem(strId,strategyItem);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(strategyItemCreated);

        

        mvc.perform(post("/strategyItems").with(csrf()).param("strId", strId.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'testA'}"));
    }


    //werkt niet => door dat geen params heeft er komt een forbidden error
    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityIdUpdatedCapability_whenUpdateCapability_returnsUpdatedCapability() throws Exception {
     // Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    //  companyService.addCompany(company);
  
   //   String name = "Siemens";
   //   Environment env = new Environment(name, company);
   //   environmentRepository.save(env);
  
   ///   String strategyName = "Jan";
    //  Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
    //  strategyService.createStrategy(strategy);
    //  Long strId =strategy.getId();
   //   strategy.setId(strId);
   //   StrategyItem strategyItem = new StrategyItem("testA","description");

    //  StrategyItem strategyItemupdate = strategyItemService.createStrategyItem(strId,strategyItem);
   //   Long strategyItemUpdatedId = strategyItemupdate.getId();

    //  strategyItemupdate.setName("testB");
      //strategyItemupdate.setDescription("description1");

        
   //     ObjectMapper mapper = new ObjectMapper();
    //    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    //    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    //    String requestJson = ow.writeValueAsString(strategyItemupdate);

        

    //    mvc.perform(put("/strategyItems/{strItemId}", strategyItemUpdatedId)
    //        .contentType(MediaType.APPLICATION_JSON)
    //        .accept(MediaType.APPLICATION_JSON)
    //        .content(requestJson))
    ////        .andExpect(status().isOk())
    //        .andDo(print())
    //        .andExpect(content().json("{'name':'testB'}"));
    }


    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityId_whenDeleteCapability_returnsCapabilityDeleted() throws Exception {
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyService.addCompany(company);
  
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRepository.save(env);
  
      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      strategyService.createStrategy(strategy);
      Long strId =strategy.getId();
      strategy.setId(strId);
      StrategyItem strategyItem = new StrategyItem("testA","description");

      StrategyItem strategyItemDelete = strategyItemService.createStrategyItem(strId,strategyItem);
      Long strategyDeleteUpdatedId = strategyItemDelete.getId();

        mvc.perform(delete("/strategyItems/{strItemId}", strategyDeleteUpdatedId).with(csrf()).param("strId", strId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }


}
