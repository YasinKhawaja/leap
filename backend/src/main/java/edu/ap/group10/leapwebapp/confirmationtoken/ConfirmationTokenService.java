package edu.ap.group10.leapwebapp.confirmationtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.company.Company;

@Service
public class ConfirmationTokenService {
    
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void deleteConfirmationToken(String token){
        confirmationTokenRepository.delete(confirmationTokenRepository.findByToken(token));
    }

    public String addConfirmationToken(Company company){
        ConfirmationToken confirmationToken = new ConfirmationToken(company);
        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken.getToken();
    }

    public ConfirmationToken getConfirmationToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }
}
