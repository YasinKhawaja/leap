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
public class UserDTOUnitTests {

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    private static final String firstName = "Sander";
    private static final String surname = "Standaert";
    private static final String email = "Standaertsander@gmail.com";
    private static final String username = "Sander";
    private static final String password = "17818532d7efeeeb76cd4e8a05bc89964b7f50e75ced67c9c8bf16047451188c";
    private static final int role = -1;

    @Test
    void givenUserDTOParams_whenUserDTOToString_returnsUserDTOToString() {
        // Given
        UserDTO user = new UserDTO();
        user.setFirstname(firstName);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setCompany(company);

        // When
        String toString = "UserDTO(id=null, username=Sander, firstname=Sander, surname=Standaert, email=Standaertsander@gmail.com, password=17818532d7efeeeb76cd4e8a05bc89964b7f50e75ced67c9c8bf16047451188c, role=-1, company=Company(id=null, vatNumber=1, companyName=Test Company, email=sv@gmail.com, streetName=kerkstraat, houseNumber=3, postcode=5, city=Mortsel, country=België, businessActivity=null, taxOffice=null, approved=false, environments=null))";

        String actualString = user.toString();

        // Then
        assertEquals(toString, actualString);

    }

    @Test
    void givenUsers_whenHashCode_returnsTrue() {
        // Given
        UserDTO userAdmin1 = new UserDTO();
        userAdmin1.setFirstname(firstName);
        userAdmin1.setSurname(surname);
        userAdmin1.setEmail(email);
        userAdmin1.setUsername(username);
        userAdmin1.setPassword(password);
        userAdmin1.setRole(0);
        userAdmin1.setCompany(company);
        UserDTO userAdmin2 = new UserDTO();
        userAdmin2.setFirstname(firstName);
        userAdmin2.setSurname(surname);
        userAdmin2.setEmail(email);
        userAdmin2.setUsername(username);
        userAdmin2.setPassword(password);
        userAdmin2.setRole(0);
        userAdmin2.setCompany(company);
        UserDTO editor = new UserDTO();
        editor.setFirstname(firstName);
        editor.setSurname(surname);
        editor.setEmail(email);
        editor.setUsername(username);
        editor.setPassword(password);
        editor.setRole(1);
        editor.setCompany(company);
        UserDTO applicationAdmin = new UserDTO();
        applicationAdmin.setFirstname(firstName);
        applicationAdmin.setSurname(surname);
        applicationAdmin.setEmail(email);
        applicationAdmin.setUsername(username);
        applicationAdmin.setPassword(password);
        applicationAdmin.setRole(-1);
        applicationAdmin.setCompany(company);
        UserDTO reader = new UserDTO();
        reader.setFirstname(firstName);
        reader.setSurname(surname);
        reader.setEmail(email);
        reader.setUsername(username);
        reader.setPassword(password);
        reader.setRole(2);
        reader.setCompany(company);

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
        UserDTO userAdmin1 = new UserDTO();
        userAdmin1.setFirstname(firstName);
        userAdmin1.setSurname(surname);
        userAdmin1.setEmail(email);
        userAdmin1.setUsername(username);
        userAdmin1.setPassword(password);
        userAdmin1.setRole(0);
        userAdmin1.setCompany(company);
        UserDTO userAdmin2 = new UserDTO();
        userAdmin2.setFirstname(firstName);
        userAdmin2.setSurname(surname);
        userAdmin2.setEmail(email);
        userAdmin2.setUsername(username);
        userAdmin2.setPassword(password);
        userAdmin2.setRole(0);
        userAdmin2.setCompany(company);
        UserDTO editor = new UserDTO();
        editor.setFirstname(firstName);
        editor.setSurname(surname);
        editor.setEmail(email);
        editor.setUsername(username);
        editor.setPassword(password);
        editor.setRole(1);
        editor.setCompany(company);
        UserDTO applicationAdmin = new UserDTO();
        applicationAdmin.setFirstname(firstName);
        applicationAdmin.setSurname(surname);
        applicationAdmin.setEmail(email);
        applicationAdmin.setUsername(username);
        applicationAdmin.setPassword(password);
        applicationAdmin.setRole(-1);
        applicationAdmin.setCompany(company);
        UserDTO reader = new UserDTO();
        reader.setFirstname(firstName);
        reader.setSurname(surname);
        reader.setEmail(email);
        reader.setUsername(username);
        reader.setPassword(password);
        reader.setRole(2);
        reader.setCompany(company);

        // When
        boolean validator = userAdmin1.equals(userAdmin2) && editor != userAdmin1 && applicationAdmin != userAdmin1
                && reader != userAdmin1;

        // Then
        assertTrue(validator);
    }
}
