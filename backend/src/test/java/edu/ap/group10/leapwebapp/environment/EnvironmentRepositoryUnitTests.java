
package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EnvironmentRepositoryUnitTests {

    @Autowired
    EnvironmentRepository eRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        // Fresh H2 DB before each test
        eRepositoryUnderTest.deleteAll();
    }

    @Test
    void findByName_EnvInDb_ReturnEnv() {
        // Given
        Environment expectedEnvToReturn = new Environment("EnvironmentTest");
        eRepositoryUnderTest.save(expectedEnvToReturn);

        // When
        Environment actualEnvReturned = eRepositoryUnderTest.findByName("EnvironmentTest");

        // Then
        assertEquals(expectedEnvToReturn, actualEnvReturned);
    }

    @Test
    void findByName_EnvNotInDb_ReturnNull() {
        // Given
        // No environment saved in DB

        // When
        Environment actualEnvReturned = eRepositoryUnderTest.findByName("EnvironmentTest");

        // Then
        assertEquals(null, actualEnvReturned);
    }

}
