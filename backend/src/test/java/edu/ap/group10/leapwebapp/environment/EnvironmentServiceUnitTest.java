
package edu.ap.group10.leapwebapp.environment;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class EnvironmentServiceUnitTest {

    @Mock
    private EnvironmentRepository environmentRepositoryMock;
    // sut => system under test
    @InjectMocks
    private EnvironmentService sut;

    @Test
    void givenEnvironmentId_whenGetEnvironmentById_returnsEnvironmentFound() {
        // given
        String name = "Siemens";
        Environment environment = new Environment(name);
        environment.setId(1L);
        Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

        // When
        sut.getEnvironment(environment.getId());

        // Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(environmentRepositoryMock).findById(longArgumentCaptor.capture());

        assertEquals(longArgumentCaptor.getValue(), environment.getId());

    }

    @Test
    void givenThreeEnvironments_whenGetAllEnvironments_returnsAllEnvironments() {
        // When
        when(environmentRepositoryMock.findAll()).thenReturn(Arrays.asList(new Environment("EnvironmentTest0"),
                new Environment("EnvironmentTest1"), new Environment("EnvironmentTest2")));

        List<Environment> actualEnvsFound = sut.getAllEnvironments();

        // Then
        verify(environmentRepositoryMock).findAll();

        assertEquals("EnvironmentTest0", actualEnvsFound.get(0).getName());
        assertEquals("EnvironmentTest1", actualEnvsFound.get(1).getName());
        assertEquals("EnvironmentTest2", actualEnvsFound.get(2).getName());
    }

    @Test
    void givenEnvironmentName_whenCreateEnvironment_returnsEnvironmentCreated() {
        // Given
        String envName = "EnvironmentTest";
        ArgumentCaptor<Environment> aCaptor = ArgumentCaptor.forClass(Environment.class);

        // When
        sut.createEnvironment(envName);

        // Then
        verify(environmentRepositoryMock).save(aCaptor.capture());

        Environment actEnvToBeCreated = aCaptor.getValue();
        assertEquals(envName, actEnvToBeCreated.getName());
    }

    @Test
    void givenNewEnvironmentName_whenUpdateEnvironment_returnsEnvironmentNameIsUpdated() {
        // Given
        String name = "Siemens";
        Environment environment = new Environment(name);
        environment.setId(1L);
        Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

        String newName = "Philips";
        Long id = environment.getId();

        // When
        sut.updateEnvironment(id, newName);

        // Then
        ArgumentCaptor<Environment> environmentArgumentCaptor = ArgumentCaptor.forClass(Environment.class);
        verify(environmentRepositoryMock).save(environmentArgumentCaptor.capture());

        assertEquals(environmentArgumentCaptor.getValue().getName(), newName);
        // test if updated with new name
    }

    @Test
    void givenEnvironmentId_whenDeleteEnvironment_returnsEnvironmentDeleted() {
        // Given
        Long envIdToDelete = 1L;

        // When
        sut.deleteEnvironment(envIdToDelete);

        // Then
        verify(environmentRepositoryMock).deleteById(envIdToDelete);
    }

}
