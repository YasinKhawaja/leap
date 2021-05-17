package edu.ap.group10.leapwebapp.capability;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@DataJpaTest
public class CapabilityRepositoryUnitTests {

    @Autowired
    CapabilityRepository cRepositoryUnderTest;
    
    @Autowired
    EnvironmentRepository eRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        // Fresh H2 DB before each test
        cRepositoryUnderTest.deleteAll();
    }

    @Test
    void findByEnv() {

        // Given
        Environment env = new Environment("EnvironmentTest");
        eRepositoryUnderTest.save(env);


        Capability expectedCaToReturn = new Capability("jan",PaceOfChange.DIFFERENTIATION,Tom.COORDINATION,2);
        expectedCaToReturn.setEnvironment(env);
        cRepositoryUnderTest.save(expectedCaToReturn);


        // When
        List<Capability> actualCaReturned = cRepositoryUnderTest.findByEnvironment(env);

        // Then
        assertEquals(expectedCaToReturn, actualCaReturned.get(0));
    }
    
}
