package edu.ap.group10.leapwebapp.cabilitybusinessprocess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityBusinessProcessRepository extends CrudRepository<CapabilityBusinessProcess, Long>{
    
}
