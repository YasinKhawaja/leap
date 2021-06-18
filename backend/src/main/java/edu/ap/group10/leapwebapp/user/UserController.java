package edu.ap.group10.leapwebapp.user;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private ModelMapper modelMapper;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/application-admin")
    public void addApplicationAdmin(@RequestBody UserDTO userDTO, @RequestParam String secret) {
        User user = modelMapper.map(userDTO, User.class);
        if (secret.equals(SecurityConstraints.APPLICATION_ADMIN_SECRET)
                && userService.checkUser(user.getEmail(), user.getUsername())) {
            user.setRole(-1);
            user.setCompany(null);
            userService.addUser(user);
        }
    }

    @PostMapping("/useradmin")
    public void addUserAdmin(@RequestParam String token, @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        if (userService.checkUser(user.getEmail(), user.getUsername())) {
            user.setRole(0);
            user.setCompany(userService.validateToken(token));
            confirmationTokenService.deleteConfirmationToken(token);
            userService.addUser(user);
        }
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserDTO userDTO, @RequestParam String company, @RequestParam Integer role) {
        if (!role.equals(-1) && !role.equals(0)) {
            User user = modelMapper.map(userDTO, User.class);
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
    }

    @GetMapping("/user")
    public List<UserDTO> getUsers(@RequestParam("companyId") String companyId) {
        List<User> users = userService.findUserByCompany(Long.parseLong(companyId));
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(modelMapper.map(user, UserDTO.class));
        }
        return userDTOs;
    }

    @DeleteMapping("/user")
    public void delUser(@RequestParam("userid") String userid) {
        userService.delUser(Long.parseLong(userid));
    }

    @PutMapping("/user")
    public void updateUser(@RequestParam String userid, @RequestBody UserDTO userDTO, @RequestParam Integer role) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(role);
        user.setCompany(userService.findUserById(userid).getCompany());
        userService.updateUser(userid, user);
    }

    @GetMapping("/user/{userid}")
    public UserDTO getUser(@PathVariable String userid) {
        User user = userService.findUserById(userid);
        return modelMapper.map(user, UserDTO.class);
    }

    @GetMapping("/user/login")
    public void trylogin(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletResponse response) {
        String value = Base64.getEncoder().withoutPadding().encodeToString(
                userService.authenticateUser(new UsernamePasswordAuthenticationToken(username, password)).getBytes());
        String name = Base64.getEncoder().withoutPadding().encodeToString(("jwt").getBytes());
        ResponseCookie cookie = ResponseCookie.from(name, value).httpOnly(false).secure(false).domain("localhost")
                .path("/").maxAge(Duration.ofMinutes(15)).sameSite("Strict").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @PostMapping("/user/jwt")
    public void jwt(@RequestParam String token, @RequestParam String username, HttpServletResponse response) {
        String newToken = userService.refreshJwt(token, username);
        response.addHeader("Access-Control-Expose-Headers", SecurityConstraints.HEADER_STRING);
        response.addHeader("Access-Control-Allow-Headers", SecurityConstraints.HEADER_STRING);
        String name = Base64.getEncoder().withoutPadding().encodeToString(("jwt").getBytes());
        newToken = Base64.getEncoder().withoutPadding().encodeToString(newToken.getBytes());
        ResponseCookie cookie = ResponseCookie.from(name, newToken).httpOnly(false).secure(false).domain("localhost")
                .path("/").maxAge(Duration.ofMinutes(15)).sameSite("Strict").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

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

    @PutMapping("/user/resetpassword")
    public void resetPassword(@RequestParam String token, @RequestParam String password) {

        userService.changePassword(Long.parseLong(userService.getUserIDJwt(token)), password);
    }
}
