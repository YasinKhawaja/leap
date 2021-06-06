
package edu.ap.group10.leapwebapp.environment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
    public Boolean existsByName(String name);

}
