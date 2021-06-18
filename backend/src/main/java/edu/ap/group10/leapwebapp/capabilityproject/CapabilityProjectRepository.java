package edu.ap.group10.leapwebapp.capabilityproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityProjectRepository extends JpaRepository<CapabilityProject, Long> {

}
