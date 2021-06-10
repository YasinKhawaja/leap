package edu.ap.group10.leapwebapp.strategyitems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.*;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.strategy.Strategy;
import edu.ap.group10.leapwebapp.strategy.StrategyRepository;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItem;
import edu.ap.group10.leapwebapp.strategy_item.StrategyItemRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class StrategyItemRepositoryUnitTests {

  @Autowired
  StrategyItemRepository sir;

  @Autowired
  StrategyRepository strategyRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  EnvironmentRepository environmentRespository;

  @BeforeEach
  void setUp() {

    sir.deleteAll();
    strategyRepository.deleteAll();
    environmentRespository.deleteAll();
  }

  @Test
  void givenStrategy_whenFindByStrategy_returnsStrategyItemFound() {

    // Given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR",
        "?");
    companyRepository.save(company);
    String name = "Siemens";
    Environment env = new Environment(name, company);
    environmentRespository.save(env);

    Strategy strategy = new Strategy("TestStrategy", "2020-02-01", "2020-05-03", env);
    strategyRepository.save(strategy);

    StrategyItem strategyItem = new StrategyItem("TestStrategyItem", "description1");
    strategyItem.setStrategy(strategy);
    sir.save(strategyItem);

    // When
    List<StrategyItem> strItems = sir.findByStrategy(strategy);

    // Then
    assertEquals(strategyItem.getId(), strItems.get(0).getId());
    assertEquals(strategyItem.getStrategy().getId(), strItems.get(0).getStrategy().getId());
  }

  @Test
  void givenStrategyItemName_whenFindByName_returnsStrategyItemFound() {

    // Given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR",
        "?");
    companyRepository.save(company);
    String name = "Siemens";
    Environment env = new Environment(name, company);
    environmentRespository.save(env);

    Strategy strategy = new Strategy("TestStrategy", "2020-03-04", "2020-10-05", env);
    strategyRepository.save(strategy);

    String strategyItemName = "Jan";
    StrategyItem strategyItem = new StrategyItem(strategyItemName, "description1");
    strategyItem.setStrategy(strategy);
    sir.save(strategyItem);

    // When
    StrategyItem strItemFound = sir.findByName(strategyItemName);

    // Then
    assertEquals(strategyItem.getId(), strItemFound.getId());
    assertEquals(strategyItem.getStrategy().getId(), strItemFound.getStrategy().getId());
  }
}
