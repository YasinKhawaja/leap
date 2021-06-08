
package edu.ap.group10.leapwebapp.environment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    public List<Environment> getAllEnvironments(String companyid) {
        List<Environment> environments = new ArrayList<>();
        for (Environment environment : environmentRepository.findAll()) {
            if(environment.getCompany().getId().equals(Long.parseLong(companyid))){
                environments.add(environment);
            }
        }
        return environments;
    }

    public Environment getEnvironment(Long id) throws NoSuchElementException {
        return environmentRepository.findById(id).orElseThrow();
    }

    public Environment createEnvironment(String name, String companyid) throws ArithmeticException {
        if (environmentRepository.existsByName(name).booleanValue()) {
            throw new ArithmeticException("Environment already exists!");
        }
        Company company = companyRepository.findById(Long.parseLong(companyid))
        .orElseThrow(ResourceNotFoundException::new);
        Environment envToSave = new Environment(name, company);

        return environmentRepository.save(envToSave);
    }

    public Environment updateEnvironment(Long id, String name) throws NoSuchElementException, ArithmeticException {
        Environment envToUpdate = environmentRepository.findById(id).orElseThrow();

        if (environmentRepository.existsByName(name).booleanValue()) {
            throw new ArithmeticException("An environment with this name already exists!");
        }

        envToUpdate.setName(name);

        return environmentRepository.save(envToUpdate);
    }

    public void deleteEnvironment(Long id) throws IllegalArgumentException {
        environmentRepository.deleteById(id);
    }

}
