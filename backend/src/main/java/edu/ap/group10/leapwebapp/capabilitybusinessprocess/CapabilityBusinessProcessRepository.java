package edu.ap.group10.leapwebapp.capabilitybusinessprocess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityBusinessProcessRepository extends JpaRepository<CapabilityBusinessProcess, Long> {

}
