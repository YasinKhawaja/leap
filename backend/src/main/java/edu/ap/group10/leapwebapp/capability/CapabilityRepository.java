
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.environment.Environment;

@Repository
public interface CapabilityRepository extends JpaRepository<Capability, Long> {

    public Capability findByName(String name);

    public List<Capability> findByEnvironment(Environment environment);

    public Boolean existsByName(String name);

    public List<Capability> searchOneByName(String name);

}
