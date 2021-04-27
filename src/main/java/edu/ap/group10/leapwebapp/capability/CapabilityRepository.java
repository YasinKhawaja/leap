package edu.ap.group10.leapwebapp.capability;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityRepository extends CrudRepository<Capability, Long>{
    
}
