package edu.ap.group10.leapwebapp.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class UserUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    private static final String firstName = "Sander";
    private static final String surname = "Standaert";
    private static final String email = "Standaertsander@gmail.com";
    private static final String username = "Sander";
    private static final String password = "17818532d7efeeeb76cd4e8a05bc89964b7f50e75ced67c9c8bf16047451188c";
    private static final int role = -1;

    @Test
    void givenUserParams_whenUserToString_returnsUserToString() {
        // Given
        User user = new User(firstName, surname, email, username, password, role, company);

        // When
        String toString = "User(id=null, username=" + username + ", firstName=" + firstName + ", surname=" + surname
                + ", email=" + email + ", password=" + user.getPassword() + ", role=" + role + ", company="
                + company.toString() + ")";

        // Then
        assertEquals(toString, user.toString());

    }

    @Test
    void givenUsers_whenHashCode_returnsTrue() {
        // Given
        User user1 = new User(firstName, surname, email, username, "", role, company);
        User user2 = new User(firstName, surname, email, username, "", role, company);

        // When
        int user1Hash = user1.hashCode();
        int user2Hash = user2.hashCode();

        // Then
        assertEquals(user1Hash, user2Hash);
    }

    @Test
    void givenUsers_whenEquals_returnsTrue() {
        // Given
        User user1 = new User(firstName, surname, email, username, "", role, company);
        User user2 = new User(firstName, surname, email, username, "", role, company);

        // When
        boolean validator = user1.equals(user2);

        // Then
        assertTrue(validator);
    }
}
