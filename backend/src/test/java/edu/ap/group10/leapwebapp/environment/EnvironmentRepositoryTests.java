package edu.ap.group10.leapwebapp.environment;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class EnvironmentRepositoryTests {

    @Autowired
    private EnvironmentRepository sut; // system under test

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setup() {
        companyRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }

    @Test
    void givenEnvironment_whenExistsByName_returnsTrue() {
        // Given
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyRepository.save(company);
        String name = "Siemens";
        Environment environment = new Environment(name, company);
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
