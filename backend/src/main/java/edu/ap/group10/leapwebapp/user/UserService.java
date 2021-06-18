package edu.ap.group10.leapwebapp.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.company.CompanyService;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.CustomAuthenticationProvider;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public String getApplicationAdmins() {
        List<String> emails = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getRole().equals(-1)) {
                emails.add(user.getEmail());
            }
        }
        String emailsToString = emails.toString();
        emailsToString = emailsToString.substring(1, emailsToString.length() - 1);
        return emailsToString;
    }

    public Company validateToken(String token) {
        return confirmationTokenRepository.findByToken(token).getCompany();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public boolean delUser(Long userid) {
        Boolean validator = false;
        userRepository.deleteById(userid);
        if (!userRepository.findById(userid).isPresent()) {
            validator = true;
        }
        return validator;
    }

    public User updateUser(String userid, User user) {
        User oUser = userRepository.findById(Long.parseLong(userid)).orElseThrow(ResourceNotFoundException::new);
        oUser.setEmail(user.getEmail());
        oUser.setFirstName(user.getFirstName());
        oUser.setSurname(user.getSurname());
        oUser.setUsername(user.getUsername());
        oUser.setRole(user.getRole());

        return userRepository.save(oUser);
    }

    public void changePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        user.setPassword(password);
        userRepository.save(user);
    }

    public boolean checkUser(String email, String username) {
        if (userRepository.findByEmail(email) != null || userRepository.findByUsername(username) != null) {
            throw new EntityExistsException("User already exists");
        }
        return true;
    }

    public Company findCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<User> findUserByCompany(Long id) {
        return userRepository.findByCompany(companyService.findCompany(id));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(String userid) {
        return userRepository.findById(Long.parseLong(userid)).orElseThrow(ResourceNotFoundException::new);
    }

    public String authenticateUser(UsernamePasswordAuthenticationToken token) {
        Authentication auth = customAuthenticationProvider.authenticate(token);
        return customAuthenticationProvider.onAuthenticationSuccess(auth);
    }

    public Environment findEnvironment(Long environment) {
        return environmentRepository.findById(environment).orElseThrow(ResourceNotFoundException::new);
    }

    public void sendMail(String email, String username, String userid) {
        String newPasswordLink = "http://localhost:4200/resetpassword/confirm?id=" + this.encodeId(userid);

        Mail mail = new Mail();
        mail.setReceiver(email);
        mail.setSubject("Your first login credentials.");
        mail.setContent("Your username is: " + username + "\nTo active your account, set a password using this link: "
                + newPasswordLink
                + "\nThis link is only valid for 1h, after 1h please reset your password using the password reset option on the site.");
        mailService.sendMail(mail);
    }

    public String encodeId(String id) {
        return customAuthenticationProvider.newUserIdJwt(id);
    }

    public String getUserIDJwt(String token) {
        return customAuthenticationProvider.checkUserID(token);
    }

    public String refreshJwt(String token, String username) {
        return customAuthenticationProvider.newJwt(token, username);
    }

    public User findUserByMail(String email) {
        return userRepository.findByEmail(email);
    }
}
