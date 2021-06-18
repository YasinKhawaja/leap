package edu.ap.group10.leapwebapp.itapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
class ITApplicationUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenITApplication_whenToString_returnsITApplicationToString() {

        // Given
        ITApplication itapplication = new ITApplication("test", "tech", environment);

        // When
        String toString = "ITApplication(id=null, name=test, technology=tech, version=version, acquisitionDate=acquisitionDate, endOfLife=endOfLife, currentScalability=0, expectedScalability=0, currentPerformance=0, expectedPerformance=0, currentSecurityLevel=0, expectedSecurityLevel=0, currentStability=0, expectedStability=0, costCurrency=Eur, currentValueForMoney=0, currentTotalCostPerYear=0.0, toleratedTotalCostPerYear=0.0, timeValue=TIMEVALUE, importanceFactor=0.0, environment=Environment(id=null, name=Test environment, capabilities=null, strategies=null, resources=null, itApplications=null, programs=null, businessProcesses=null, company=Company(id=null, vatNumber=1, companyName=Test Company, email=sv@gmail.com, streetName=kerkstraat, houseNumber=3, postcode=5, city=Mortsel, country=België, businessActivity=null, taxOffice=null, approved=false, environments=null)))";

        // Then
        assertEquals(itapplication.toString(), toString);
    }

    @Test
    void givenITApplication_whenHashCode_returnsHashCode() {

        // Given
        ITApplication itapplication = new ITApplication("test", "tech", environment);
        ITApplication itapplicationb = new ITApplication("test", "tech", environment);

        // When
        int a = itapplication.hashCode();
        int b = itapplicationb.hashCode();

        // Then
        assertEquals(a, b);
    }

    @Test
    void givenITApplication_whenEquals_returnsTrue() {

        // Given
        ITApplication itapplication = new ITApplication("test", "tech", environment);
        ITApplication itapplicationb = new ITApplication("test", "tech", environment);

        // When
        boolean validator = itapplication.equals(itapplicationb);

        // Then
        assertTrue(validator);
    }
}
