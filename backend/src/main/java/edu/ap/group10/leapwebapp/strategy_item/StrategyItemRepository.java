package edu.ap.group10.leapwebapp.strategy_item;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface StrategyItemRepository extends CrudRepository<StrategyItem, Long> {
    
}
