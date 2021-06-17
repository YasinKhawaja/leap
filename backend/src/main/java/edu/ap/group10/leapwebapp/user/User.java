package edu.ap.group10.leapwebapp.user;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.company.Company;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {
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
    @Column(nullable = false, name = "user_role")
    private Integer role;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "company_id")
    private Company company;

    public User() {
    }

    public User(String firstName, String surname, String email, String username, String password, Integer role,
            Company company) {
        this.setRole(role);
        this.setFirstName(firstName);
        this.setSurname(surname);
        this.setEmail(email);
        this.setUsername(username);
        this.setPassword(password);
        this.setCompany(company);
    }

    public void setPassword(String password) {
        if (password.equals("")) {
            this.password = password;
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);
            this.password = password;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = "Reader";
        if (getRole() == 0) {
            userRole = "Admin";
        } else if (getRole() == 1) {
            userRole = "Editor";
        } else if (getRole() == -1) {
            userRole = "Application admin";
        }
        return Collections.<GrantedAuthority>singleton(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
