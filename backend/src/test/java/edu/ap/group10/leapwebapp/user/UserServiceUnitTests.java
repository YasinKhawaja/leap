package edu.ap.group10.leapwebapp.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.company.CompanyService;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.exceptions.RegisterException;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.CustomAuthenticationProvider;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private EnvironmentRepository environmentRepository;

    @Mock
    private User user;

    @Mock
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Mock
    private MailService mailService;

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
    void givenUsername_whenLoadUserByName_returnsUsernameNotFoundException() {
        // Given
        String username = "Bob";

        // When
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Then
        assertThrows(UsernameNotFoundException.class, () -> sut.loadUserByUsername(username));
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

    @Test
    void givenUserID_whenDelUser_returnsFalse() {
        // Given
        Long userid = 4L;
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);

        // When
        when(userRepository.findById(userid)).thenReturn(Optional.of(user));
        Boolean validator = sut.delUser(userid);

        // Then
        assertFalse(validator);
    }

    @Test
    void givenUser_UserID_whenUpdateUser_returnsUpdatedUser() {
        // Given
        Long userid = 4L;
        User oldUser = new User("firstName", "surname", "email", "username", "password", -1, company);
        oldUser.setId(userid);
        User newUser = new User("emaNtsrif", "emanrus", "liame", "emanresu", "drowssap", 3, company);

        // When
        when(userRepository.findById(userid)).thenReturn(Optional.of(oldUser));
        ArgumentCaptor<User> argCap = ArgumentCaptor.forClass(User.class);
        sut.updateUser(userid.toString(), newUser);

        // Then
        verify(userRepository).save(argCap.capture());
        assertEquals(oldUser.getId(), argCap.getValue().getId());
        assertEquals(newUser.getUsername(), argCap.getValue().getUsername());
    }

    @Test
    void givenUserID_Password_whenChangePassword_returnsTrue() {
        // Given
        Long userid = 4L;
        String password = "";
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);
        user.setId(userid);

        // When
        ArgumentCaptor<User> argCap = ArgumentCaptor.forClass(User.class);
        when(userRepository.findById(userid)).thenReturn(Optional.of(user));
        sut.changePassword(userid, password);

        // Then
        verify(userRepository).save(argCap.capture());
        assertEquals(password, argCap.getValue().getPassword());
    }

    @Test
    void givenUserID_Password_whenChangePassword_returnsResourceNotFoundException() {
        // Given
        Long userid = 40L;
        String password = "";

        // When
        when(userRepository.findById(userid)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> sut.changePassword(userid, password));
    }

    @Test
    void givenEmail_Username_whenCheckUser_returnsTrue() throws RegisterException {
        // Given
        String email = "standaertsander@gmail.com";
        String username = "Sander";

        // When
        when(userRepository.findByEmail(email)).thenReturn(null);
        when(userRepository.findByUsername(username)).thenReturn(null);
        boolean validator = sut.checkUserAdmin(email, username);

        // Then
        assertTrue(validator);
    }

    @Test
    void givenEmail_Username_whenCheckUser_ExistingUser_returnsEntityExistsException() {
        // Given
        String email = "standaertsander@gmail.com";
        String username = "Sander";
        User user = new User("firstName", "surname", email, username, "password", -1, company);

        // When
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Then
        assertThrows(RegisterException.class, () -> sut.checkUserAdmin(email, username));
    }

    @Test
    void givenEmail_Username_whenCheckUser_ExistingEmail_returnsEntityExistsException() {
        // Given
        String email = "standaertsander@gmail.com";
        String username = "Sander";
        User user = new User("firstName", "surname", email, username, "password", -1, company);

        // When
        when(userRepository.findByEmail(email)).thenReturn(user);

        // Then
        assertThrows(EntityExistsException.class, () -> sut.checkUserAdmin(email, username));
    }

    @Test
    void givenCompanyID_whenFindCompany_returnsCompany() {
        // Given
        company.setId(50L);
        Long companyid = company.getId();

        // When
        when(companyRepository.findById(companyid)).thenReturn(Optional.of(company));
        Company actualCompany = sut.findCompany(companyid);

        // Then
        assertEquals(company, actualCompany);
    }

    @Test
    void givenCompanyID_whenFindCompany_returnsResourceNotFoundException() {
        // Given
        Long companyid = 60L;

        // When
        when(companyRepository.findById(companyid)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> sut.findCompany(companyid));
    }

    @Test
    void givenCompanyID_whenFindUserByCompany_returnsUsers() {
        // Given
        company.setId(50L);
        Long companyid = company.getId();

        User user1 = new User("firstName", "surname", "email", "username", "password", -1, company);
        User user2 = new User("firstName", "surname", "email", "username", "password", -1, company);
        User user3 = new User("firstName", "surname", "email", "username", "password", -1, company);
        List<User> users = Arrays.asList(user1, user2, user3);

        // When
        when(companyService.findCompany(companyid)).thenReturn(company);
        when(userRepository.findByCompany(company)).thenReturn(users);
        List<User> actualUsers = sut.findUserByCompany(companyid);

        // Then
        assertEquals(users, actualUsers);
    }

    @Test
    void givenUsername_whenFindUserByUsername_returnsUser() {
        // Given
        String username = "Sander";
        User user = new User("firstName", "surname", "email", username, "password", -1, company);

        // When
        when(userRepository.findByUsername(username)).thenReturn(user);
        User actualUser = sut.findUserByUsername(username);

        // Then
        assertEquals(user, actualUser);
    }

    @Test
    void givenUserID_whenFindUserById_returnsUser() {
        // Given
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);
        user.setId(5L);
        String userid = user.getId().toString();

        // When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User actualUser = sut.findUserById(userid.toString());

        // Then
        assertEquals(user, actualUser);
    }

    @Test
    void givenAuthenticationToken_whenAuthenticateUser_returnsJWT() {
        // Given
        String jwt = "Success";
        String username = "User";
        String password = "Password";
        User user = new User("firstName", "surname", "email", "username", "password", -1, company);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                user.getAuthorities());

        // When
        when(customAuthenticationProvider.authenticate(token)).thenReturn(auth);

        when(customAuthenticationProvider.onAuthenticationSuccess(auth)).thenReturn("Success");
        String actualJWT = sut.authenticateUser(token);

        // Then
        assertEquals(jwt, actualJWT);
    }

    @Test
    void givenEnvironmentID_whenFindEnvironment_returnsEnvironment() {
        // Given
        Long environmentid = 5L;
        Environment environment = new Environment("name", company);
        environment.setId(environmentid);

        // When
        when(environmentRepository.findById(environmentid)).thenReturn(Optional.of(environment));
        Environment actualEnvironment = sut.findEnvironment(environmentid);

        // Then
        assertEquals(environment, actualEnvironment);
    }

    @Test
    void givenEnvironmentID_whenFindEnvironment_returnsResourceNotFoundException() {
        // Given
        Long environmentid = 5L;

        // When
        when(environmentRepository.findById(environmentid)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> sut.findEnvironment(environmentid));
    }

    @Test
    void givenEmail_Username_UserID_whenSendMail_returnsMail() {
        // Given
        String email = "email";
        String username = "username";
        String userid = "1";

        // When
        String newPasswordLink = "http://localhost:4200/resetpassword/confirm?id=OK";
        Mail mail = new Mail();
        mail.setReceiver(email);
        mail.setSubject("Your first login credentials.");
        mail.setContent("Your username is: " + username + "\nTo active your account, set a password using this link: "
                + newPasswordLink
                + "\nThis link is only valid for 1h, after 1h please reset your password using the password reset option on the site.");

        when(sut.encodeId(userid)).thenReturn("OK");
        sut.sendMail(email, username, userid);

        // Then
        ArgumentCaptor<Mail> argCap = ArgumentCaptor.forClass(Mail.class);
        verify(mailService).sendMail(argCap.capture());
        Mail actualMail = argCap.getValue();

        assertEquals(mail, actualMail);
    }

    @Test
    void givenUserID_whenEncodeID_returnsJWT() {
        // Given
        String userid = "5";
        String jwt = "OK";

        // When
        when(customAuthenticationProvider.newUserIdJwt(userid)).thenReturn("OK");
        String actualJWT = sut.encodeId(userid);

        // Then
        assertEquals(jwt, actualJWT);
    }

    @Test
    void givenJWT_whenGetUserIDJwt_returnsUserID() {
        // Given
        String userid = "5";
        String jwt = "OK";

        // When
        when(customAuthenticationProvider.checkUserID(jwt)).thenReturn(userid);
        String actualUserid = sut.getUserIDJwt(jwt);

        // Then
        assertEquals(userid, actualUserid);
    }

    @Test
    void givenJWT_whenRefreshJWT_returnsJWT() {
        // Given
        String jwt = "OK";
        String newJWT = "NEW";
        String username = "test";

        // When
        when(customAuthenticationProvider.newJwt(jwt, username)).thenReturn(newJWT);
        String actualNewJWT = sut.refreshJwt(jwt, username);

        // Then
        assertEquals(newJWT, actualNewJWT);
    }

    @Test
    void givenEmail_whenFindUserByMail_returnsUser() {
        // Given
        String email = "email";
        User user = new User("firstName", "surname", email, "username", "password", -1, company);

        // When
        when(userRepository.findByEmail(email)).thenReturn(user);
        User actualUser = sut.findUserByMail(email);

        // Then
        assertEquals(user, actualUser);
    }
}
