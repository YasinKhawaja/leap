package edu.ap.group10.leapwebapp.strategyitems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;

@SpringBootTest
@AutoConfigureTestDatabase
public class StrategyItemUnitTests {
   
    @Test
    void givenStrategyItem_whenToString_returnsStrategyItemToString() {

    // Given
 
    StrategyItem strategyItem = new StrategyItem("testNaam","description1");

    // When
    String toString = "StrategyItem(id=null, strategy=null, name=testNaam, description=description1)";

    // Then
    assertEquals(strategyItem.toString(), toString);     
    }

    @Test
    void givenStrategyItem_whenHashCode_returnsHashCode() {

        // Given
        StrategyItem strategyItem = new StrategyItem("testNaam","description1");
        StrategyItem strategyItemB = new StrategyItem("testNaam","description1");

        // When
        int a = strategyItem.hashCode();
        int b = strategyItemB.hashCode();

        // Then
        assertEquals(a, b);
    }

    @Test
    void givenStrategyItem_whenEquals_returnsTrue() {

        // Given
        StrategyItem strategyItem = new StrategyItem("testNaam","description1");
        StrategyItem strategyItemB = new StrategyItem("testNaam","description1");

        // When
        boolean validator = strategyItem.equals(strategyItemB);

        // Then
        assertTrue(validator);
    }

    
}
