package edu.ap.group10.leapwebapp.user;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.useradmin.Useradmin;

@Entity // This makes a table out of this class
@DiscriminatorValue("bewerker")
public class UserLeap extends Useradmin{

    //Change default company and environment value, this is done for useradmin when they are created
    @Column(nullable = false, name = "user_environment")
    private String environment;

    public UserLeap(){}

  public UserLeap(String firstName, String surname, String email, String username, String password, Long company, String environment)
  {
      super(firstName, surname, email, username, password, company);
      this.setEnvironment(environment);
  }

  public String getEnvironment() {
    return this.environment;
}

public void setEnvironment(String environment) {
    this.environment = environment;
}

@Override
public void setPassword(String password) {
    if (password == null){
        UserPasswordGenerator generatedpassword= new UserPasswordGenerator();
        password = generatedpassword.generatePassayPassword();
    }
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    password = bCryptPasswordEncoder.encode(password);
    super.setPassword(password);
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("USER"));
}

}

