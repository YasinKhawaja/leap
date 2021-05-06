package edu.ap.group10.leapwebapp.useradmin;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.company.Company;

@Entity // This makes a table out of this class
@Inheritance
@DiscriminatorColumn(name="user_type")
@Table(name="user")
@DiscriminatorValue("admin")
public class Useradmin implements UserDetails{
  //add names for colums like so (name="first_name") instead of firstName

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, unique = true, updatable = false, name = "user_id")
  private Long id;
  @Column(nullable = false, unique = true, length = 25, name = "user_username")
  private String username;
  @Column(nullable = false, name = "user_first_name")
  private String firstName;
  @Column(nullable = false, name = "user_surname")
  private String surname;
  @Column(nullable = false, name = "user_email")
  private String email;
  @Column(nullable = false, name = "user_password")
  private String password;
  @Column(nullable = false, name = "user_company")
  private Long company;

  public Useradmin(){}

  public Useradmin(String firstName, String surname, String email, String username, String password, Long Company)
  {
    this.setFirstName(firstName);
    this.setSurname(surname);
    this.setEmail(email);
    this.setUsername(username);
    this.setPassword(password);
    this.setCompany(company);
  }

  public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getCompany() {
  return this.company;
}

public void setCompany(Long company) {
  this.company = company;
}
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    password = bCryptPasswordEncoder.encode(password);
    this.password = password;
  }

  @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("USER_ADMIN"));
}

@Override
public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
}

@Override
public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
}

@Override
public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
}
}