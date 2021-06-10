package edu.ap.group10.leapwebapp.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class StrategyRepositoryUnitTests {


    @Autowired
   private StrategyRepository srt;
    
    @Autowired
    private EnvironmentRepository environmentRespository;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        srt.deleteAll();
    }


    @Test
    void givenStrategyName_whenFindByName_returnsStrategyFound() {

       // Given
      Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
      companyRepository.save(company);
      String name = "Siemens";
      Environment env = new Environment(name, company);
      environmentRespository.save(env);

      String strategyName = "Jan";
      Strategy strategy = new Strategy(strategyName,"2020-02-01","2021-02-03",env);
      srt.save(strategy);


     // When
      Strategy strFound = srt.findByName(strategyName);

      // Then
      assertEquals(strategy.getId(), strFound.getId());
      assertEquals(strategy.getEnvironment().getId(), strFound.getEnvironment().getId());
     }
    
    
}
