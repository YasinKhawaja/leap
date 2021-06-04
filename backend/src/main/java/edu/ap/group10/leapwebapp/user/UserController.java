package edu.ap.group10.leapwebapp.user;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/useradmin")
    public User addUserAdmin(@RequestParam("firstName") String firstname, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password,
    @RequestParam("token")String confirmationToken) {
        try{
            Boolean validateUsername = userService.findUsername(username);
            Boolean validateEmail = userService.findUserEmail(email);

            if(validateUsername != null && validateUsername){
                throw new UnsupportedOperationException("User already exists");
            }
            else if (validateEmail != null && validateEmail){
                throw new UnsupportedOperationException("Email is in use");
            }
            User user = new User(firstname, surname, email, username, password, 0, userService.validateToken(confirmationToken));
            confirmationTokenService.deleteConfirmationToken(confirmationToken);
            return userService.addUser(user);

        } catch (Exception e){
            log.error("Exception", e);
            throw new EntityExistsException(e);
        }
    }

    @PostMapping("/user")
    public User addUser(@RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
    @RequestParam("email") String email, @RequestParam("companyId") String company, @RequestParam("username") String username,
    @RequestParam("role") Integer role){
        try{
            Boolean validateUsername = userService.findUsername(username);
            Boolean validateEmail = userService.findUserEmail(email);

            if(validateUsername != null && validateUsername){
                throw new UnsupportedOperationException("User already exists");
            }
            else if (validateEmail != null && validateEmail){
                throw new UnsupportedOperationException("Email is in use");
            }

            String password = userService.generatePassword();
            User user = new User(firstName, surname, email, username, password, role, userService.findCompany(Long.parseLong(company)));
            
            userService.sendMail(email, username, password);
            
            return userService.addUser(user);

        } catch (Exception e){
            log.error("Exception", e);
            return null;
        }        
    }

    @GetMapping("/user")
    public List<User> getUsers(@RequestParam("companyId") String companyId){
        return userService.findUserByCompany(Long.parseLong(companyId));
    }

    @DeleteMapping("/user")
    public void delUser(@RequestParam("userid") String userid){
        userService.delUser(Long.parseLong(userid));
    }

    @PutMapping("/user")
    public User updateUser(@RequestParam("userid") String userid, @RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
    @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("role") Integer role){
        return userService.updateUser(userid, new User(firstName, surname, email, username, "", role, userService.findUserById(userid).getCompany()));
    }

    @GetMapping("/user/{userid}")
    public User getUser(@PathVariable String userid){
        return userService.findUserById(userid);
    }

    @PostMapping("/user/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        response.addHeader("Access-Control-Expose-Headers", SecurityConstraints.HEADER_STRING);
        response.addHeader("Access-Control-Allow-Headers", SecurityConstraints.HEADER_STRING);
        response.setHeader("Authorization", SecurityConstraints.TOKEN_PREFIX + userService.authenticateUser(new UsernamePasswordAuthenticationToken(username, password)));
    }

    //throw failed login exception
    @GetMapping("/user/login")
    public String error(@RequestParam("token") Authentication auth, HttpServletResponse response){
        return "Failed to log in";
    }

    @PostMapping("/user/jwt")
    public void jwt(@RequestParam String token, HttpServletResponse response){
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
        mail.setContent("To reset your password click on the following link. \nhttp://localhost:4200/resetpassword/confirm?id=" + id);
        mailService.sendMail(mail);
    }

    @PutMapping("/user/resetpassword/{token}")
    public void resetPassword(@PathVariable String token, @RequestParam String password) {

        userService.changePassword(Long.parseLong(userService.getUserIDJwt(token)), password);
    }
}
