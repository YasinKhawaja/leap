package edu.ap.group10.leapwebapp.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @InjectMocks
    private UserService sut;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    @Test
    void givenUsername_whenLoadUserByName_returnsUserDetails() {
        // Given
        String username = "Sander";
        User user = new User("firstName", "surname", "email", username, "password", -1, company);

        // When
        when(userRepository.findByUsername(username)).thenReturn(user);
        UserDetails actualUser = sut.loadUserByUsername(username);

        // Then
        assertEquals(user.getPassword(), actualUser.getPassword());
        assertEquals(user.getUsername(), actualUser.getUsername());
    }

    @Test
    void givenConfirmationToken_whenFindByToken_returnsCompany() {
        // Given
        ConfirmationToken confirmationToken = new ConfirmationToken(company);

        // When
        when(confirmationTokenRepository.findByToken(confirmationToken.getToken())).thenReturn(confirmationToken);
        Company actualCompany = sut.validateToken(confirmationToken.getToken());

        // Then
        assertEquals(company, actualCompany);
    }

    @Test
    void givenUser_whenAddUser_returnsUser() {
        // Given
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);
        user.setId(4L);

        // When
        when(userRepository.save(user)).thenReturn(user);
        User actualUser = sut.addUser(user);

        // Then
        assertEquals(user, actualUser);
    }

    @Test
    void givenUserID_whenDelUser_returnsUserDeleted() {
        // Given
        Long userid = 4L;
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);
        user.setId(userid);

        // When
        when(userRepository.findById(userid)).thenReturn(Optional.empty());
        Boolean validator = sut.delUser(userid);

        // Then
        assertTrue(validator);
    }
}
