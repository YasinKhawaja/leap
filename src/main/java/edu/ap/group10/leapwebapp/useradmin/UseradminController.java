package edu.ap.group10.leapwebapp.useradmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UseradminController {

  @Autowired 
  private UseradminRepository userRepository;

  //USERADMIN
  @GetMapping("/useradmin")
  public @ResponseBody Iterable<Useradmin> getAllUseradmins(){
    return userRepository.findAll();
  }

  @PostMapping("/useradmin/register") // Map ONLY POST Requests
  public @ResponseBody String addNewUser(@RequestParam("firstName") String firstName
      , @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {

    Useradmin n = new Useradmin(firstName, surname, email, username, password);
    userRepository.save(n);
    return "Saved";
  }
}