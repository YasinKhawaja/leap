
package edu.ap.group10.leapwebapp.capabilityresource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapResourceRepository extends JpaRepository<CapResource, Long> {

    public List<CapResource> findByCapabilityId(Long id);

    public List<CapResource> findByResourceId(Long id);

    public Boolean existsByCapabilityIdAndResourceId(Long capabilityId, Long resourceId);

}
