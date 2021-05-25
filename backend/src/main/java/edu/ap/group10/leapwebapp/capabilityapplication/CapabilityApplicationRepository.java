package edu.ap.group10.leapwebapp.capabilityapplication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityApplicationRepository extends CrudRepository<CapabilityApplication, Long>{
    
}
