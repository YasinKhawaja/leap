
package edu.ap.group10.leapwebapp.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        Environment expectedEnvToBeCreated = new Environment(envName);

        given(eRepositoryUnderTest.save(expectedEnvToBeCreated)).willAnswer(invocation -> invocation.getArgument(0));

        // When
        Environment actualEnvCreated = eServiceUnderTest.createEnvironment(envName);

        // Then
        verify(eRepositoryUnderTest).save(any(Environment.class));

        assertEquals(expectedEnvToBeCreated.getName(), actualEnvCreated.getName());
    }

    @Test
    void updateEnvironment_GivenEnv_VerifyIfUpdated() {
        // Given

        // When

        // Then
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
