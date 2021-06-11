package edu.ap.group10.leapwebapp.company;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationToken;
import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenService;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @Autowired
  private ConfirmationTokenService confirmationTokenService;

  @Autowired
  private MailService mailService;

  @GetMapping("/companies")
  public List<Company> getAllCompanies(@RequestParam String role) {
    try {
      if (companyService.checkRole(role)) {
        return companyService.getCompanies();
      }
    } catch (AccessDeniedException ex) {
      log.error(ex.getMessage());
    }
    return Arrays.asList();
  }

  @PostMapping("/companies")
  public void addNewCompany(@RequestBody Company company) {
    companyService.addCompany(company);

    String token = confirmationTokenService.addConfirmationToken(company);

    String confirmationTokenString = "http://localhost:4200/company/register/?id=" + company.getId() + "&token="
        + token;

    Mail mail = new Mail();
    // change setReceiver to get a list of email adresses from the application
    // admins
    mail.setReceiver("standaertsander@gmail.com, stijnverhaegen@gmail.com, yasin.khawaja@student.ap.be, janelguera@gmail.com");
    mail.setSubject("New application from: " + company.getCompanyName());
    mail.setContent(
        "Click on this link to view the request from: " + company.getCompanyName() + ".\n" + confirmationTokenString);
    mailService.sendMail(mail);
  }

  @GetMapping("/companies/{token}")
  public Company viewCompanyApplication(@PathVariable("token") String confirmationToken, @RequestParam String role) {
    Company c = null;
    try {
      if (companyService.checkRole(role)) {
        ConfirmationToken token = confirmationTokenService.getConfirmationToken(confirmationToken);
        c = companyService.findCompany(token.getCompany().getId());
      }
      return c;
    } catch (Exception e) {
      log.error(e.getMessage());
      return c;
    }
  }

  @PostMapping("/companies/{token}/applicationStatus")
  public void companyApplicationResult(@PathVariable String token, @RequestParam("accepted") Boolean accepted,
      @RequestParam String role) {
    try {
      if (companyService.checkRole(role)) {
        ConfirmationToken verifyToken = confirmationTokenService.getConfirmationToken(token);

        Long companyID = verifyToken.getCompany().getId();
        Company company = companyService.findCompany(companyID);

        confirmationTokenService.deleteConfirmationToken(token);

        String confirmationTokenString = "";

        if (accepted.booleanValue()) {
          String tokenAdmin = confirmationTokenService.addConfirmationToken(company);
          confirmationTokenString = "http://localhost:4200/register-useradmin?token=" + tokenAdmin;
        } else {
          companyService.deleteCompany(companyID);
        }

        Mail mail = new Mail();
        mail.setReceiver(company.getEmail());
        mail.setSubject("Your registration has been reviewed.");
        mail.setContent(
            "The admin has decided to" + (accepted.booleanValue() ? " accept " : " deny ") + "your application.\n"
                + (accepted.booleanValue()
                    ? "You can register your user-admin by clicking on the following link: " + confirmationTokenString
                    : "If you think this was a mistake, then send an e-mail to leapwebapp@gmail.com"));

        mailService.sendMail(mail);
      }
    } catch (AccessDeniedException ex) {
      log.error(ex.getMessage());
    }
  }
}
