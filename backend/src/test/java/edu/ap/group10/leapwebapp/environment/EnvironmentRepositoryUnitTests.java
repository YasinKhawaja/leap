
package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EnvironmentRepositoryUnitTests {

    @Autowired
    private EnvironmentRepository eRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        eRepositoryUnderTest.deleteAll();
    }

    @Test
    void findByName_EnvironmentInDb_ReturnEnvironment() {
        // Arrange
        Environment environment = new Environment("EnvironmentTest");
        eRepositoryUnderTest.save(environment);

        Environment expected = environment;

        // Act
        Environment actual = eRepositoryUnderTest.findByName(environment.getName());

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void findByName_EnvironmentNotInDb_ReturnNull() {
        // Arrange
        String environmentName = "EnvironmentTest";

        Environment expected = null;

        // Act
        Environment actual = eRepositoryUnderTest.findByName(environmentName);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByName_EnvironmentInDb_ReturnTrue() {
        // Arrange
        Environment environment = new Environment("EnvironmentTest");
        eRepositoryUnderTest.save(environment);

        Boolean expected = true;

        // Act
        Boolean actual = eRepositoryUnderTest.existsByName(environment.getName());

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void existsByName_EnvironmentNotInDb_ReturnFalse() {
        // Arrange
        String environmentName = "EnvironmentTest";

        Boolean expected = false;

        // Act
        Boolean actual = eRepositoryUnderTest.existsByName(environmentName);

        // Assert
        assertEquals(expected, actual);
    }
}
