package edu.ap.group10.leapwebapp.capabilityapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.itapplication.ITApplication;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(Lifecycle.PER_CLASS)
public class CapabilityApplicationUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "HR", "?");
    private static final Environment environment = new Environment("Siemens", company);
    private static final Capability capability = new Capability("Youth", environment);
    private static final ITApplication itapplication = new ITApplication("test", "tech", environment);

    @BeforeAll
    void setup() {
        company.setId(1L);
        environment.setId(2L);
        capability.setId(3L);
        itapplication.setId(4L);
    }

    @Test
    void givenCapabilityApplication_whenToString_returnsCapabilityApplicationToString() {
        // Given
        CapabilityApplication capabilityApplication = new CapabilityApplication(0.0, 0, 0, 0, 0, 0, 0, 0, capability,
                itapplication);

        // When
        String toString = "CapabilityApplication(id=43, importance=0.0, businessEfficiencySupport=0, businessFunctionalCoverage=0, businessCorrectness=0, businessFuturePotential=0, informationCompleteness=0, informationCorrectness=0, informationAvailability=0, capability="
                + capability.toString() + ", itApplication=" + itapplication.toString() + ")";

        // Then
        assertEquals(capabilityApplication.toString(), toString);
    }

    @Test
    void givenCapabilityApplications_whenHashCode_returnsTrue() {
        // Given
        CapabilityApplication capabilityApplication1 = new CapabilityApplication(0.0, 0, 0, 0, 0, 0, 0, 0, capability,
                itapplication);
        CapabilityApplication capabilityApplication2 = new CapabilityApplication(0.0, 0, 0, 0, 0, 0, 0, 0, capability,
                itapplication);

        // When
        int capabilityApplication1hash = capabilityApplication1.hashCode();
        int capabilityApplication2hash = capabilityApplication2.hashCode();

        // Then
        assertEquals(capabilityApplication1hash, capabilityApplication2hash);
    }

    @Test
    void givenCapabilityApplications_whenEquals_returnsTrue() {
        // Given
        CapabilityApplication capabilityApplication1 = new CapabilityApplication(0.0, 0, 0, 0, 0, 0, 0, 0, capability,
                itapplication);
        CapabilityApplication capabilityApplication2 = new CapabilityApplication(0.0, 0, 0, 0, 0, 0, 0, 0, capability,
                itapplication);

        // When
        boolean validator = capabilityApplication1.equals(capabilityApplication2);

        // Then
        assertTrue(validator);
    }
}
