
package edu.ap.group10.leapwebapp.capabilitystrategyitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapStrategyItemsRepository extends JpaRepository<CapStrategyItems, Long> {

}
