package edu.ap.group10.leapwebapp.environment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.company.CompanyService;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class EnvironmentControllerIntegrationTest {

    @Autowired 
    private MockMvc mvc;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @AfterEach
    void tearDown(){
        environmentRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void givenEnvironments_whenGetAllEnvironments_returnsEnvironmentsArePresent() throws Exception{
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
        environmentService.createEnvironment("Siemens", company.getId().toString());
        environmentService.createEnvironment("Philips", company.getId().toString());

        mvc.perform(get("/environments").param("companyid", company.getId().toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Siemens'}, {'name':'Philips'}]"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentId_whenGetEnvironment_returnsEnvironment() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long Id = environment.getId();

        mvc.perform(get("/environments/{id}",Id))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));
    }

    @Test
    @WithMockUser
    public void givenEnvironment_whenCreateEnvironment_returnsEnvironmentSaved() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        mvc.perform(post("/environments").with(csrf()).param("name", "Siemens").param("companyid", company.getId().toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));
    }

    @Test
    @WithMockUser
    public void givenNewEnvironmentName_whenUpdateEnvironment_returnsEnvironmentUpdatedWithNewName() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
        Environment environment = environmentService.createEnvironment("Philips", company.getId().toString());
        Long Id = environment.getId();
        

        mvc.perform(put("/environments/{id}",Id).with(csrf()).param("name", "Siemens"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));

    }

    @Test
    @WithMockUser
    public void givenEnvironmentId_whenDeleteEnvironment_returnsEnvironmentDeleted() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long Id = environment.getId();

        mvc.perform(delete("/environments/{id}", Id).with(csrf()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }
    
}
