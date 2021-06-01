package edu.ap.group10.leapwebapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void deleteConfirmationToken(String token){
        confirmationTokenRepository.delete(confirmationTokenRepository.findByConfirmationToken(token));
    }
    
}
