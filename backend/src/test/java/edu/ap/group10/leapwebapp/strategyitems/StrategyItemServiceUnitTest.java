 package edu.ap.group10.leapwebapp.strategyitems;

import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
 import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
 import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
 import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;
 import edu.ap.group10.leapwebapp.strategy.Strategy;
 import edu.ap.group10.leapwebapp.strategy.StrategyRepository;
 import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
 import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
 import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

 @SpringBootTest
 @AutoConfigureTestDatabase
 @ExtendWith(MockitoExtension.class)
 public class StrategyItemServiceUnitTest {

     @Mock
     private StrategyItemRepository strategyItemRepository;

     @Mock
     private StrategyRepository strategyRepository;


     @InjectMocks
     private StrategyItemService strategyItemService;


  @Test
      void givenStrategyItems_whenGetAllStrategyItems_returnsAllStrategyItems() {
        // When
        when(strategyItemRepository.findAll()).thenReturn(Arrays.asList(new StrategyItem("test1","description1"),
                new StrategyItem("test2","description2"), new StrategyItem("test3","description3")));

         List<StrategyItem> actualCaFound = strategyItemService.getAllStrategyItems();

        // Then
         verify(strategyItemRepository).findAll();

         assertEquals("test1", actualCaFound.get(0).getName());
         assertEquals("description1", actualCaFound.get(0).getDescription());

         assertEquals("test2", actualCaFound.get(1).getName());
         assertEquals("description2", actualCaFound.get(1).getDescription());

         assertEquals("test3", actualCaFound.get(2).getName());
         assertEquals("description3", actualCaFound.get(2).getDescription());
      }


      @Test
      void  givenStrategyId_whenGetAllStrategyItemsInStrategy_returnsStrategyItemsInStrategy() {
        
         Long strId = 5L;
         Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
         Environment environment = new Environment("testEnvironment",company);
         Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
         strategy.setId(strId);


       Mockito.when(strategyRepository.findById(5L)).thenReturn(Optional.of(strategy));

       StrategyItem strategyItem = new StrategyItem("vfd","fs");
       strategyItem.setStrategy(strategy);

       Mockito.when(strategyItemRepository.findByStrategy(strategy))
       .thenReturn(Arrays.asList(strategyItem));
  

    // when
    List<StrategyItem> strItems = strategyItemService.getAllStrategiesInStrategy(strategy.getId());
     
     // then
     ArgumentCaptor<Strategy> strategyArgumentCaptor = ArgumentCaptor.forClass(Strategy.class);
     verify(strategyItemRepository).findByStrategy(strategyArgumentCaptor.capture());


     assertEquals(strategyItem.getStrategy().getId(), strItems.get(0).getStrategy().getId());
 
     }

// //byname

    
   @Test
   void givenStrategyId_whenGetAStrategyItemInStrategy_returnsStrategyItemInStrategy() {
     // given
     Long strId = 5L;
     Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      Environment environment = new Environment("testEnvironment",company);
     Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
     strategy.setId(strId);

     Mockito.when(strategyRepository.findById(5L)).thenReturn(Optional.of(strategy));

       StrategyItem strategyItem = new StrategyItem("vfd","description1");
       strategyItem.setStrategy(strategy);
       strategyItem.setId(1L);
   
       Mockito.when(strategyItemRepository.findByStrategy(strategy))
       .thenReturn(Arrays.asList(strategyItem));

     // when
          strategyItemService.getStrategyItem(strategy.getId(), strategyItem.getId());
     List<StrategyItem> strItems = strategyItemService.getAllStrategiesInStrategy(strategy.getId());
     List<StrategyItem> strategyItemfound = strItems.stream().filter(strItem -> strItem.getId().equals(strategyItem.getId()))
         .collect(Collectors.toList());

     // then
     assertEquals(strategyItem, strategyItemfound.get(0));

   }

   //create
  


   @Test
   void givenStrategyItem_whenUpdateStrategyItem_returnsUpdatedStrategyItem() {
    Long strId = 5L;
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    Environment environment = new Environment("testEnvironment",company);
    Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
    strategy.setId(strId);

    Mockito.when(strategyRepository.findById(5L)).thenReturn(Optional.of(strategy));


     StrategyItem strategyItem = new StrategyItem("vfd","description1");
       strategyItem.setStrategy(strategy);
       strategyItem.setId(2L);

     String newNameStrategyItem = "Philips";
     String newDescription = "updated";

     Mockito.when(strategyItemRepository.findByStrategy(strategy)).thenReturn(Arrays.asList(strategyItem));

     // when
     strategyItemService.updateStrategyItem(strategy.getId(), strategyItem.getId(), strategyItem);


     List<StrategyItem> strItemsList = strategyItemService.getAllStrategiesInStrategy(strategy.getId());
    List<StrategyItem> strItemFound = strItemsList.stream().filter(strItem -> strItem.getId().equals(strategyItem.getId()))
          .collect(Collectors.toList());
 
      // then
      strItemFound.get(0).setName(newNameStrategyItem);
      strItemFound.get(0).setDescription(newDescription);


    ArgumentCaptor<StrategyItem> strItemArgumentCaptor = ArgumentCaptor.forClass(StrategyItem.class);
    verify(strategyItemRepository).save(strItemArgumentCaptor.capture());

    assertEquals(strItemArgumentCaptor.getValue().getName(), newNameStrategyItem);
   assertEquals(strItemArgumentCaptor.getValue().getDescription(), newDescription);
  }

   @Test
  void givenStrategyIdStrategyItemId_whenDeleteStrategyItem_returnsStrategyItemIsDeleted() {
    Long strId = 5L;
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    Environment environment = new Environment("testEnvironment",company);
   Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
   strategy.setId(strId);
 
    Mockito.when(strategyRepository.findById(5L)).thenReturn(Optional.of(strategy));

    StrategyItem strategyItem = new StrategyItem("testSTrategyItem","description1");
   strategyItem.setStrategy(strategy);
    strategyItem.setId(2L);

     Mockito.when(strategyItemRepository.findByStrategy(strategy)).thenReturn(Arrays.asList(strategyItem));

    // when
    strategyItemService.deleteStrategyItem(strategy.getId(), strategyItem.getId());

     List<StrategyItem> strItemsList = strategyItemService.getAllStrategiesInStrategy(strategy.getId());
     List<StrategyItem> strItemFound = strItemsList.stream().filter(strItem -> strItem.getId().equals(strategyItem.getId()))
          .collect(Collectors.toList());

     // then
     strItemFound.get(0);
     ArgumentCaptor<StrategyItem> strItemArgumentCaptor = ArgumentCaptor.forClass(StrategyItem.class);
    verify(strategyItemRepository).delete(strItemArgumentCaptor.capture());

     assertEquals(strategyItem.getName(), strItemArgumentCaptor.getValue().getName());
   }
 }
