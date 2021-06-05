package edu.ap.group10.leapwebapp.environment;
/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class EnvironmentRepositoryTests {

    @Autowired
    private EnvironmentRepository sut; // system under test

    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }

    @Test
    void givenEnvironment_whenExistsByName_returnsTrue() {
        // Given
        String name = "Siemens";
        Environment environment = new Environment(name);
        sut.save(environment);

        //Act
        boolean actual = sut.existsByName(name);

        //Assert
        assertTrue(actual);
    }

    @Test
    void givenEnvironment_whenExistsByName_returnsDoesNotExistByName() {
        // Arrange
        String name = "Siemens";

        //Act
        boolean actual = sut.existsByName(name);

        //Assert
        assertFalse(actual);
    }

}
*/