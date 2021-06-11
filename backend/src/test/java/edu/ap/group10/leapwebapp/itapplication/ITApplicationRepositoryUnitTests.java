package edu.ap.group10.leapwebapp.itapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.user.UserRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class ITApplicationRepositoryUnitTests {

    @Autowired
    private ITApplicationRepository sut;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    private static final Company company = new Company("1", "Testing Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @BeforeAll
    void setup() {
        userRepository.deleteAll();
        companyRepository.deleteAll();
        environmentRepository.deleteAll();
        companyRepository.save(company);
        environmentRepository.save(environment);
    }

    @BeforeEach
    void delete() {
        sut.deleteAll();
    }

    @Test
    void givenITApplication_whenExistsByName_returnsITApplication() {
        // Given
        ITApplication itapplication = new ITApplication("Test Application", "Mockito", environment);
        sut.save(itapplication);

        // When
        ITApplication actualApplication = sut.findByName(itapplication.getName());

        // Then
        assertEquals(itapplication.getId(), actualApplication.getId());
    }

    @Test
    void givenITApplication_whenExistsByName_returnsNULL() {
        // Given
        ITApplication itapplication = new ITApplication("Test Application", "Mockito", environment);
        sut.save(itapplication);

        // When
        ITApplication actualApplication = sut.findByName("bob");

        // Then
        assertEquals(null, actualApplication);
    }
}
