
package edu.ap.group10.leapwebapp.capability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.environment.Environment;

@Repository
public interface CapabilityRepository extends JpaRepository<Capability, Long> {

    public List<Capability> findByEnvironment(Environment environment);

}
