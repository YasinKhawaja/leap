package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EnvironmentRepositoryTest {

    @Autowired
    private EnvironmentRepository environmentRepository;

    @AfterEach
    void tearDown(){
        environmentRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfEnvironmentExistsByName(){
        //Arrange
        String name = "Siemens";
        Environment environment = new Environment(name);
        environmentRepository.save(environment);

        //Act
        boolean expected = environmentRepository.existsByName(name);

        //Assert
        assertEquals(expected, true);
    }

    @Test
    void itShouldCheckIfEnvironmentDoesNotExistsByName(){
        //Arrange
        String name = "Siemens";

        //Act
        boolean expected = environmentRepository.existsByName(name);

        //Assert
        assertEquals(expected, false);
    }
}
