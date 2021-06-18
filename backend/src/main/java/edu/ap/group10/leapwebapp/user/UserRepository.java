package edu.ap.group10.leapwebapp.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ap.group10.leapwebapp.company.Company;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByCompany(Company company);
}
