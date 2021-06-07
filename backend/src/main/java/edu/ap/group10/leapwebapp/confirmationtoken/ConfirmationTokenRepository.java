package edu.ap.group10.leapwebapp.confirmationtoken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long>{
    ConfirmationToken findByToken(String token);
}
