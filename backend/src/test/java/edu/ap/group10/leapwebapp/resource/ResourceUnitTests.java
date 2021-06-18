package edu.ap.group10.leapwebapp.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
class ResourceUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void GivenResource_WhenToString_ReturnsResourceToString() {
        // Given
        Resource resource = new Resource("name", "description", 0, environment);
        // When
        String toString = "Resource(id=null, name=name, description=description, fullTimeEquivalentYearlyValue=0, environment=Environment(id=null, name=Test environment, capabilities=null, strategies=null, resources=null, itApplications=null, programs=null, businessProcesses=null, company="
                + company.toString() + "))";
        // Then
        assertEquals(toString, resource.toString());
    }

    @Test
    void GivenResources_WhenHashCode_ReturnsTrue() {
        // Given
        Resource resource1 = new Resource("name", "description", 0, environment);
        Resource resource2 = new Resource("name", "description", 0, environment);
        // When
        int resource1Hash = resource1.hashCode();
        int resource2Hash = resource2.hashCode();
        // Then
        assertEquals(resource1Hash, resource2Hash);
    }

    @Test
    void GivenResources_WhenEquals_ReturnsTrue() {
        // Given
        Resource resource1 = new Resource("name", "description", 0, environment);
        Resource resource2 = new Resource("name", "description", 0, environment);
        // When
        boolean validator = resource1.equals(resource2);
        // Then
        assertTrue(validator);
    }
}
