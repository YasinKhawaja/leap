package edu.ap.group10.leapwebapp.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.user.UserLeap;

@Repository
public interface UserRepository extends CrudRepository<UserLeap, Long> {
    UserLeap findByUsername(String username);
    UserLeap findByEmail(String email);
}
