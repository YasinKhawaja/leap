package edu.ap.group10.leapwebapp.strategy_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ap.group10.leapwebapp.strategy.StrategyRepository;

@Service
public class StrategyItemService {

    @Autowired
    private StrategyItemRepository strategyItemRepository;

    @Autowired
    private StrategyRepository strategyRepository;

    
}
