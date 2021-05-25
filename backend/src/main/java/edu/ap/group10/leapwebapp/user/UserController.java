package edu.ap.group10.leapwebapp.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.useradmin.Useradmin;
import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.CustomAuthenticationProvider;

@RestController
public class UserController {
   
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private UseradminRepository useradminRepository;

    @Autowired
    private CompanyRepository companyRepository;

    //Be sure to send password encrypted via angular, decrypt again here! TO DO FOR WEB SECURITY
    //Future web security JWT implementation: https://bezkoder.com/spring-boot-jwt-authentication/
    //change to /user
    @PostMapping("/user/login")
    public String loginResult(@RequestParam("username") String username, @RequestParam("password") String password){
        Authentication auth = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return auth.getAuthorities().toString();
    }
    //change to /user
    @PostMapping("/user/register")
    public String register(@RequestParam("firstName") String firstName, @RequestParam("surname") String surname,
    @RequestParam("email") String email, @RequestParam("company") Long company, @RequestParam("username") String username,
    @RequestParam("environment") String environment){

        Company c = companyRepository.findById(company).get();

        String password = null;

        UserLeap validateUser = userRepository.findByUsername(username);
        Useradmin validateAdmin = useradminRepository.findByUsername(username);

        if(validateUser != null || validateAdmin != null){
            return "Username is already in use! " + username;
        }
        else if(userRepository.findByEmail(email) != null || useradminRepository.findByEmail(email) != null){
            return "Email is already in use! " + email;
        }

        if(c != null){
            UserLeap u = new UserLeap(firstName, surname, email, username, password, c, environment);
            userRepository.save(u);

            Mail mail = new Mail();
            mail.setSender("leapwebapp@gmail.com");
            //add all application admins? maybe send mail to the role and get all email records that fit this role -> later implementation
            mail.setReceiver(email);
            mail.setSubject("Your first login credentials.");
            mail.setContent("Your username is: " + username + "\nAnd your automatically generated password is: " + password + "\nPlease change your password as soon as possible by going to this link: " + "Not implemented yet");
            mailService.sendMail(mail);
            return "Saved user.";   
        }
        else{
            return "Failed to save user."; 
        }
    }
}
