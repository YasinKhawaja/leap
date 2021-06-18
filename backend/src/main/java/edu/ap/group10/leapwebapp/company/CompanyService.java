package edu.ap.group10.leapwebapp.company;

import org.springframework.security.access.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.security.CustomAuthenticationProvider;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    public boolean checkRole(String role) throws AccessDeniedException {
        Boolean validator = false;
        if (role.equals("Application admin")) {
            validator = true;
        } else {
            throw new AccessDeniedException("Only application admins can access this");
        }
        return validator;
    }

    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    public Company findCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(ResourceNotFoundException::new);
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            companies.add(company);
        }
        return companies;
    }

    public void updateCompany(Long companyid, boolean value) {
        Company oldCompany = companyRepository.findById(companyid).orElseThrow(ResourceNotFoundException::new);
        oldCompany.setApproved(value);
        companyRepository.save(oldCompany);
    }

    public String getRole(String token) {
        String role = customAuthenticationProvider.getRole(token);
        return role.substring(2, role.length() - 2);
    }
}
