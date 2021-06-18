package edu.ap.group10.leapwebapp.capabilityapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityApplicationRepository extends JpaRepository<CapabilityApplication, Long> {

}
