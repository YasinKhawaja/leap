package edu.ap.group10.leapwebapp.capability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
public class CapabilityUnitTests {
    
    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenCapability_whenToString_returnsCapabilityToString() {

        //given 
        Capability capability = new Capability("Test", environment);

        //when
        String toString = "Capability(id=null, level=1, name=Test, paceOfChange=NONE, targetOperationModel=NONE, resourcesQuality=0, informationQuality=0.0, applicationFit=0.0, subcapabilities=null, parent=null, environment=Environment(id=null, name=Test environment, capabilities=null, strategies=null, resources=null, itApplications=null, programs=null, businessProcesses=null, company=Company(id=null, vatNumber=1, companyName=Test Company, email=sv@gmail.com, streetName=kerkstraat, houseNumber=3, postcode=5, city=Mortsel, country=België, businessActivity=null, taxOffice=null, approved=false, environments=null)))";

        //then
        assertEquals(toString, capability.toString());
    }

   @Test
   void givenCapability_whenHashCode_returnsHashCode() {

    //given
    Capability capability1 = new Capability("Test", environment);
    Capability capability2 = new Capability("Test", environment);

    //when
    int a = capability1.hashCode();
    int b = capability2.hashCode();

    //then
    assertEquals(a, b);
   } 

   @Test
   void givenCapability_whenEquals_returnsTrue() {

    //given
    Capability capability1 = new Capability("Test", environment);
    Capability capability2 = new Capability("Test", environment);

    //when
    boolean validator = capability1.equals(capability2);

    //then
    assertTrue(validator);
   }
}
