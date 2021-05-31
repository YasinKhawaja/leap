package edu.ap.group10.leapwebapp.capability;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class CapabilityServiceUnitTests {


    @Mock
    private CapabilityRepository capabilityRepositoryMock;

    @InjectMocks
    private CapabilityService sut;

    
    @Autowired
    EnvironmentRepository eRepositoryUnderTest;



    
     @Test
     void givenThreeCapabilities_whenGetAllCapabilities_returnsAllCapabilites() {
       // When
       when(capabilityRepositoryMock.findAll()).thenReturn(Arrays.asList(new Capability("test1"),
               new Capability("test2"), new Capability("test3")));

        List<Capability> actualCaFound = sut.getAllCapabilities();

       // Then
        verify(capabilityRepositoryMock).findAll();

        assertEquals("test1", actualCaFound.get(0).getName());
        assertEquals("test2", actualCaFound.get(1).getName());
        assertEquals("test3", actualCaFound.get(2).getName());
     }

    
}
