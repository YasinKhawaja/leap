
package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnvironmentServiceUnitTest {

    @Mock
    private EnvironmentRepository eRepositoryUnderTest;

    @InjectMocks
    private EnvironmentService eServiceUnderTest;
    
    @Test
    void canGetAllEnvironments(){
        // When
        eServiceUnderTest.getAllEnvironments();

        // Then
        verify(eRepositoryUnderTest).findAll();
    }

    @Test
    void existsByName() {
        // given
        String name = "Siemens";
        Environment environment = new Environment(name);
        eRepositoryUnderTest.save(environment);

        // when
        eServiceUnderTest.existsByName(name);

        // then
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        
        verify(eRepositoryUnderTest).existsByName(stringArgumentCaptor.capture());

        String capturedName = stringArgumentCaptor.getValue();

        assertEquals(capturedName, name);
    }

    @Test
    void getEnvironments_3EnvsInDb_Return3Envs() {
        // When
        when(eRepositoryUnderTest.findAll()).thenReturn(Arrays.asList(new Environment("EnvironmentTest0"),
                new Environment("EnvironmentTest1"), new Environment("EnvironmentTest2")));

        List<Environment> actualEnvsFound = eServiceUnderTest.getAllEnvironments();

        // Then
        verify(eRepositoryUnderTest).findAll();

        assertEquals("EnvironmentTest0", actualEnvsFound.get(0).getName());
        assertEquals("EnvironmentTest1", actualEnvsFound.get(1).getName());
        assertEquals("EnvironmentTest2", actualEnvsFound.get(2).getName());
    }

    @Test
    void createEnvironment_GivenEnv_VerifyIfCreated() {
        // Given
        String envName = "EnvironmentTest";
        ArgumentCaptor<Environment> aCaptor = ArgumentCaptor.forClass(Environment.class);

        // When
        eServiceUnderTest.createEnvironment(envName);

        // Then
        verify(eRepositoryUnderTest).save(aCaptor.capture());

        Environment actEnvToBeCreated = aCaptor.getValue();
        assertEquals(envName, actEnvToBeCreated.getName());
    }

    // Error : java.util.NoSuchElementException: No value present
	// at java.base/java.util.Optional.orElseThrow(Optional.java:375)
    @Test
    void updateEnvironment_GivenEnv_VerifyIfUpdated() {
        // Given
        Long id = (long) 1;
        String name = "Siemens";

        // When
        eServiceUnderTest.updateEnvironment(id, name);

        // Then
        ArgumentCaptor<Environment> environmentArgumentCaptor = ArgumentCaptor.forClass(Environment.class);
        verify(eRepositoryUnderTest).save(environmentArgumentCaptor.capture());
    }

    @Test
    void deleteEnvironment_VerifyIfDeleted() {
        // Given
        Long envIdToDelete = 1L;

        // When
        eServiceUnderTest.deleteEnvironment(envIdToDelete);

        // Then
        verify(eRepositoryUnderTest).deleteById(envIdToDelete);
    }

}
