package edu.ap.group10.leapwebapp.businessprocess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
public class BusinessProcessUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenBusinessProcess_whenToString_returnsBusinessToString() {

        //given 
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);

        //when
        String toString = "BusinessProcess(id=null, name=Sales, description=Income of the year 2021, "
        + "environment=Environment(id=null, name=Test environment, capabilities=null, strategies=null, "
        + "resources=null, itApplications=null, programs=null, businessProcesses=null, company=Company(id=null, "
        + "vatNumber=1, companyName=Test Company, email=sv@gmail.com, streetName=kerkstraat, houseNumber=3, postcode=5, "
        + "city=Mortsel, country=België, businessActivity=null, taxOffice=null, environments=null)))";

        //then
        assertEquals(toString, businessProcess.toString());
    }

    @Test
    void givenBusinessProcess_whenHashCode_returnsHashCode() {

        //given 
        BusinessProcess businessProcess1 = new BusinessProcess("Sales", "Income of the year 2021", environment);
        BusinessProcess businessProcess2 = new BusinessProcess("Sales", "Income of the year 2021", environment);

        //when
        int a = businessProcess1.hashCode();
        int b = businessProcess2.hashCode();

        //then
        assertEquals(a, b);
    }

    @Test
    void givenBusinessProcess_whenEquals_returnsTrue() {

        //given 
        BusinessProcess businessProcess1 = new BusinessProcess("Sales", "Income of the year 2021", environment);
        BusinessProcess businessProcess2 = new BusinessProcess("Sales", "Income of the year 2021", environment);

        //when
        boolean validator = businessProcess1.equals(businessProcess2);

        //then
        assertTrue(validator);
    }


    
}
