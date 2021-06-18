package edu.ap.group10.leapwebapp.capabilityinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityInfoRepository extends JpaRepository<CapabilityInfo, Long> {

}
