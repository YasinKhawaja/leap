
package edu.ap.group10.leapwebapp.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class ResourceServiceTests {

    @Mock
    private EnvironmentRepository environmentRepoMock;

    @Mock
    private ResourceRepository resourceRepoMock;

    @InjectMocks
    private ResourceService sut; // system under test

    @Captor
    private ArgumentCaptor<Resource> argumentCaptor;

    private static Environment environment;

    @BeforeEach
    void setUp() {
        environment = new Environment("Env", null);
        environment.setId(1L);
    }

    @Test
    void GivenResources_WhenGetAllResourcesInEnvironment_ThenReturnAllResourcesInEnvironment() {
        // Given
        when(resourceRepoMock.findAll())
                .thenReturn(Arrays.asList(new Resource("", "", 1, environment), new Resource("", "", 1, environment)));
        // When
        List<Resource> actual = sut.getAllResourcesInEnvironment(1L);
        // Then
        assertEquals(2, actual.size());
    }

    @Test
    void GivenResource_WhenCreateResource_ThenCreateResource() {
        // Given
        Resource resource = new Resource("Res", "", 1, null);
        when(environmentRepoMock.findById(1L)).thenReturn(Optional.of(environment));
        // When
        sut.createResource(1L, resource);
        // Then
        verify(resourceRepoMock).save(argumentCaptor.capture());
        Resource resourceCaptured = argumentCaptor.getValue();
        assertEquals(1L, resourceCaptured.getEnvironment().getId());
    }

    @Test
    void GivenResourceIdAndNewValues_WhenUpdateResource_ThenUpdateResource() {
        // Given
        Resource resourceToUpdate = new Resource("Test", "Desc", 1, environment);
        Resource resourceNewValues = new Resource("UpdatedTest", "UpdatedDesc", 10, null);
        when(resourceRepoMock.findById(1L)).thenReturn(Optional.of(resourceToUpdate));
        // When
        sut.updateResource(1L, resourceNewValues);
        // Then
        verify(resourceRepoMock).save(argumentCaptor.capture());
        Resource resourceCaptured = argumentCaptor.getValue();
        assertEquals("UpdatedTest", resourceCaptured.getName());
        assertEquals(1L, resourceCaptured.getEnvironment().getId());
    }

    @Test
    void GivenResourceId_WhenDeleteResource_ThenDeleteResource() {
        // Given
        Long resourceId = 1L;
        // When
        sut.deleteResource(resourceId);
        // Then
        verify(resourceRepoMock).deleteById(resourceId);
    }

}
