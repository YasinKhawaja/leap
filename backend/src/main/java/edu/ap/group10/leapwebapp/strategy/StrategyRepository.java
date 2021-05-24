package edu.ap.group10.leapwebapp.strategy;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StrategyRepository extends CrudRepository<Strategy,Long> {
   


}
