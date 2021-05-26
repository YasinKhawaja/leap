package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EnvironmentRepositoryTest {

    @Autowired
    private EnvironmentRepository sut;

    @AfterEach
    void tearDown(){
        sut.deleteAll();
    }

    @Test
    void givenEnvironment_whenExistsByName_returnsExistsByName(){
        //Arrange
        String name = "Siemens";
        Environment environment = new Environment(name);
        sut.save(environment);

        //Act
        boolean expected = sut.existsByName(name);

        //Assert
        assertEquals(expected, true);
    }

    @Test
    void givenEnvironment_whenExistsByName_returnsDoesNotExistByName(){
        //Arrange
        String name = "Siemens";

        //Act
        boolean expected = sut.existsByName(name);

        //Assert
        assertEquals(expected, false);
    }
}
