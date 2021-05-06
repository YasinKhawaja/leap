package edu.ap.group10.leapwebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.user.UserRepository;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;
import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import edu.ap.group10.leapwebapp.user.UserLeap;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    private UseradminRepository useradminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/user/login")
    public @ResponseBody String loginResult(){
        return "logged in!";
      }

      @GetMapping("/user/login-error")
      public @ResponseBody String failureResult(){
          return "failed to log in";
        }

        @GetMapping("/user/access-denied")
        public @ResponseBody String accessDenied(){
            return "Access Denied!";
          }

    @PostMapping("/user/register")
    public @ResponseBody String register(@RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
    @RequestParam("email") String email, @RequestParam("company") Long company, @RequestParam("username") String username,
    @RequestParam("environment") String environment){

        String password = null;

        UserLeap validateUser = userRepository.findByUsername(username);
        Useradmin validateAdmin = useradminRepository.findByUsername(username);

        if(validateUser != null || validateAdmin != null){
            return "Username is already in use! " + username;
        }
        else if(userRepository.findByEmail(email) != null || useradminRepository.findByEmail(email) != null){
            return "Email is already in use! " + email;
        }

        UserLeap u = new UserLeap(firstName, surname, email, username, password, company, environment);
        userRepository.save(u);
        return "Saved: " + u.getPassword();
    }
}
