package edu.ap.group10.leapwebapp.company;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenRepository;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CompanyController {

  //to do: put mail sender in a project wide variable

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
	private MailService mailService;

  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  //COMPANY
  @GetMapping("/companies")
  public @ResponseBody Iterable<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  //OPTIONAL: Make /companies/register/{id} only accessible by those with the application admin role, requires login and is more inconvenient but more secure
  @PostMapping("/companies/register")
  public @ResponseBody String addNewCompany(@RequestParam("vatNumber") String vatNumber
      , @RequestParam("companyName") String companyName, @RequestParam("taxOffice") String taxOffice, @RequestParam("businessActivity") String businessActivity, @RequestParam("email") String email, @RequestParam("streetName") String streetName
      , @RequestParam("houseNumber") Integer houseNumber, @RequestParam("postcode") Integer postcode, @RequestParam("city") String city, @RequestParam("country") String country) {

        Company n = new Company(vatNumber, companyName, email, streetName, houseNumber, postcode, city, country, businessActivity, taxOffice);
        
        System.out.println(n);
        
        companyRepository.save(n);

        ConfirmationToken confirmationToken = new ConfirmationToken(n);
        confirmationTokenRepository.save(confirmationToken);
        String confirmationTokenString =  "https://localhost:8080/company/register/" + n.getId() + "?token=" + confirmationToken.getConfirmationToken();

        Mail mail = new Mail();
        mail.setSender("leapwebapp@gmail.com");
        mail.setReceiver("standaertsander@gmail.com");
        mail.setSubject("New application from: " + companyName);
        mail.setContent("Click on this link to view the request from: " + companyName + ".\n" + confirmationTokenString);
        mailService.sendMail(mail);

        return "You have sucesfully registered your company. Please wait for one of the admins to confirm your registration. This can take up to 5 business days.";
  }

  //Not implemented yet
  @GetMapping("/companies/register/{id}")
  public @ResponseBody String viewCompanyApplication(@PathVariable String sID, @RequestParam("token")String confirmationToken){

    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if(token != null) {
      Long id = Long.parseLong(sID);
      Company c = companyRepository.findById(id).get();
    //return all the details of the company onto an angular page with a form to accept/deny given company
      return "something";
    }
    //pressing accept will send a true boolean to /companies/register/{id}/applicationStatus
    //pressing refuse will send a false boolean to /companies/register/{id}/applicationStatus
    return "You do not have the permission to accept or refuse a company.";
  }

  @PostMapping("/companies/register/{id}/applicationStatus")
  public @ResponseBody String companyApplicationResult(@PathVariable String id, @RequestParam("accepted") Boolean accepted) {
    Long companyID = Long.parseLong(id);
    Company company = companyRepository.findById(companyID).get();
    String companyEmail = company.getEmail();
    
    String confirmationTokenString = "";

    if (accepted){
      //generate link for useradmin registering
      ConfirmationToken confirmationToken = new ConfirmationToken(company);
      confirmationTokenRepository.save(confirmationToken);
      confirmationTokenString =  "https://localhost:8080/useradmin/register?token=" + confirmationToken.getConfirmationToken();
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