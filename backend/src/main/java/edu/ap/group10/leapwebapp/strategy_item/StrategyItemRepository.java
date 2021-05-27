package edu.ap.group10.leapwebapp.strategy_item;

import org.springframework.stereotype.Repository;


import edu.ap.group10.leapwebapp.strategy.Strategy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StrategyItemRepository extends JpaRepository<StrategyItem, Long> {
    
    public List<StrategyItem> findByStrategy(Strategy strategy);
}
