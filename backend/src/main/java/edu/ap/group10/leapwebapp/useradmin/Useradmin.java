package edu.ap.group10.leapwebapp.useradmin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This makes a table out of this class
public class Useradmin {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String surname;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;

  public Useradmin(String firstName, String surname, String email, String username, String password)
  {
    this.setFirstName(firstName);
    this.setSurname(surname);
    this.setEmail(email);
    this.setUsername(username);
    this.setPassword(password);
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
    this.password = password;
  }
}