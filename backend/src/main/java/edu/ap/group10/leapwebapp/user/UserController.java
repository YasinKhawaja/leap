package edu.ap.group10.leapwebapp.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.security.SecurityConstraints;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

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
            User user = new User(firstname, surname, email, username, password, 1, userService.validateToken(confirmationToken), null);
            return userService.addUser(user);

        } catch (Exception e){
            log.error("Exception", e);
            return null;
        }
    }

    @PostMapping("/user")
    public User addUser(@RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
    @RequestParam("email") String email, @RequestParam("company") Long company, @RequestParam("username") String username,
    @RequestParam("environment") Long environment, @RequestParam("role") Integer role){
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
            User user = new User(firstName, surname, email, username, password, role, userService.findCompany(company), userService.findEnvironment(environment));
            
            userService.sendMail(email, username, password);
            
            return userService.addUser(user);

        } catch (Exception e){
            log.error("Exception", e);
            return null;
        }        
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
    public String jwt(@RequestParam String token){
        return userService.refreshJwt(token);
    }
}
