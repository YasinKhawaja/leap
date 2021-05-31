package edu.ap.group10.leapwebapp.capability;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class CapabilityServiceUnitTests {


    @Mock
    private CapabilityRepository capabilityRepositoryMock;

    @Mock
    private EnvironmentRepository environmentRepositoryMock;

    @InjectMocks
    private CapabilityService sut;
    
    @Test
    void givenCapabilities_whenGetAllCapabilities_returnsAllCapabilites() {
       // When
       when(capabilityRepositoryMock.findAll()).thenReturn(Arrays.asList(new Capability("test1"),
               new Capability("test2"), new Capability("test3")));

        List<Capability> capabilities = sut.getAllCapabilities();

       // Then
        verify(capabilityRepositoryMock).findAll();

        assertEquals("test1", capabilities.get(0).getName());
        assertEquals("test2", capabilities.get(1).getName());
        assertEquals("test3", capabilities.get(2).getName());
    }

    @Test
    void givenEnvironmentId_whenGetAllCapabilitiesInEnvironment_returnsCapabilitiesInEnvironment() {
      //given 
      String name = "Siemens";
      Environment environment = new Environment(name);
      environment.setId(1L);
      Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

      Capability capability1 = new Capability("Youth");
      capability1.setEnvironment(environment);
      Capability capability2 = new Capability("Trainers");
      capability2.setEnvironment(environment);
      Mockito.when(capabilityRepositoryMock.findByEnvironment(environment)).thenReturn(Arrays.asList(capability1,capability2));

      //when
      List<Capability> capabilities = sut.getAllCapabilitiesInEnvironment(environment.getId());

      //then
      ArgumentCaptor<Environment> environmentArgumentCaptor = ArgumentCaptor.forClass(Environment.class);
      verify(capabilityRepositoryMock).findByEnvironment(environmentArgumentCaptor.capture());

      assertEquals(capability1.getEnvironment().getId(), capabilities.get(0).getEnvironment().getId());
      assertEquals(capability2.getEnvironment().getId(), capabilities.get(1).getEnvironment().getId());

    }

    @Test
    void givenEnvironmetId_whenGetACapabilityInEnvironment_returnsCapabilityInEnvironment() {

      //given

    }

    @Test
    void givenCapabilityId_whenGetCapabilityById_returnsCapability() {

      //given
      Capability capability = new Capability("Youth");
      capability.setId(1L);
      Mockito.when(capabilityRepositoryMock.findById(1L)).thenReturn(Optional.of(capability));

      //when
      sut.getCapabilityById(capability.getId());

      //then
      ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
      verify(capabilityRepositoryMock).findById(longArgumentCaptor.capture());

      assertEquals(longArgumentCaptor.getValue(), capability.getId());
    }

    
}
