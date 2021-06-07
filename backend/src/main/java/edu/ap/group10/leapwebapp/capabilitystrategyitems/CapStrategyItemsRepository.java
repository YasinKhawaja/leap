
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;

@Repository
public interface CapStrategyItemsRepository extends JpaRepository<CapStrategyItems, Long> {

 

   // public List<CapResource> findByCapabilityId(Long id);

   // public List<CapResource> findByResourceId(Long id);

  

}
