package edu.ap.group10.leapwebapp.company;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.company.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
}