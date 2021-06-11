package edu.ap.group10.leapwebapp.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
public class StrategyUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
    "Mortsel", "België", "", "");

    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenStrategy_whenToString_returnsStrategyToString() {

    // Given
    Strategy strategy = new Strategy("test", "2020-02-01","2021-02-01",environment);

    // When
    String toString = "Strategy(id=null, name=test, timeframeFrom=2020-02-01, timeframeTo=2021-02-01, environment="
         + environment.toString() + ", strategyItems=null)";

    // Then
    assertEquals(strategy.toString(), toString);     
    }

    @Test
    void givenStrategy_whenHashCode_returnsHashCode() {

        // Given
        Strategy strategy = new Strategy("test", "2020-02-01","2021-02-01",environment);
        Strategy strategyB = new Strategy("test", "2020-02-01","2021-02-01",environment);

        // When
        int a = strategy.hashCode();
        int b = strategyB.hashCode();

        // Then
        assertEquals(a, b);
    }


    @Test
    void givenStrategy_whenEquals_returnsTrue() {

        // Given
        Strategy strategy = new Strategy("test", "2020-02-01","2021-02-01",environment);
        Strategy strategyB = new Strategy("test", "2020-02-01","2021-02-01",environment);

        // When
        boolean validator = strategy.equals(strategyB);

        // Then
        assertTrue(validator);
    }

    
    
}
