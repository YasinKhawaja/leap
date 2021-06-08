
package edu.ap.group10.leapwebapp.capabilityresource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import edu.ap.group10.leapwebapp.capability.Capability;
import edu.ap.group10.leapwebapp.resource.Resource;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class CapResourceServiceTests {

    @Mock
    private CapResourceRepository capResourceRepoMock;

    @InjectMocks
    private CapResourceService sut; // system under test

    @Test
    void GivenCapResources_WhenGetAllCapResources_ThenReturnAllCapResources() {
        // Given
        when(capResourceRepoMock.findAll()).thenReturn(Arrays.asList(new CapResource(), new CapResource()));
        // When
        List<CapResource> actual = sut.getAllCapResources();
        // Then
        assertEquals(2, actual.size());
    }

    @Test
    void GivenCapResources_WhenGetAllCapResourcesByCapabilityId_ThenReturnAllCapResourcesWithSameCapabilityId() {
        // Given
        Capability capability = new Capability();
        capability.setId(1L);

        when(capResourceRepoMock.findByCapabilityId(1L)).thenReturn(Arrays.asList(
                new CapResource(capability, new Resource(), 1), new CapResource(capability, new Resource(), 1)));
        // When
        List<CapResource> actual = sut.getAllCapResourcesByCapabilityId(1L);
        // Then
        assertEquals(2, actual.size());
    }

    @Test
    void GivenCapResources_WhenGetAllCapResourcesByResourceId_ThenReturnAllCapResourcesWithSameResourceId() {
        // Given
        Resource resource = new Resource();
        resource.setId(1L);

        when(capResourceRepoMock.findByResourceId(1L)).thenReturn(Arrays.asList(
                new CapResource(new Capability(), resource, 1), new CapResource(new Capability(), resource, 1)));
        // When
        List<CapResource> actual = sut.getAllCapResourcesByResourceId(1L);
        // Then
        assertEquals(2, actual.size());
    }

}
