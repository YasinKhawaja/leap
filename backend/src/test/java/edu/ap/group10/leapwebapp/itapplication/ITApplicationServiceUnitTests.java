package edu.ap.group10.leapwebapp.itapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class ITApplicationServiceUnitTests {

    @Mock
    private ITApplicationRepository itApplicationRepository;

    @InjectMocks
    private ITApplicationService sut;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenEnvironmentId_whenGetITAppllications_returnsListITApplications() {

        // Given
        Long environmentId = 1L;
        environment.setId(environmentId);
        // Could put it applications in a list and see if it returns the same list

        // When
        when(itApplicationRepository.findAll())
                .thenReturn(Arrays.asList(new ITApplication("Test Application", "Mockito", environment),
                        new ITApplication("Test2", "Java", environment)));
        List<ITApplication> itApplications = sut.getITApplications(environmentId.toString());

        // Then
        assertEquals("Test Application", itApplications.get(0).getName());
        assertEquals("Mockito", itApplications.get(0).getTechnology());
        assertEquals("Test2", itApplications.get(1).getName());
        assertEquals("Java", itApplications.get(1).getTechnology());

        assertEquals(environmentId, itApplications.get(0).getEnvironment().getId());
        assertEquals(environmentId, itApplications.get(1).getEnvironment().getId());

        assertEquals(2, itApplications.size());
    }

    @Test
    void givenApplicationName_whenFindITApplicationByName_returnsITApplication() {

        // Given
        String applicationName = "test";
        ITApplication itApplication = new ITApplication(applicationName, "tech", environment);

        // When
        when(itApplicationRepository.findByName(itApplication.getName())).thenReturn(itApplication);
        ITApplication foundITApplication = sut.findITApplicationByName(applicationName);

        // Then
        assertEquals(applicationName, foundITApplication.getName());
        assertEquals(itApplication, foundITApplication);
    }

    @Test
    void givenApplicationId_whenFindITApplication_returnsITApplication() {

        // Given
        Long applicationId = 5L;
        ITApplication itApplication = new ITApplication("test", "tech", environment);
        itApplication.setId(applicationId);
        when(itApplicationRepository.findById(applicationId)).thenReturn(Optional.of(itApplication));

        // When
        ITApplication foundITApplication = sut.findITApplication(applicationId);

        // Then
        ArgumentCaptor<Long> argCap = ArgumentCaptor.forClass(Long.class);
        verify(itApplicationRepository).findById(argCap.capture());

        assertEquals(applicationId, argCap.getValue());
        assertEquals(itApplication, foundITApplication);
    }

    @Test
    void givenITApplication_whenCreateITApplication_returnsITApplication() {

        // Given
        ITApplication itApplication = new ITApplication("test", "tech", environment);
        ArgumentCaptor<ITApplication> argCap = ArgumentCaptor.forClass(ITApplication.class);

        // When
        sut.createITApplication(itApplication);

        // Then
        verify(itApplicationRepository).save(argCap.capture());

        assertEquals(itApplication.getId(), argCap.getValue().getId());
        assertEquals(itApplication, argCap.getValue());
    }

    @Test
    void givenITApplication_ITApplicationId_whenUpdateITApplication_returnsITApplication() {

        // Given
        Long itApplicationId = 5L;
        ITApplication oldITApp = new ITApplication("test", "test", environment);
        oldITApp.setId(itApplicationId);
        when(itApplicationRepository.findById(itApplicationId)).thenReturn(Optional.of(oldITApp));

        ITApplication newITApp = new ITApplication("app name", "app tech", "app version", "2012.05.30", "2012.05.30", 0,
                0, 0, 0, 0, 0, 0, 0, "EUR", 0, 0.0, 0.0, "eliminate", environment);

        // When
        sut.updateITApplication(itApplicationId, newITApp);
        ArgumentCaptor<ITApplication> argCap = ArgumentCaptor.forClass(ITApplication.class);

        // Then
        verify(itApplicationRepository).save(argCap.capture());
        assertEquals(oldITApp.getId(), argCap.getValue().getId());
        assertEquals(newITApp.getName(), argCap.getValue().getName());
    }

    @Test
    void givenITApplicationId_whenDeleteITApplication_returnsITApplicationDeleted() {

        // Given
        Long itApplicationId = 5L;
        ITApplication itApplication = new ITApplication("test", "test", environment);
        when(itApplicationRepository.existsById(itApplicationId)).thenReturn(false);
        when(itApplicationRepository.findById(itApplicationId)).thenReturn(Optional.of(itApplication));

        // When
        Boolean deleted = sut.deleteITApplication(itApplicationId);

        // Then
        verify(itApplicationRepository).delete(itApplication);
        assertFalse(deleted);
    }

    @Test
    void givenITApplicationId_whenDeleteITApplication__throwsResourceNotFoundException() {

        // Given
        Long itApplicationId = 5L;

        // When
        when(itApplicationRepository.findById(5L)).thenThrow(new ResourceNotFoundException());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> sut.deleteITApplication(itApplicationId));
    }

    @Test
    void givenITApplicaitonid_whenDeleteITApplication_returnsITApplicationNotDeleted() {
        // Given
        Long itapplicationid = 5L;
        ITApplication itApplication = new ITApplication("test", "test", environment);
        ITApplication itApplication2 = new ITApplication("test", "test", environment);
        itApplication.setId(itapplicationid);

        // When
        when(itApplicationRepository.existsById(itapplicationid)).thenReturn(true);
        when(itApplicationRepository.findById(itapplicationid)).thenReturn(Optional.of(itApplication2));
        Boolean deleted = sut.deleteITApplication(itapplicationid);

        // Then
        assertTrue(deleted);

    }

    @Test
    void givenITApplicationId_whenGetITApplication_returnsITApplication() {

        // Given
        Long itapplicationid = 5L;
        ITApplication itApplication = new ITApplication("test", "test", environment);

        // When
        when(itApplicationRepository.findById(itapplicationid)).thenReturn(Optional.of(itApplication));

        // Then
        ITApplication actualApplication = sut.getITApplication(itapplicationid);

        assertEquals(itApplication, actualApplication);
    }

    @Test
    void givenITApplicaitonid_whenGetITApplication_throwsResourceNotFoundException() {
        // Given
        Long itapplicationid = 5L;

        // When
        when(itApplicationRepository.findById(5L)).thenThrow(new ResourceNotFoundException());

        // Then
        assertThrows(ResourceNotFoundException.class, () -> sut.getITApplication(itapplicationid));
    }
}