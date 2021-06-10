package edu.ap.group10.leapwebapp.capability;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.company.CompanyService;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;
import edu.ap.group10.leapwebapp.environment.EnvironmentService;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class CapabilityControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CapabilityRepository capabilityRepository;

    @Autowired
    private CapabilityService capabilityService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private EnvironmentService environmentService;

    @AfterEach
    void tearDown(){
        capabilityRepository.deleteAll();
        environmentRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void givenCapabilities_whenGetAllCapabilities_returnsAllCapabilities() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);
        
        Environment environment1 = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environment1Id = environment1.getId();

        Environment environment2 = environmentService.createEnvironment("Philips", company.getId().toString());
        Long environment2Id = environment2.getId();

        Capability capability1 = new Capability("Youth", environment1);

        Capability capability2 = new Capability("Management", environment2);

        Capability capability3 = new Capability("Transfers", environment2);
        
        capabilityService.createCapability(environment1Id, null, capability1);
        capabilityService.createCapability(environment1Id, capability1.getId(), capability2);
        capabilityService.createCapability(environment2Id, null, capability3);

        mvc.perform(get("/capabilities/all"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Transfers'}, {'name':'Management'}, {'name':'Youth'}]"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentId_whenGetAllCapabilitiesInEnvironment_returnsAllCapabilities() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();

        Capability capability1 = new Capability("Youth", environment);

        Capability capability2 = new Capability("Management", environment);

        Capability capability3 = new Capability("Transfers", environment);
        
        capabilityService.createCapability(environmentId, null, capability1);
        capabilityService.createCapability(environmentId, capability1.getId(), capability2);
        capabilityService.createCapability(environmentId, capability1.getId(), capability3);

        mvc.perform(get("/capabilities").param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Youth'},{'name':'Transfers'}, {'name':'Management'}]"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityId_whenGetCapabilityInEnvironment_returnsCapabilityFound() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();

        Capability capability1 = new Capability("Youth", environment);

        Capability capability2 = new Capability("Management", environment);

        Capability capabilityCreated = capabilityService.createCapability(environmentId, capability1.getId(), capability2);
        Long capabilityCreatedId = capabilityCreated.getId();

        mvc.perform(get("/capabilities/{capId}", capabilityCreatedId).param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Management'}"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentIdParentCapabilityIdCapabilityToCreate_whenCreateCapability_returnsCapabilityCreated() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth", environment);

        Capability capabilityParent = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityParentId = capabilityParent.getId();

        Capability capabilityToCreate = new Capability("Management", environment);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(capabilityToCreate);

        

        mvc.perform(post("/capabilities").with(csrf()).param("envId", environmentId.toString()).param("parentCapId", capabilityParentId.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Management'}"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityIdUpdatedCapability_whenUpdateCapability_returnsUpdatedCapability() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth", environment);

        Capability capabilityToUpdate = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityToUpdateId = capabilityToUpdate.getId();

        capabilityToUpdate.setName("Youth 2.0");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(capabilityToUpdate);

        

        mvc.perform(put("/capabilities/{capId}", capabilityToUpdateId).with(csrf()).param("envId", environmentId.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Youth 2.0'}"));
    }

    @Test
    @WithMockUser
    public void givenEnvironmentIdCapabilityId_whenDeleteCapability_returnsCapabilityDeleted() throws Exception {
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        companyService.addCompany(company);

        Environment environment = environmentService.createEnvironment("Siemens", company.getId().toString());
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth", environment);

        Capability capabilityToDelete = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityToDeleteId = capabilityToDelete.getId();

        mvc.perform(delete("/capabilities/{id}", capabilityToDeleteId).with(csrf()).param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }
    
}
