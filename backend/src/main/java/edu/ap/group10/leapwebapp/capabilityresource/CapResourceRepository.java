
package edu.ap.group10.leapwebapp.capabilityresource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapResourceRepository extends JpaRepository<CapResource, Long> {

    List<CapResource> findByCapabilityId(Long id);

    List<CapResource> findByResourceId(Long id);

    Boolean existsByCapabilityIdAndResourceId(Long capabilityId, Long resourceId);

}
