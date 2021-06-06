package edu.ap.group10.leapwebapp.businessprocesses;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProcessRepository extends CrudRepository<BusinessProcess, Long>{
    BusinessProcess findByName(String name);
}
