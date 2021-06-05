package edu.ap.group10.leapwebapp.company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void deleteCompany(Long companyId){
        companyRepository.deleteById(companyId);
    }

    public Company findCompany(Long companyId){
        return companyRepository.findById(companyId)
        .orElseThrow(ResourceNotFoundException::new);
    }
    
    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<Company>();
        for (Company company : companyRepository.findAll()) {
            companies.add(company);
        }
        return companies;
    }
}
