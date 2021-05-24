package edu.ap.group10.leapwebapp.strategy_item;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StrategyItemRepository extends JpaRepository<StrategyItem, Long> {
    
}
