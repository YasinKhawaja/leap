
package edu.ap.group10.leapwebapp.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class ResourceServiceTests {

    @Mock
    private ResourceRepository resRepoMock;

    @InjectMocks
    private ResourceService sut; // system under test

    @Test // positive
    void GivenThreeResources_WhenGetAllResources_ReturnsAllResources() {
        // Given
        when(resRepoMock.findAll()).thenReturn(Arrays.asList(new Resource(), new Resource(), new Resource()));

        // When
        List<Resource> actualResFound = sut.getAllResources();

        // Then
        assertEquals(3, actualResFound.size());
    }

    @Test // positive
    void GivenResource_WhenGetResource_ReturnsResource() {
        // Given
        Resource expRes = new Resource();
        expRes.setId(1L);

        when(resRepoMock.findById(1L)).thenReturn(Optional.of(expRes));

        // When
        Resource actRes = sut.getResource(1L);

        // Then
        assertEquals(1L, actRes.getId());
    }

    @Test // negative
    void GivenResource_WhenGetResource_ThrowsException() {
        // Given
        Exception expExc = new NoSuchElementException();

        // When
        when(resRepoMock.findById(null)).thenThrow(NoSuchElementException.class);

        // Then
        assertThrows(expExc.getClass(), () -> sut.getResource(null));
    }

    @Test // positive
    void GivenResource_WhenCreateResource_ReturnsCreatedResource() {
        // Given
        Resource expRes = new Resource();

        when(resRepoMock.save(expRes)).thenReturn(expRes);

        // When
        Resource actRes = sut.createResource(expRes);

        // Then
        assertEquals(expRes, actRes);
    }

    @Test // negative
    void GivenNull_WhenCreateResource_ThrowException() {
        // Given
        Exception expExc = new IllegalArgumentException();

        // When
        when(resRepoMock.save(null)).thenThrow(IllegalArgumentException.class);

        // Then
        assertThrows(expExc.getClass(), () -> sut.createResource(null));
    }

    @Test // positive
    void GivenResourceIdAndNewValues_WhenUpdateResource_ReturnsUpdatedResource() {
        // Given
        Long id = 1L;
        Resource res = new Resource("Name", "Desc", 1);

        Resource expRes = new Resource("NewName", "NewDesc", 10);

        when(resRepoMock.findById(id)).thenReturn(Optional.of(res));
        when(resRepoMock.save(expRes)).thenReturn(expRes);

        // When
        Resource actRes = sut.updateResource(id, expRes);

        // Then
        verify(resRepoMock).save(expRes);
        assertEquals(expRes, actRes);
    }

    @Test // negative
    void GivenNull_WhenUpdateResource_ThrowsException() {
        // Given
        Exception expExc = new NoSuchElementException();

        // When
        when(resRepoMock.findById(null)).thenThrow(NoSuchElementException.class);

        // Then
        assertThrows(expExc.getClass(), () -> sut.updateResource(null, null));
    }

    @Test // positive
    void GivenResourceId_WhenDeleteResource_VerifyDeletedById() {
        // Given
        Long expResId = 1L;

        // When
        sut.deleteResource(expResId);

        // Then
        verify(resRepoMock).deleteById(expResId);
    }

    @Test // negative
    void GivenNull_WhenDeleteResource_ThrowsException() {
        // Given
        Exception expExc = new IllegalArgumentException();

        // When
        doThrow(IllegalArgumentException.class).when(resRepoMock).deleteById(null);

        // Then
        assertThrows(expExc.getClass(), () -> sut.deleteResource(null));
    }

}
