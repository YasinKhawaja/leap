package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;

@SpringBootTest
@AutoConfigureTestDatabase
public class EnvironmentUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    @Test
    void givenEnvironment_whenToString_returnsEnvironmentToString() {

        // given
        Environment environment = new Environment("Test environment", company);

        // when
        String toString = "Environment(id=null, name=Test environment, capabilities=null, strategies=null, resources=null, itApplications=null, programs=null, businessProcesses=null, company=Company(id=null, vatNumber=1, companyName=Test Company, email=sv@gmail.com, streetName=kerkstraat, houseNumber=3, postcode=5, city=Mortsel, country=België, businessActivity=null, taxOffice=null, approved=false, environments=null))";

        // then
        assertEquals(environment.toString(), toString);
    }

    @Test
    void givenEnvironment_whenHashCode_returnsHashCode() {

        //given
        Environment environment1 = new Environment("Test environment", company);
        Environment environment2 = new Environment("Test environment", company);

        //when
        int a = environment1.hashCode();
        int b = environment2.hashCode();

        //then
        assertEquals(a, b);
    }

    @Test
    void givenEnvironment_whenEquals_returnsTrue() {
        
        //given
        Environment environment1 = new Environment("Test environment", company);
        Environment environment2 = new Environment("Test environment", company);

        //when
        boolean validator = environment1.equals(environment2);

        //then
        assertTrue(validator);
    }

}
