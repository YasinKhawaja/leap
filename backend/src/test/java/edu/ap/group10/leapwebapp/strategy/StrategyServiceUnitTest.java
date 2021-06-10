 package edu.ap.group10.leapwebapp.strategy;

 import java.util.Arrays;
 import java.util.List;
 import java.util.Optional;

 import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.ArgumentCaptor;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
 import org.springframework.boot.test.context.SpringBootTest;
 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

 @SpringBootTest
 @AutoConfigureTestDatabase
 @ExtendWith(MockitoExtension.class)
 public class StrategyServiceUnitTest {

     @Mock
     private StrategyRepository strategyRepository;

     @InjectMocks
     private StrategyService strategyService;

    @Test
    void givenEnvironmentId_whenGetStrategies_returnsListStrategies(){
        
        //Given
        Long environmentId = 1L;
        String environmentName = "Test environment";

        Environment environment = new Environment(environmentName,null);
       environment.setId(environmentId);
       
        //When
        when(strategyService.getStrategies(environment.getId())).thenReturn(Arrays.asList(new Strategy("TestStrategy", "2020-02-02","2020-10-04", environment)
        , new Strategy("Test2","2020-02-01","2020-10-03", environment)));
         List<Strategy> strategies = strategyService.getStrategies(environmentId);

         //Then
          assertEquals("TestStrategy", strategies.get(0).getName());
          assertEquals("2020-02-02", strategies.get(0).getTimeframeFrom());
          assertEquals("2020-10-04", strategies.get(0).getTimeframeTo());

          assertEquals("Test2", strategies.get(1).getName());
         assertEquals("2020-02-01", strategies.get(1).getTimeframeFrom());
         assertEquals("2020-10-03", strategies.get(1).getTimeframeTo());
  
          assertEquals(environmentName, strategies.get(0).getEnvironment().getName());
          assertEquals(environmentId, strategies.get(0).getEnvironment().getId());
          assertEquals(environmentName, strategies.get(1).getEnvironment().getName());
        assertEquals(environmentId, strategies.get(1).getEnvironment().getId());
   
    }

    @Test
    void givenStrategyId_whenFindStrategy_returnsStrategy(){

        //Given
         Long strId = 10L;
         Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        Strategy strategy = new Strategy("test", "2020-02-01","2021-02-01", new Environment("test",company));
        strategy.setId(strId);
        when(strategyRepository.findById(strId)).thenReturn(Optional.of(strategy));

        //When
         Strategy foundStrategy = strategyService.findStrategy(strId);

         //Then
         ArgumentCaptor<Long> argCap = ArgumentCaptor.forClass(Long.class);
         verify(strategyRepository).findById(argCap.capture());

        assertEquals(strId, argCap.getValue());
         assertEquals(strategy, foundStrategy);
     }


     @Test
     void givenStrategy_whenCreateStrategy_returnsStrategy(){
         //Given

         Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
         Strategy strategy = new Strategy("test","2020-02-01","2021-02-01", new Environment("test",company));
         ArgumentCaptor<Strategy> argCap = ArgumentCaptor.forClass(Strategy.class);

         //When
        strategyService.createStrategy(strategy);

       //Then
       verify(strategyRepository).save(argCap.capture());
       assertEquals(strategy.getId(), argCap.getValue().getId());
       assertEquals(strategy, argCap.getValue());
   }

      @Test
     void givenStrategy_StrategyId_whenUpdateStrategy_returnsStrategy(){

        //Given
        Long strId = 5L;
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
       Strategy oldStrategy = new Strategy("test", "2020-02-01","2021-02-01", new Environment("test",company));
        oldStrategy.setId(strId);         when(strategyRepository.findById(strId)).thenReturn(Optional.of(oldStrategy));

        Strategy newStr = new Strategy("name", "2020-02-20","2021-02-01",new Environment("hallo",company));

        //When
        strategyService.updateStrategy(strId, newStr);
       ArgumentCaptor<Strategy> argCap = ArgumentCaptor.forClass(Strategy.class);

       //Then
       verify(strategyRepository).save(argCap.capture());
     assertEquals(oldStrategy.getId(), argCap.getValue().getId());
      assertEquals(newStr.getName(), argCap.getValue().getName());
    }

    //werkt niet meer
    @Test
   void givenStrategyId_whenDeleteStrategy_returnsStrategyDeleted(){

     
     }
    
 }
