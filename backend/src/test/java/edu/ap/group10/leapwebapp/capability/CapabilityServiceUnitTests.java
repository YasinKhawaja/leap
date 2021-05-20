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

import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceUnitTests {


    @Mock
    private CapabilityRepository cRepositoryUnderTest;

    @InjectMocks
    private CapabilityService cServiceUnderTest;

    
    @Autowired
    EnvironmentRepository eRepositoryUnderTest;



    
    // @Test
    // void getCapability_InDb_ReturnCap() {
    //     // When
    //     when(cRepositoryUnderTest.findAll()).thenReturn(Arrays.asList(new Capability("test1",PaceOfChange.DIFFERENTIATION,Tom.COORDINATION,2),
    //             new Capability("test2",PaceOfChange.INNOVATIVE,Tom.DIVERSIFICATION,2), new Capability("test3",PaceOfChange.STANDARD,Tom.REPLICATION,2)));

    //     List<Capability> actualCaFound = cServiceUnderTest.getCapabilities();

    //     // Then
    //     verify(cRepositoryUnderTest).findAll();

    //     assertEquals("test1", actualCaFound.get(0).getName());
    //     assertEquals("test2", actualCaFound.get(1).getName());
    //     assertEquals("test3", actualCaFound.get(2).getName());
    // }

    
}
