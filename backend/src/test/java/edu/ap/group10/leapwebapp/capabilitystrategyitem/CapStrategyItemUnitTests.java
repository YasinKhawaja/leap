// package edu.ap.group10.leapwebapp.capabilitystrategyitem;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;

// import edu.ap.group10.leapwebapp.capability.Capability;
// import edu.ap.group10.leapwebapp.capabilitystrategyitems.CapStrategyItems;
// import edu.ap.group10.leapwebapp.capabilitystrategyitems.StrategicEmphasis;
// import edu.ap.group10.leapwebapp.company.Company;
// import edu.ap.group10.leapwebapp.environment.Environment;
// import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;

// @SpringBootTest
// @AutoConfigureTestDatabase
// public class CapStrategyItemUnitTests {

//     private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
//     "Mortsel", "België", "", "");

//     private Environment environment = new Environment("Test environment", company);
//     private Capability capability = new Capability("testCapbility",environment);
           
//     StrategyItem strategyItem = new StrategyItem("name1","description1");
    
//     @Test
//     void givenCapStrategyItem_whenToString_returnsCapStrategyItemToString() {

//     // Given
 
//     CapStrategyItems capStrategyItem = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.LOW);

//     // When
//     String toString = "CapStrategyItems(id=null, capability="+ capability.toString() +", strategyItem="+ strategyItem.toString()+", strategicEmphasis="+StrategicEmphasis.LOW+")";

//     // Then
//     assertEquals(capStrategyItem.toString(), toString);
//     }

//     @Test
//     void givenCapStrategyItem_whenHashCode_returnsHashCode() {

//         // Given
//         CapStrategyItems capStrategyItem = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.LOW);
//         CapStrategyItems capStrategyItemB = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.LOW);

//         // When
//         int a = capStrategyItem.hashCode();
//         int b = capStrategyItemB.hashCode();

//         // Then
//         assertEquals(a, b);
//     }

//     @Test
//     void givenCapStrategyItem_whenEquals_returnsTrue() {

//         // Given
//         CapStrategyItems capStrategyItem = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.LOW);
//         CapStrategyItems capStrategyItemB = new CapStrategyItems(capability,strategyItem,StrategicEmphasis.LOW);

//         // When
//         boolean validator = capStrategyItem.equals(capStrategyItemB);

//         // Then
//         assertTrue(validator);
//     }

    
// }
