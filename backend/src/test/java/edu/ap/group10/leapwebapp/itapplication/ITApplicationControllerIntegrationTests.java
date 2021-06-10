package edu.ap.group10.leapwebapp.itapplication;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ITApplicationControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ITApplicationService itApplicationService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");

    @BeforeEach
    void setup() {
        company.setId(1L);
        companyRepository.save(company);

        final Environment environment = new Environment("Test environment", company);
        environment.setId(2L);
        environmentRepository.save(environment);

        final ITApplication itapplicationA = new ITApplication("test A", "tech", environment);
        final ITApplication itapplicationB = new ITApplication("test B", "tech", environment);
        itapplicationA.setId(3L);
        itapplicationB.setId(4L);

        itApplicationService.createITApplication(itapplicationA);
        itApplicationService.createITApplication(itapplicationB);
    }

    @Test
    @WithMockUser
    @Order(1)
    void givenEnvironmentID_whenGetAllITApplications_returnsITApplications() throws Exception {
        // Given
        String environmentid = "2";

        // When
        mvc.perform(get("/itapplications/{environmentId}", environmentid))

                // Then
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().json("[{'name':'test A'}, {'name':'test B'}]"));
    }

    @Test
    @WithMockUser
    @Order(2)
    void givenEnvironmentID_ITApplication_whenAddITApplication_returnsIsOK() throws Exception {
        // Given
        String environmentid = "2";
        ITApplication itapplicationc = new ITApplication("test C", "tech", null);
        itapplicationc.setId(5L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(itapplicationc);

        // When
        mvc.perform(post("/itapplications/{environmentId}", environmentid).with(csrf())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson))

                // Then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @Order(3)
    void givenITApplicationID_ITApplication_whenupdateITApplication_returnsIsOK() throws Exception {
        // Given
        String applicationid = "3";
        ITApplication itapplicationc = new ITApplication("test D", "tech", null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(itapplicationc);

        // When
        mvc.perform(put("/itapplications/{applicationId}", applicationid).with(csrf())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson))

                // Then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @Order(4)
    void givenITApplicationID_whenDeleteITApplication_returnsIsOk() throws Exception {
        // Given
        Long applicationid = 3L;

        // When
        mvc.perform(delete("/itapplications/{applicationId}", applicationid).with(csrf()))

                // Then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @Order(5)
    void givenITApplicationID_whenGetITApplication_returnsITApplication() throws Exception {
        // Given
        String applicationid = "3";

        // When
        mvc.perform(get("/itapplication/{applicationId}", applicationid))

                // Then
                .andExpect(status().isOk()).andDo(print()).andExpect(content().json("{'name':'test A'}"));
    }
}
