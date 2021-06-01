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
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;

@RestController
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
	private MailService mailService;

  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  //OPTIONAL: Make /companies/register/{id} only accessible by those with the application admin role, requires login and is more inconvenient but more secure
  @PostMapping("/companies/register")
  public String addNewCompany(@RequestParam("vatNumber") String vatNumber
      , @RequestParam("companyName") String companyName, @RequestParam("taxOffice") String taxOffice, @RequestParam("businessActivity") String businessActivity, @RequestParam("email") String email, @RequestParam("streetName") String streetName
      , @RequestParam("houseNumber") Integer houseNumber, @RequestParam("postcode") Integer postcode, @RequestParam("city") String city, @RequestParam("country") String country) {

        Company n = new Company(vatNumber, companyName, email, streetName, houseNumber, postcode, city, country, businessActivity, taxOffice);
        
        companyRepository.save(n);

        ConfirmationToken confirmationToken = new ConfirmationToken(n);
        confirmationTokenRepository.save(confirmationToken);

        String confirmationTokenString =  "http://localhost:4200/company/register/?id=" + n.getId() + "&token=" + confirmationToken.getConfirmationToken();

        Mail mail = new Mail();
        mail.setSender("leapwebapp@gmail.com");
        mail.setReceiver("standaertsander@gmail.com, stijnverhaegen@gmail.com, yasin.khawaja@student.ap.be");
        mail.setSubject("New application from: " + companyName);
        mail.setContent("Click on this link to view the request from: " + companyName + ".\n" + confirmationTokenString);
        mailService.sendMail(mail);

        return "You have sucesfully registered your company. Please wait for one of the admins to confirm your registration. This can take up to 5 business days.";
  }

  @GetMapping("/companies/register/{id}/")
  public Company viewCompanyApplication(@PathVariable String id, @RequestParam("token")String confirmationToken){

    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if(token != null) {
      Long companyID = Long.parseLong(id);
      Company c = companyRepository.findById(companyID).get();

      if(c != null){
        companyService.deleteConfirmationToken(confirmationToken);
        return c;
      }
      else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
      }
    }
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You do not have the permission to accept or refuse a company.");
  }

  @PostMapping("/companies/register/{id}/applicationStatus")
  public String companyApplicationResult(@PathVariable String id, @RequestParam("accepted") Boolean accepted) {
    Long companyID = Long.parseLong(id);
    Company company = companyRepository.findById(companyID).get();
    String companyEmail = company.getEmail();
    
    String confirmationTokenString = "";

    if (accepted){
      ConfirmationToken confirmationToken = new ConfirmationToken(company);
      confirmationTokenRepository.save(confirmationToken);

      confirmationTokenString = "http://localhost:4200/register-useradmin?token=" + confirmationToken.getConfirmationToken();
    }

    Mail mail = new Mail();
    mail.setSender("leapwebapp@gmail.com");
    mail.setReceiver(companyEmail);
    mail.setSubject("Your registration has been reviewed.");
    mail.setContent("The admin has decided to" + (accepted ? " accept " : " deny ") + "your application.\n" +
    (accepted ? "You can register your user-admin by clicking on the following link: " + confirmationTokenString
    : "If you think this was a mistake, then send an e-mail to leapwebapp@gmail.com"));

    mailService.sendMail(mail);

    return "Success";
  }
}
