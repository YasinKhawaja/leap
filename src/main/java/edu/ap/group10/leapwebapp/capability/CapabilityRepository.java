package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CapabilityRepository extends CrudRepository<Capability, Long>{
    public Capability findByName(String name);

    public List<Capability> searchOneByName(String name);
    
}
