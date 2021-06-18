package edu.ap.group10.leapwebapp.businessprocess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcessService;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class BusinessProcessControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private BusinessProcessService businessProcessService;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    private static final Environment environment = new Environment("Test environment", company);
    
    private static final BusinessProcess businessProcess1 = new BusinessProcess("Sales", "Income of the year 2021", environment);
    private static final BusinessProcess businessProcess2 = new BusinessProcess("HR", "Staff income", environment);


    @BeforeAll
    void setup() {
        companyRepository.save(company);
        environmentRepository.save(environment);

        businessProcessService.addBusinessProcess(businessProcess1);
        businessProcessService.addBusinessProcess(businessProcess2);
    }

    @Test
    @WithMockUser
    public void givenEnvironmentId_whenGetAllBussinessProcesses_returnsBusinessProcesses() throws Exception {

        //given
        Long environmentId = environment.getId();

        //when
        mvc.perform(get("/businessprocesses/{environmentid}", environmentId))
        
        //then
        .andExpect(status().isOk()).andDo(print())
        .andExpect(content().json("[{'name': 'Sales'}, {'name':'HR'}]"));
    }

    @Test
    @WithMockUser
    public void givenBusinessProcessId_whenGetBusinessProcess_returnsBusinessProcess() throws Exception {

        //given
        Long businessProcess1Id = businessProcess1.getId();

        //when
        mvc.perform(get("/businessprocess/{businessprocessid}", businessProcess1Id))
        
        //then
        .andExpect(status().isOk()).andDo(print())
        .andExpect(content().json("{'name': 'Sales'}"));
    }
    
}
