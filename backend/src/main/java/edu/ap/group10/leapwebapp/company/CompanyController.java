package edu.ap.group10.leapwebapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenService;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;

@RestController
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @Autowired
  private ConfirmationTokenService confirmationTokenService;

  @Autowired
	private MailService mailService;

  @PostMapping("/companies")
  public void addNewCompany(@RequestParam("vatNumber") String vatNumber
      , @RequestParam("companyName") String companyName, @RequestParam("taxOffice") String taxOffice, @RequestParam("businessActivity") String businessActivity, @RequestParam("email") String email, @RequestParam("streetName") String streetName
      , @RequestParam("houseNumber") Integer houseNumber, @RequestParam("postcode") Integer postcode, @RequestParam("city") String city, @RequestParam("country") String country) throws Exception{

        try{
          Company n = new Company(vatNumber, companyName, email, streetName, houseNumber, postcode, city, country, businessActivity, taxOffice);
        
          companyService.addCompany(n);
  
          String token = confirmationTokenService.addConfirmationToken(n);
  
          String confirmationTokenString =  "http://localhost:4200/company/register/?id=" + n.getId() + "&token=" + token;
  
          Mail mail = new Mail();
          mail.setSender("leapwebapp@gmail.com");
          mail.setReceiver("standaertsander@gmail.com, stijnverhaegen@gmail.com, yasin.khawaja@student.ap.be");
          mail.setSubject("New application from: " + companyName);
          mail.setContent("Click on this link to view the request from: " + companyName + ".\n" + confirmationTokenString);
          mailService.sendMail(mail);
        } catch (Exception e){
          throw new Exception(e);
        }
  }

  @GetMapping("/companies/{token}")
  public Company viewCompanyApplication(@PathVariable("token") String confirmationToken){

    ConfirmationToken token = confirmationTokenService.getConfirmationToken(confirmationToken);
    if(token != null) {
      Company c = companyService.findCompany(token.getCompany().getId());

      if(c != null){
        return c;
      }
      else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
      }
    }
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You do not have the permission to accept or refuse a company.");
  }

  @PostMapping("/companies/{token}/applicationStatus")
  public void companyApplicationResult(@PathVariable String token, @RequestParam("accepted") Boolean accepted) {

    ConfirmationToken verifyToken = confirmationTokenService.getConfirmationToken(token);

    Long companyID = verifyToken.getCompany().getId();
    Company company = companyService.findCompany(companyID);

    confirmationTokenService.deleteConfirmationToken(token);
    
    String confirmationTokenString = "";

    if (accepted.booleanValue()){
      String tokenAdmin = confirmationTokenService.addConfirmationToken(company);
      confirmationTokenString = "http://localhost:4200/register-useradmin?token=" + tokenAdmin;
    } else {
      companyService.deleteCompany(companyID);
    }

    Mail mail = new Mail();
    mail.setSender("leapwebapp@gmail.com");
    mail.setReceiver(company.getEmail());
    mail.setSubject("Your registration has been reviewed.");
    mail.setContent("The admin has decided to" + (accepted.booleanValue() ? " accept " : " deny ") + "your application.\n" +
    (accepted.booleanValue() ? "You can register your user-admin by clicking on the following link: " + confirmationTokenString
    : "If you think this was a mistake, then send an e-mail to leapwebapp@gmail.com"));

    mailService.sendMail(mail);
  }
}
