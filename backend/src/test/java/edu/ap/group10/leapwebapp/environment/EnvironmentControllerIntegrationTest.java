package edu.ap.group10.leapwebapp.environment;
/*
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
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class EnvironmentControllerIntegrationTest {

    @Autowired 
    private MockMvc mvc;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @AfterEach
    void tearDown(){
        environmentRepository.deleteAll();
    }

    @Test
    public void givenEnvironments_whenGetAllEnvironments_returnsEnvironmentsArePresent() throws Exception{
        environmentService.createEnvironment("Siemens");
        environmentService.createEnvironment("Philips");

        mvc.perform(get("/environments"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("[{'name':'Siemens'}, {'name':'Philips'}]"));
    }

    @Test
    public void givenEnvironmentId_whenGetEnvironment_returnsEnvironment() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long Id = environment.getId();

        mvc.perform(get("/environments/{id}",Id))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));
    }

    @Test
    public void givenEnvironment_whenCreateEnvironment_returnsEnvironmentSaved() throws Exception {

        mvc.perform(post("/environments").param("name", "Siemens"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));
    }

    @Test
    public void givenNewEnvironmentName_whenUpdateEnvironment_returnsEnvironmentUpdatedWithNewName() throws Exception {
        Environment environment = environmentService.createEnvironment("Philips");
        Long Id = environment.getId();

        mvc.perform(put("/environments/{id}",Id).param("name", "Siemens"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().json("{'name':'Siemens'}"));

    }

    @Test
    public void givenEnvironmentId_whenDeleteEnvironment_returnsEnvironmentDeleted() throws Exception {
        Environment environment = environmentService.createEnvironment("Siemens");
        Long Id = environment.getId();

        mvc.perform(delete("/environments/{id}", Id))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(""));
    }
    
}
*/