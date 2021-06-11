package edu.ap.group10.leapwebapp.itapplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITApplicationRepository extends JpaRepository<ITApplication, Long> {
    ITApplication findByName(String name);
}
