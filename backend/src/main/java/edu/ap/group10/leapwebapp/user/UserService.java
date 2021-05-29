package edu.ap.group10.leapwebapp.user;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.CustomAuthenticationProvider;

public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }

    public Company validateToken(String token){
        return confirmationTokenRepository.findByConfirmationToken(token).getCompany();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
 
    public Boolean findUsername(String username){
        Boolean validate = false;
        if (userRepository.findByUsername(username) != null){
            validate = true;
        }
        return validate;
    }

    public Boolean findUserEmail(String email){
        Boolean validate = false;
        if (userRepository.findByEmail(email) != null){
            validate = true;
        }
        return validate;
    }

    public Company findCompany(Long id){
        return companyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public String generatePassword(){
        UserPasswordGenerator generatedpassword= new UserPasswordGenerator();
        return generatedpassword.generatePassayPassword();
    }

    public String authenticateUser(UsernamePasswordAuthenticationToken token) {
        Authentication auth = customAuthenticationProvider.authenticate(token);
        return customAuthenticationProvider.onAuthenticationSuccess(auth);
    }

    public Environment findEnvironment(Long environment) {
        return environmentRepository.findById(environment).orElseThrow(ResourceNotFoundException::new);
    }

    public void sendMail(String email, String username, String password) {
        Mail mail = new Mail();
        mail.setSender("leapwebapp@gmail.com");
        mail.setReceiver(email);
        mail.setSubject("Your first login credentials.");
        mail.setContent("Your username is: " + username + "\nAnd your automatically generated password is: " + password + "\nPlease change your password as soon as possible by going to this link: " + "Not implemented yet");
        mailService.sendMail(mail);
    }

    public String refreshJwt(String token){
        return customAuthenticationProvider.newJwt(token);
    }
}
