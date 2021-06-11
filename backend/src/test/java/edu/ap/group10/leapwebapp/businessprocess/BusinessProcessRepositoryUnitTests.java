package edu.ap.group10.leapwebapp.businessprocess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcessRepository;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BusinessProcessRepositoryUnitTests {

    @Autowired
    private BusinessProcessRepository sut;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @BeforeAll
    void setup() {
        companyRepository.deleteAll();
        environmentRepository.deleteAll();
        companyRepository.save(company);
        environmentRepository.save(environment);
    }

    @BeforeEach
    void delete() {
        sut.deleteAll();
    }

    @Test
    void givenBusinessProcess_whenFindByName_returnsBusinessProcess() {
        
        //given 
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);
        sut.save(businessProcess);

        //when
        BusinessProcess actualBusinessProcess = sut.findByName(businessProcess.getName());

        //then 
        assertEquals(businessProcess.getId(), actualBusinessProcess.getId());
    }

    @Test
    void givenBusinessProcess_whenFindByName_returnsNULL() {

        //given 
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);
        sut.save(businessProcess);

        //when
        BusinessProcess actualBusinessProcess = sut.findByName("Marketing");

        //then
        assertEquals(null, actualBusinessProcess);
    }
    
}
