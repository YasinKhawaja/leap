package edu.ap.group10.leapwebapp.informationtemp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    public Information findByName(String name);
}
