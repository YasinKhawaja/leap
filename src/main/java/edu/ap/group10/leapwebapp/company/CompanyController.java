package edu.ap.group10.leapwebapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;


  //COMPANY
  @GetMapping("/companies")
  public @ResponseBody Iterable<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  @PostMapping("/companies/register")
  public @ResponseBody String addNewCompany(@RequestParam("vatNumber") String vatNumber
      , @RequestParam("companyName") String companyName, @RequestParam("taxOffice") String taxOffice, @RequestParam("businessActivity") String businessActivity, @RequestParam("email") String email, @RequestParam("streetName") String streetName
      , @RequestParam("houseNumber") Integer houseNumber, @RequestParam("postcode") Integer postcode, @RequestParam("city") String city, @RequestParam("country") String country) {

    Company n = new Company(vatNumber, companyName, email, streetName, houseNumber, postcode, city, country, businessActivity, taxOffice);
    companyRepository.save(n);
    return "Saved";
  }
}