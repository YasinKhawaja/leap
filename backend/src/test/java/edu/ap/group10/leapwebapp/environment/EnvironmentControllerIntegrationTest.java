
package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EnvironmentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate trTemplate;

    @Test
    void given2Envs_whenGetAllEnvironments_thenReturnOk() {
        ResponseEntity<Environment[]> rEntity = trTemplate.getForEntity("api/environments", Environment[].class);
        assertEquals(HttpStatus.OK, rEntity.getStatusCode());
    }

}
