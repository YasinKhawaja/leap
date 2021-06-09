package edu.ap.group10.leapwebapp.capabilityproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityProjectRepository extends CrudRepository<CapabilityProject, Long>{
    
}
