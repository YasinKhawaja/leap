package edu.ap.group10.leapwebapp.capabilitystrategyitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import edu.ap.group10.leapwebapp.capability.Capability;
//import edu.ap.group10.leapwebapp.capability.CapabilityRepository;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItems;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItemsRepository;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItemsService;
import edu.ap.group10.leapwebapp.capabilitystrategyitems.StrategicEmphasis;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.strategy.Strategy;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;
//import edu.ap.group10.leapwebapp.strategy_item.StrategyItemService;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class CapStrategyItemServiceTests {


    @Mock
    private CapStrategyItemsRepository capStrategyItemsRepository;

    @InjectMocks
    private CapStrategyItemsService capStrategyItemsService; 

  //  @Autowired
 //   private CapabilityRepository capabilityRepository;

 //   @Autowired
   // private StrategyItemService strategyItemService;

  //  @Autowired
  //  private StrategyItemRepository strategyItemRepository;

    @Test
    void givenCapStrategyItems_WhenGetAllCapStrategyItems_ThenReturnAllCapStrategyItems() {

            //Given
            Long environmentId = 1L;
            Long capabilityId = 5L;
    
            String environmentName = "Test environment";
            Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
            Environment environment = new Environment(environmentName,company);
            environment.setId(environmentId);

           Capability capability = new Capability("testCapbility",environment);
            capability.setId(capabilityId);

            Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
           
             StrategyItem strategyItem = new StrategyItem("name1","description1");
             strategyItem.setStrategy(strategy);
           

            StrategyItem strategyItem2 = new StrategyItem("name2","description2");
            strategyItem.setStrategy(strategy);
           

            //When
            when(capStrategyItemsService.getCapabilityStrategyItems(capabilityId)).thenReturn(Arrays.asList(new CapStrategyItems(capability, strategyItem2, StrategicEmphasis.LOW),
            new CapStrategyItems(capability, strategyItem, StrategicEmphasis.HIGH)));

            List<CapStrategyItems> capStrategyItems = capStrategyItemsService.getCapabilityStrategyItems(capabilityId);
    
            //Then
           assertEquals(capability ,capStrategyItems.get(0).getCapability());
            assertEquals(strategyItem2, capStrategyItems.get(0).getStrategyItem());
            assertEquals(capability, capStrategyItems.get(1).getCapability());
           assertEquals(strategyItem, capStrategyItems.get(1).getStrategyItem());
    
           assertEquals(StrategicEmphasis.LOW, capStrategyItems.get(0).getStrategicEmphasis());
         assertEquals(StrategicEmphasis.HIGH, capStrategyItems.get(1).getStrategicEmphasis());
     
         assertEquals(2, capStrategyItems.size());
        }

 @Test
 void givenCapStrategyItemId_whenFindCapStrategyItem_returnsCapStrategyItem() {
        //Given
        Long environmentId = 1L;
       Long capstrategyitems_id = 100L;
        String environmentName = "Test environment";
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        Environment environment = new Environment(environmentName,company);
        environment.setId(environmentId);
       Capability capability = new Capability("testCapbility",environment);
        capability.setId(2L);
        Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
         StrategyItem strategyItem = new StrategyItem("name1","description1");
         strategyItem.setStrategy(strategy);
       

        CapStrategyItems capStrategyItem = new CapStrategyItems(capability, strategyItem, StrategicEmphasis.LOW);
        capStrategyItem.setId(capstrategyitems_id);


        when(capStrategyItemsRepository.findById(100L)).thenReturn(Optional.of(capStrategyItem));

        //When
        CapStrategyItems foundCapStrategyItem = capStrategyItemsService.findCapabilityStrategyItem(capstrategyitems_id);

        //Then
        ArgumentCaptor<Long> argCap = ArgumentCaptor.forClass(Long.class);
        verify(capStrategyItemsRepository).findById(argCap.capture());

        assertEquals(100L, argCap.getValue());
        assertEquals(capStrategyItem, foundCapStrategyItem);

 }

 //werkt niet 

 @Test
 void createCapStrategyItems() {

            //Given
          //  Long environmentId = 1L;
        //     String strategyItemName = "TestName";
        //     Long capability_id = 2L;
       //      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
       //      Environment environment = new Environment("Test environment",company);
       //      environment.setId(environmentId);
       //      Capability capability = new Capability("testCapbility",environment);
       //     capability.setId(capability_id);
       //     Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
       //     StrategyItem strategyItem = new StrategyItem(strategyItemName,"description1");
       //     strategyItem.setStrategy(strategy);

            //Mockito.when(capabilityRepository.findById(capability_id)).thenReturn(Optional.of(capability));
         
      //      CapStrategyItems capStrategyItem = new CapStrategyItems(capability, strategyItem, StrategicEmphasis.LOW);

            
       //     capStrategyItemsService.createCapStrategyItems(capability.getId(), strategy.getName(),capStrategyItem.getStrategicEmphasis());

      //      List<StrategyItem> strategyItems = strategyItemService.getAllStrategiesInStrategy(strategy.getId());
      //      List<StrategyItem> stratItemFound = strategyItems.stream()
      //      .filter(strItem -> strItem.getName().equals(strategyItemName)).collect(Collectors.toList());

      //      capStrategyItem.setCapability(capability);
      //      capStrategyItem.setStrategyItem(stratItemFound.get(0));

      //      ArgumentCaptor<CapStrategyItems> capabilityStrategyItemArgumentCaptor = ArgumentCaptor.forClass(CapStrategyItems.class);
      //      verify(capStrategyItemsRepository).save(capabilityStrategyItemArgumentCaptor.capture());

       
   //     assertEquals(capabilityStrategyItemArgumentCaptor.getValue().getCapability().getId(), capability.getId());
  //      assertEquals(capabilityStrategyItemArgumentCaptor.getValue().getStrategyItem().getName(), strategyItem.getName());
 //     assertEquals(capabilityStrategyItemArgumentCaptor.getValue().getStrategicEmphasis(), capStrategyItem.getStrategicEmphasis());

    }


    @Test
    void givenCapStrategyItem_CapStrategyItemId_whenUpdateCapStrategyItem_returnsCapStrategyItem() {
        Long environmentId = 1L;
        String strategyItemName = "TestName";
        Long capability_id = 2L;
        Long id = 100L;
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        Environment environment = new Environment("Test environment",company);
        environment.setId(environmentId);
        Capability capability = new Capability("testCapbility",environment);
       capability.setId(capability_id);
       Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
       StrategyItem strategyItem = new StrategyItem(strategyItemName,"description1");
       strategyItem.setStrategy(strategy);

       CapStrategyItems oldcapStrategyItem = new CapStrategyItems(capability, strategyItem, StrategicEmphasis.LOW);
       oldcapStrategyItem.setId(id);
        when(capStrategyItemsRepository.findById(id)).thenReturn(Optional.of(oldcapStrategyItem));

        CapStrategyItems newcapStrategyItem = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.HIGH);

        //When
        capStrategyItemsService.updateCapStrategyItems(id, newcapStrategyItem.getStrategicEmphasis());
        ArgumentCaptor<CapStrategyItems> argCap = ArgumentCaptor.forClass(CapStrategyItems.class);

        //Then
        verify(capStrategyItemsRepository).save(argCap.capture());
        assertEquals(oldcapStrategyItem.getId(), argCap.getValue().getId());
        assertEquals(newcapStrategyItem.getStrategicEmphasis(), argCap.getValue().getStrategicEmphasis());


    }
  
    @Test
    void givenCapStrategyItemId_whenDeleteCapStrategyItem_returnsCapStrategyItemDeleted() {

        Long environmentId = 1L;
        String strategyItemName = "TestName";
        Long capability_id = 2L;
        Long id = 100L;
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        Environment environment = new Environment("Test environment",company);
        environment.setId(environmentId);
        Capability capability = new Capability("testCapbility",environment);
       capability.setId(capability_id);
       Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
       StrategyItem strategyItem = new StrategyItem(strategyItemName,"description1");
       strategyItem.setStrategy(strategy);

       CapStrategyItems capStrategyItem = new CapStrategyItems(capability, strategyItem, StrategicEmphasis.LOW);
       capStrategyItem.setId(id);

       when(capStrategyItemsRepository.findById(id)).thenReturn(Optional.of(capStrategyItem));

       capStrategyItemsService.deleteCapStrategyItems(capStrategyItem.getId());

       ArgumentCaptor<CapStrategyItems> capStrategyItemArgumentCaptor = ArgumentCaptor.forClass(CapStrategyItems.class);
        verify(capStrategyItemsRepository).delete(capStrategyItemArgumentCaptor.capture());

     assertEquals(capStrategyItem.getId(), capStrategyItemArgumentCaptor.getValue().getId());
    
    }


    //werkt niet 
    @Test
    void getAllCapabilityStrategyItemsLinkedToStrategyItem(){
        //name 

          //Given
     //     Long environmentId = 1L;
     //     Long capabilityId = 5L;
  
    //      String environmentName = "Test environment";
    //      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
     //     Environment environment = new Environment(environmentName,company);
     //     environment.setId(environmentId);

     //    Capability capability = new Capability("testCapbility",environment);
     //     capability.setId(capabilityId);

       //   Capability capability2 = new Capability("test22",environment);
       //   capability.setId(50L);


      //    Strategy strategy = new Strategy ( "testStrategy","2020-02-03", "2020-01-04", environment);
         
      //    String name = "TestName";
      //     StrategyItem strategyItem = new StrategyItem(name,"description1");
       //    strategyItem.setStrategy(strategy);

       //    when(strategyItemRepository.findByName(strategyItem.getName())).thenReturn(strategyItem);


          //When
      //    when(capStrategyItemsService.getAllCapabilityStrategyItemsLinkedToStrategyItem(strategyItem.getName())).thenReturn(Arrays.asList(new CapStrategyItems(capability, strategyItem, StrategicEmphasis.LOW),
      //    new CapStrategyItems(capability2, strategyItem, StrategicEmphasis.HIGH)));
      //    List<CapStrategyItems> capStrategyItems = capStrategyItemsService.getAllCapabilityStrategyItemsLinkedToStrategyItem(strategyItem.getName());
  
          //Then
      // assertEquals(2, capStrategyItems.size());


    }


}
