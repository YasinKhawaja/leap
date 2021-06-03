package edu.ap.group10.leapwebapp.strategy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends CrudRepository<Strategy,Long> {

    public Strategy findByName(String name);
   
}
