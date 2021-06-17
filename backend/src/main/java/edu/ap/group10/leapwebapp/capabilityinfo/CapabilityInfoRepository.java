package edu.ap.group10.leapwebapp.capabilityinfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityInfoRepository extends CrudRepository<CapabilityInfo, Long> {

}
