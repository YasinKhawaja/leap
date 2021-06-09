package edu.ap.group10.leapwebapp.user;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenService;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.SecurityConstraints;

@RestController
public class UserController {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @ExceptionHandler(Exception.class)
    @PostMapping("/application-admin")
    public void addApplicationAdmin(@RequestBody User user, @RequestParam String secret) {
        if (secret.equals(SecurityConstraints.APPLICATION_ADMIN_SECRET)
                && userService.checkUser(user.getEmail(), user.getUsername())) {
            user.setRole(-1);
            user.setCompany(null);
            userService.addUser(user);
        }
    }

    @ExceptionHandler(Exception.class)
    @PostMapping("/useradmin")
    public void addUserAdmin(@RequestParam String token, @RequestBody User user) {
        if (userService.checkUser(user.getEmail(), user.getUsername())) {
            user.setRole(0);
            user.setCompany(userService.validateToken(token));
            confirmationTokenService.deleteConfirmationToken(token);
            userService.addUser(user);
        }
    }

    @ExceptionHandler(Exception.class)
    @PostMapping("/user")
    public void addUser(@RequestBody User user, @RequestParam String company, @RequestParam Integer role) {
        if (userService.checkUser(user.getEmail(), user.getUsername())) {
            byte[] bytes = new byte[10];
            new Random().nextBytes(bytes);
            String password = Base64.getEncoder().encodeToString(bytes);
            user.setRole(role);
            user.setPassword(password);
            user.setCompany(userService.findCompany(Long.parseLong(company)));

            userService.addUser(user);

            userService.sendMail(user.getEmail(), user.getUsername(), user.getId().toString());
        }
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/user")
    public List<User> getUsers(@RequestParam("companyId") String companyId) {
        return userService.findUserByCompany(Long.parseLong(companyId));
    }

    @ExceptionHandler(Exception.class)
    @DeleteMapping("/user")
    public void delUser(@RequestParam("userid") String userid) {
        userService.delUser(Long.parseLong(userid));
    }

    @ExceptionHandler(Exception.class)
    @PutMapping("/user")
    public void updateUser(@RequestParam String userid, @RequestBody User user, @RequestParam Integer role) {
        user.setRole(role);
        user.setCompany(userService.findUserById(userid).getCompany());
        userService.updateUser(userid, user);
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/user/{userid}")
    public User getUser(@PathVariable String userid) {
        return userService.findUserById(userid);
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/user/login")
    public void trylogin(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletResponse response) {
        String value = Base64.getEncoder().withoutPadding().encodeToString(
                userService.authenticateUser(new UsernamePasswordAuthenticationToken(username, password)).getBytes());
        String name = Base64.getEncoder().withoutPadding().encodeToString(("jwt").getBytes());
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(900);
        response.addCookie(cookie);
    }

    @ExceptionHandler(Exception.class)
    @PostMapping("/user/jwt")
    public void jwt(@RequestParam String token, HttpServletResponse response) {
        String newToken = userService.refreshJwt(token);
        response.addHeader("Access-Control-Expose-Headers", SecurityConstraints.HEADER_STRING);
        response.addHeader("Access-Control-Allow-Headers", SecurityConstraints.HEADER_STRING);
        response.setHeader("Authorization", newToken);
    }

    @ExceptionHandler(Exception.class)
    @PostMapping("/user/resetpassword")
    public void requestResetPassword(@RequestParam String email) {
        User user = userService.findUserByMail(email);

        String id = userService.encodeId(user.getId().toString());

        Mail mail = new Mail();
        mail.setReceiver(user.getEmail());
        mail.setSubject("Password change");
        mail.setContent(
                "To reset your password click on the following link. \nhttp://localhost:4200/resetpassword/confirm?id="
                        + id);
        mailService.sendMail(mail);
    }

    @ExceptionHandler(Exception.class)
    @PutMapping("/user/resetpassword")
    public void resetPassword(@RequestParam String token, @RequestParam String password) {

        userService.changePassword(Long.parseLong(userService.getUserIDJwt(token)), password);
    }
}
