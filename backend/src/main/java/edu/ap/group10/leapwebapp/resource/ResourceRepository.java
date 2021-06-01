
package edu.ap.group10.leapwebapp.resource;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Boolean existsByName(String name);

}
