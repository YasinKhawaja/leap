package edu.ap.group10.leapwebapp.company;

import java.util.Arrays;
import java.util.List;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.group10.leapwebapp.confirmationtoken.ConfirmationTokenService;
import edu.ap.group10.leapwebapp.mail.Mail;
import edu.ap.group10.leapwebapp.mail.MailService;
import edu.ap.group10.leapwebapp.security.SecurityConstraints;
import edu.ap.group10.leapwebapp.user.UserService;
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

  @Autowired
  private UserService userService;

  @GetMapping("/companies")
  public List<Company> getAllCompanies(@RequestHeader("Authorization") String auth) {
    try {
      String token = auth.replace(SecurityConstraints.TOKEN_PREFIX, "");
      token = this.companyService.getRole(token);
      if (companyService.checkRole(token)) {
        return companyService.getCompanies();
      }
    } catch (AccessDeniedException ex) {
      log.error(ex.getMessage());
    }
    return Arrays.asList();
  }

  @PostMapping("/company")
  public void addNewCompany(@RequestBody Company company) {
    company.setApproved(false);
    companyService.addCompany(company);

    String confirmationTokenString = "http://localhost:4200/company/register/" + company.getId();

    Mail mail = new Mail();
    String emails = userService.getApplicationAdmins();
    mail.setReceiver(emails);
    mail.setSubject("New application from: " + company.getCompanyName());
    mail.setContent(
        "Click on this link to view the request from: " + company.getCompanyName() + ".\n" + confirmationTokenString);
    mailService.sendMail(mail);
  }

  @GetMapping("/company/application/{companyid}")
  public Company viewCompanyApplication(@RequestHeader("Authorization") String auth, @PathVariable Long companyid) {
    Company c = null;
    try {
      String token = auth.replace(SecurityConstraints.TOKEN_PREFIX, "");
      token = this.companyService.getRole(token);
      if (companyService.checkRole(token)) {
        c = companyService.findCompany(companyid);
      }
      return c;
    } catch (Exception e) {
      log.error(e.getMessage());
      return c;
    }
  }

  @PostMapping("/company/application/{companyid}")
  public void companyApplicationResult(@PathVariable Long companyid, @RequestParam("accepted") Boolean accepted,
      @RequestHeader("Authorization") String auth) {
    String authToken = auth.replace(SecurityConstraints.TOKEN_PREFIX, "");
    authToken = this.companyService.getRole(authToken);
    try {
      if (companyService.checkRole(authToken)) {
        Long companyID = companyid;
        Company company = companyService.findCompany(companyID);

        String confirmationTokenString = "";

        if (accepted.booleanValue()) {
          String tokenAdmin = confirmationTokenService.addConfirmationToken(company);
          confirmationTokenString = "http://localhost:4200/register-useradmin?token=" + tokenAdmin;
          companyService.updateCompany(company.getId(), true);
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

  @PostMapping("/company/status")
  public void changeCompanyStatus(@RequestHeader("Authorization") String auth, @RequestParam Long companyid,
      @RequestParam Boolean status) {
    String token = auth.replace(SecurityConstraints.TOKEN_PREFIX, "");
    token = this.companyService.getRole(token);
    if (companyService.checkRole(token)) {
      this.companyService.updateCompany(companyid, status);
    } else {
      log.error("Unatuhorized request", new AuthenticationFailedException());
    }
  }
}
