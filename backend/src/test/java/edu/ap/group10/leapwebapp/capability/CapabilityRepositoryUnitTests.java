package edu.ap.group10.leapwebapp.capability;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class CapabilityRepositoryUnitTests {

    @Autowired
    CapabilityRepository sut;
    
    @Autowired
    EnvironmentRepository environmentRespository;

    @BeforeEach
    void setUp() {
        // Fresh H2 DB before each test
        sut.deleteAll();
    }

     @Test
    void givenEnvironment_whenFindByEnvironment_returnsCapabilityFound() {

       // Given
      Environment env = new Environment("EnvironmentTest");
      environmentRespository.save(env);


      Capability capability = new Capability("jan");
      capability.setEnvironment(env);
      sut.save(capability);


     // When
      List<Capability> capabilities = sut.findByEnvironment(env);

      // Then
      assertEquals(capability.getId(), capabilities.get(0).getId());
      assertEquals(capability.getEnvironment().getId(), capabilities.get(0).getEnvironment().getId());
     }
    
}
*/