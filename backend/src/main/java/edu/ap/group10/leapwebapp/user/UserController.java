package edu.ap.group10.leapwebapp.user;

import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityExistsException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenService;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.SecurityConstraints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/application-admin")
    public User addApplicationAdmin(@RequestParam("firstName") String firstname,
            @RequestParam("surname") String surname, @RequestParam("email") String email,
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("secret") String secret) {
        try {
            if (secret.equals(SecurityConstraints.APPLICATION_ADMIN_SECRET)) {

                Boolean validateUsername = userService.findUsername(username);
                Boolean validateEmail = userService.findUserEmail(email);

                if (validateUsername != null && validateUsername) {
                    throw new UnsupportedOperationException("User already exists");
                } else if (validateEmail != null && validateEmail) {
                    throw new UnsupportedOperationException("Email is in use");
                } else {
                    User user = new User(firstname, surname, email, username, password, -1, null);
                    return userService.addUser(user);
                }
            } else {
                throw new AccessDeniedException("Wrong secret");
            }

        } catch (Exception e) {
            log.error("Exception", e);
            throw new EntityExistsException(e);
        }
    }

    @PostMapping("/useradmin")
    public User addUserAdmin(@RequestParam("firstName") String firstname, @RequestParam("surname") String surname,
            @RequestParam("email") String email, @RequestParam("username") String username,
            @RequestParam("password") String password, @RequestParam("token") String confirmationToken) {
        try {
            Boolean validateUsername = userService.findUsername(username);
            Boolean validateEmail = userService.findUserEmail(email);

            if (validateUsername != null && validateUsername) {
                throw new UnsupportedOperationException("User already exists");
            } else if (validateEmail != null && validateEmail) {
                throw new UnsupportedOperationException("Email is in use");
            } else {
                User user = new User(firstname, surname, email, username, password, 0,
                        userService.validateToken(confirmationToken));
                confirmationTokenService.deleteConfirmationToken(confirmationToken);
                return userService.addUser(user);
            }

        } catch (Exception e) {
            log.error("Exception", e);
            throw new EntityExistsException(e);
        }
    }

    @PostMapping("/user")
    public void addUser(@RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
            @RequestParam("email") String email, @RequestParam("companyId") String company,
            @RequestParam("username") String username, @RequestParam("role") Integer role) {
        try {
            Boolean validateUsername = userService.findUsername(username);
            Boolean validateEmail = userService.findUserEmail(email);

            if (validateUsername != null && validateUsername) {
                throw new UnsupportedOperationException("User already exists");
            } else if (validateEmail != null && validateEmail) {
                throw new UnsupportedOperationException("Email is in use");
            }

            byte[] bytes = new byte[10];
            new Random().nextBytes(bytes);
            String password = Base64.getEncoder().encodeToString(bytes);
            User user = new User(firstName, surname, email, username, password, role,
                    userService.findCompany(Long.parseLong(company)));

            userService.addUser(user);

            userService.sendMail(email, username, user.getId().toString());

        } catch (Exception e) {
            log.error("Exception", e);
            throw e;
        }
    }

    @GetMapping("/user")
    public List<User> getUsers(@RequestParam("companyId") String companyId) {
        return userService.findUserByCompany(Long.parseLong(companyId));
    }

    @DeleteMapping("/user")
    public void delUser(@RequestParam("userid") String userid) {
        userService.delUser(Long.parseLong(userid));
    }

    @PutMapping("/user")
    public User updateUser(@RequestParam("userid") String userid, @RequestParam("firstName") String firstName,
            @RequestParam("surname") String surname, @RequestParam("email") String email,
            @RequestParam("username") String username, @RequestParam("role") Integer role) {
        return userService.updateUser(userid,
                new User(firstName, surname, email, username, "", role, userService.findUserById(userid).getCompany()));
    }

    @GetMapping("/user/{userid}")
    public User getUser(@PathVariable String userid) {
        return userService.findUserById(userid);
    }

    @GetMapping("/user/login")
    public void trylogin(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        String auth = userService.authenticateUser(token);
        String value = Base64.getEncoder().withoutPadding().encodeToString(auth.getBytes());
        String name = Base64.getEncoder().withoutPadding().encodeToString(("jwt").getBytes());
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(900);
        response.addCookie(cookie);
    }

    @PostMapping("/user/jwt")
    public void jwt(@RequestParam String token, HttpServletResponse response) {
        String newToken = userService.refreshJwt(token);
        response.addHeader("Access-Control-Expose-Headers", SecurityConstraints.HEADER_STRING);
        response.addHeader("Access-Control-Allow-Headers", SecurityConstraints.HEADER_STRING);
        response.setHeader("Authorization", newToken);
    }

    @PostMapping("/user/resetpassword")
    public void requestResetPassword(@RequestParam String email) {
        User user = userService.findUserByMail(email);

        String id = userService.encodeId(user.getId().toString());

        Mail mail = new Mail();
        mail.setSender("leapwebapp@gmail.com");
        mail.setReceiver(user.getEmail());
        mail.setSubject("Password change");
        mail.setContent(
                "To reset your password click on the following link. \nhttp://localhost:4200/resetpassword/confirm?id="
                        + id);
        mailService.sendMail(mail);
    }

    @PutMapping("/user/resetpassword")
    public void resetPassword(@RequestParam String token, @RequestParam String password) {

        userService.changePassword(Long.parseLong(userService.getUserIDJwt(token)), password);
    }
}
