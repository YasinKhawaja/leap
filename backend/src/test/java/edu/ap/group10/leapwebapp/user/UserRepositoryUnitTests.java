package edu.ap.group10.leapwebapp.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

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

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryUnitTests {

    @Autowired
    private UserRepository sut;

    @Autowired
    private CompanyRepository companyRepository;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    private User user;

    @BeforeAll
    void setup() {
        companyRepository.save(company);
    }

    @BeforeEach
    void deleteUsers() {
        sut.deleteAll();
        user = new User("Sander", "Standaert", "standaertsander@gmail.com", "username", "password", -1, company);
    }

    @Test
    void givenUser_whenFindByUsername_returnsUser() {
        // Given
        sut.save(user);

        // When
        User actualUser = sut.findByUsername("username");

        // Then
        assertEquals(user.getId(), actualUser.getId());
    }

    @Test
    void givenUser_whenFindByEmail_returnsUser() {
        // Given
        sut.save(user);

        // When
        User actualUser = sut.findByEmail("standaertsander@gmail.com");

        // Then
        assertEquals(user.getId(), actualUser.getId());
    }

    @Test
    void givenCompany_whenFindByCompany_returnsUsers() {
        // Given
        User user2 = new User("Sander", "Standaert", "standaertsander2@gmail.com", "username2", "password", -1,
                company);
        User user3 = new User("Sander", "Standaert", "standaertsander3@gmail.com", "username3", "password", -1,
                company);
        sut.save(user);
        sut.save(user2);
        sut.save(user3);
        List<User> expectedUsers = Arrays.asList(user, user2, user3);

        // When
        List<User> actualUsers = sut.findByCompany(company);

        // Then
        for (var i = 0; i < actualUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).getId(), actualUsers.get(i).getId());
        }
    }
}
