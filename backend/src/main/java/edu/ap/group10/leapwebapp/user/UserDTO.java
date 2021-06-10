package edu.ap.group10.leapwebapp.user;

import edu.ap.group10.leapwebapp.company.Company;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private Integer role;
    private Company company;
}
