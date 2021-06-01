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
import org.springframework.test.web.servlet.MockMvc;

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
    }

    @Test
    public void givenCapabilities_whenGetAllCapabilities_returnsAllCapabilities() throws Exception {
        Environment environment1 = environmentService.createEnvironment("Siemens");
        Long environment1Id = environment1.getId();

        Environment environment2 = environmentService.createEnvironment("Philips");
        Long environment2Id = environment2.getId();

        Capability capability1 = new Capability("Youth");
        capability1.setEnvironment(environment1);

        Capability capability2 = new Capability("Management");
        capability2.setEnvironment(environment1);

        Capability capability3 = new Capability("Transfers");
        capability3.setEnvironment(environment2);
        
        capabilityService.createCapability(environment1Id, null, capability1);
        capabilityService.createCapability(environment1Id, capability1.getId(), capability2);
        capabilityService.createCapability(environment2Id, null, capability3);

        mvc.perform(get("/capabilities/all"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Transfers'}, {'name':'Management'}, {'name':'Youth'}]"));
    }

    @Test
    public void givenEnvironmentId_whenGetAllCapabilitiesInEnvironment_returnsAllCapabilities() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long environmentId = environment.getId();

        Capability capability1 = new Capability("Youth");
        capability1.setEnvironment(environment);

        Capability capability2 = new Capability("Management");
        capability2.setEnvironment(environment);

        Capability capability3 = new Capability("Transfers");
        capability3.setEnvironment(environment);
        
        
        capabilityService.createCapability(environmentId, capability1.getId(), capability2);
        capabilityService.createCapability(environmentId, capability1.getId(), capability3);

        mvc.perform(get("/capabilities").param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Transfers'}, {'name':'Management'}]"));
    }

    @Test
    public void givenEnvironmentIdCapabilityId_whenGetCapabilityInEnvironment_returnsCapabilityFound() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long environmentId = environment.getId();

        Capability capability1 = new Capability("Youth");
        capability1.setEnvironment(environment);

        Capability capability2 = new Capability("Management");
        capability2.setEnvironment(environment);

        Capability capabilityCreated = capabilityService.createCapability(environmentId, capability1.getId(), capability2);
        Long capabilityCreatedId = capabilityCreated.getId();

        mvc.perform(get("/capabilities/{capId}", capabilityCreatedId).param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Management'}"));
    }

    @Test
    public void givenEnvironmentIdParentCapabilityIdCapabilityToCreate_whenCreateCapability_returnsCapabilityCreated() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth");
        capability.setEnvironment(environment);
        Capability capabilityParent = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityParentId = capabilityParent.getId();

        Capability capabilityToCreate = new Capability("Management");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(capabilityToCreate);

        

        mvc.perform(post("/capabilities").param("envId", environmentId.toString()).param("parentCapId", capabilityParentId.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Management'}"));
    }

    @Test
    public void givenEnvironmentIdCapabilityIdUpdatedCapability_whenUpdateCapability_returnsUpdatedCapability() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth");
        capability.setEnvironment(environment);
        Capability capabilityToUpdate = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityToUpdateId = capabilityToUpdate.getId();

        capabilityToUpdate.setName("Youth 2.0");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(capabilityToUpdate);

        

        mvc.perform(put("/capabilities/{capId}", capabilityToUpdateId).param("envId", environmentId.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Youth 2.0'}"));
    }

    @Test
    public void givenEnvironmentIdCapabilityId_whenDeleteCapability_returnsCapabilityDeleted() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long environmentId = environment.getId();

        Capability capability = new Capability("Youth");
        capability.setEnvironment(environment);
        Capability capabilityToDelete = capabilityService.createCapability(environmentId, null, capability);
        Long capabilityToDeleteId = capabilityToDelete.getId();

        mvc.perform(delete("/capabilities/{id}", capabilityToDeleteId).param("envId", environmentId.toString()))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }
    
}
