
package edu.ap.group10.leapwebapp.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc(addFilters = false)
class ResourceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    private static Company company; // 1L
    private static Environment environment; // 2L

    @BeforeEach
    void setUp() {
        company = new Company("", "", "", "", 1, 1, "", "", "", "");
        companyRepository.save(company);

        environment = new Environment("Env", company);
        environmentRepository.save(environment);
    }

    @Test
    void GivenParam_WhenGetAllResources_ThenReturnAllResources() throws Exception {
        // Given
        resourceRepository.save(new Resource("ResA", "", 1, environment));
        resourceRepository.save(new Resource("ResB", "", 2, environment));
        // When
        mockMvc.perform(get("/resources").param("environmentId", "2"))
                // Then
                .andExpect(status().isOk()).andExpect(content().json("[{\"name\":\"ResA\"}, {\"name\":\"ResB\"}]"));
    }

    @Test
    void GivenParamAndBody_WhenCreateResource_ThenCreateResource() throws Exception {
        // Given
        Resource resource = new Resource("Test", "", 1, null);
        resource.setId(3L);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(resource);
        // When
        mockMvc.perform(post("/resources").param("environmentId", "2").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        // Then
        Resource resourceCreated = resourceRepository.findById(resource.getId()).orElseThrow();
        assertEquals(3L, resourceCreated.getId());
    }

    @Test
    void GivenResourceIdAndBody_WhenUpdateResource_ThenUpdateResource() throws Exception {
        // Given
        Resource resourceToUpdate = new Resource("Test", "", 1, environment);
        resourceToUpdate.setId(3L);
        resourceRepository.save(resourceToUpdate);

        Resource resourceToUpdateNewValues = new Resource("UpdatedTest", "UpdatedDesc", 10, null);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(resourceToUpdateNewValues);
        // Then1
        Resource resourceToUpdateRepo = resourceRepository.findById(resourceToUpdate.getId()).orElseThrow();
        assertEquals("Test", resourceToUpdateRepo.getName());
        // When
        mockMvc.perform(put("/resources/" + resourceToUpdate.getId().toString()).content(requestBody)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        // Then2
        Resource resourceUpdated = resourceRepository.findById(resourceToUpdate.getId()).orElseThrow();
        assertEquals("UpdatedTest", resourceUpdated.getName());
    }

    @Test
    void GivenResourceId_WhenDeleteResource_ThenDeleteResource() throws Exception {
        // Given
        Resource resource = new Resource("Test", "", 1, environment);
        resource.setId(3L);
        resourceRepository.save(resource);
        // Then1
        Resource resourceRepo = resourceRepository.findById(3L).orElseThrow();
        assertEquals("Test", resourceRepo.getName());
        // When
        mockMvc.perform(delete("/resources/" + resource.getId().toString())).andExpect(status().isOk());
        // Then2
        Optional<Resource> resourceDeletedRepo = resourceRepository.findById(3L);
        assertEquals(Optional.empty(), resourceDeletedRepo);
    }

}
