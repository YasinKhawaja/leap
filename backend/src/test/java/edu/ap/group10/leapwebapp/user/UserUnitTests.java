package edu.ap.group10.leapwebapp.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;

@SpringBootTest
@AutoConfigureTestDatabase
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
        User userAdmin1 = new User(firstName, surname, email, username, "", 0, company);
        User userAdmin2 = new User(firstName, surname, email, username, "", 0, company);
        User editor = new User(firstName, surname, email, username, "", 1, company);
        User applicationAdmin = new User(firstName, surname, email, username, "", -1, company);
        User reader = new User(firstName, surname, email, username, "", 2, company);

        // When
        int userA1Hash = userAdmin1.hashCode();
        int userA2Hash = userAdmin2.hashCode();
        int userE3Hash = editor.hashCode();
        int userAA4Hash = applicationAdmin.hashCode();
        int userR5Hash = reader.hashCode();

        // Then
        assertEquals(userA1Hash, userA2Hash);
        assertNotEquals(userA1Hash, userE3Hash);
        assertNotEquals(userA1Hash, userAA4Hash);
        assertNotEquals(userE3Hash, userAA4Hash);
        assertNotEquals(userA1Hash, userR5Hash);
    }

    @Test
    void givenUsers_whenEquals_returnsTrue() {
        // Given
        User userAdmin1 = new User(firstName, surname, email, username, "", 0, company);
        User userAdmin2 = new User(firstName, surname, email, username, "", 0, company);
        User editor = new User(firstName, surname, email, username, "", 1, company);
        User applicationAdmin = new User(firstName, surname, email, username, "", -1, company);
        User reader3 = new User(firstName, surname, email, username, "", 2, company);

        // When
        boolean validator = userAdmin1.equals(userAdmin2)
                && userAdmin1.getAuthorities().equals(userAdmin2.getAuthorities())
                && userAdmin1.isAccountNonExpired() == (userAdmin2.isAccountNonExpired())
                && userAdmin1.isAccountNonLocked() == userAdmin2.isAccountNonLocked()
                && userAdmin1.isCredentialsNonExpired() == userAdmin2.isCredentialsNonExpired()
                && userAdmin1.isEnabled() == userAdmin2.isEnabled()
                && userAdmin1.getAuthorities() != editor.getAuthorities()
                && userAdmin1.getAuthorities() != (reader3.getAuthorities())
                && editor.getAuthorities() != reader3.getAuthorities()
                && editor.getAuthorities() != applicationAdmin.getAuthorities()
                && userAdmin1.getAuthorities() != applicationAdmin.getAuthorities();

        // Then
        assertTrue(validator);
    }
}
