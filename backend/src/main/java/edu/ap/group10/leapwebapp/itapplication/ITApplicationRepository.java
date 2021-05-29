package edu.ap.group10.leapwebapp.itapplication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITApplicationRepository extends CrudRepository<ITApplication, Long>{
    ITApplication findByName(String name);
}
