package edu.ap.group10.leapwebapp.capability;

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
    CapabilityRepository cRepositoryUnderTest;
    
    @Autowired
    EnvironmentRepository eRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        // Fresh H2 DB before each test
        cRepositoryUnderTest.deleteAll();
    }

     @Test
    void findByEnv() {

       // Given
      Environment env = new Environment("EnvironmentTest");
       eRepositoryUnderTest.save(env);


      Capability expectedCaToReturn = new Capability("jan");
      expectedCaToReturn.setEnvironment(env);
       cRepositoryUnderTest.save(expectedCaToReturn);


     // When
      List<Capability> actualCaReturned = cRepositoryUnderTest.findByEnvironment(env);

      // Then
      assertEquals(expectedCaToReturn, actualCaReturned.get(0));
     }
    
}
