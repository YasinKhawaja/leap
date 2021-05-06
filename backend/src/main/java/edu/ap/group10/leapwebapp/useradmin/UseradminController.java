package edu.ap.group10.leapwebapp.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.user.UserLeap;
import edu.ap.group10.leapwebapp.user.UserRepository;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UseradminController {

  @Autowired 
  private UseradminRepository useradminRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  @PostMapping("/useradmin/register") // Map ONLY POST Requests
  public @ResponseBody String addNewUser(@RequestParam("firstName") String firstName
      , @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password,
      @RequestParam("token")String confirmationToken) {

    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    Long companyID = token.getCompany().getId();

    if(token != null){
      UserLeap validateUser = userRepository.findByUsername(username);
      Useradmin validateAdmin = useradminRepository.findByUsername(username);

      if(validateUser != null || validateAdmin != null){
          return "Username is already in use! " + username;
      }
      else if(userRepository.findByEmail(email) != null || useradminRepository.findByEmail(email) != null){
          return "Email is already in use! " + email;
      }
      Useradmin n = new Useradmin(firstName, surname, email, username, password, companyID);
      useradminRepository.save(n);
      return "Saved";
    }
    else{
      return "You do not have access to create an user admin.\nPlease use the link from your email, if you did not get one then register your company first and wait for the application to be accepted.";
    }
  }
}