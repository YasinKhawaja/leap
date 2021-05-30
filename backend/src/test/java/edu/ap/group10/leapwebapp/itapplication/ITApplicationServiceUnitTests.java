package edu.ap.group10.leapwebapp.itapplication;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class ITApplicationServiceUnitTests {

    @Mock
    private ITApplicationRepository itApplicationRepository;

    @InjectMocks
    private ITApplicationService itApplicationService;

    @Test
    void givenEnvironmentId_whenGetITAppllications_returnsListITApplications(){
        
        //Given
        Long environmentId = 1L;
        String environmentName = "Test environment";

        Environment environment = new Environment(environmentName);
        environment.setId(environmentId);
        //Could put it applications in a list and see if it returns the same list

        //When
        when(itApplicationService.getITApplications(environment.getId().toString())).thenReturn(Arrays.asList(new ITApplication("Test Application", "Mockito", environment)
        , new ITApplication("Test2", "Java", environment)));
        List<ITApplication> itApplications = itApplicationService.getITApplications(environmentId.toString());

        //Then
        assertEquals("Test Application", itApplications.get(0).getName());
        assertEquals("Mockito", itApplications.get(0).getTechnology());
        assertEquals("Test2", itApplications.get(1).getName());
        assertEquals("Java", itApplications.get(1).getTechnology());

        assertEquals(environmentName, itApplications.get(0).getEnvironment().getName());
        assertEquals(environmentId, itApplications.get(0).getEnvironment().getId());
        assertEquals(environmentName, itApplications.get(1).getEnvironment().getName());
        assertEquals(environmentId, itApplications.get(1).getEnvironment().getId());
    }
    
    @Test
    void givenApplicationName_whenFindITApplicationByName_returnsITApplication(){
        
        //Given
        String applicationName = "test";
        ITApplication itApplication = new ITApplication(applicationName, "tech", new Environment("environment"));

        //When
        when(itApplicationService.findITApplicationByName(itApplication.getName())).thenReturn(itApplication);
        ITApplication foundITApplication = itApplicationService.findITApplicationByName(applicationName);

        //Then
        assertEquals(applicationName, foundITApplication.getName());
        assertEquals(itApplication, foundITApplication);
    }
    
    @Test
    void givenApplicationId_whenFindITApplication_returnsITApplication(){

        //Given
        Long applicationId = 10L;
        ITApplication itApplication = new ITApplication("test", "tech", new Environment("test"));
        itApplication.setId(applicationId);
        when(itApplicationRepository.findById(applicationId)).thenReturn(Optional.of(itApplication));

        //When
        ITApplication foundITApplication = itApplicationService.findITApplication(applicationId);

        //Then
        ArgumentCaptor<Long> argCap = ArgumentCaptor.forClass(Long.class);
        verify(itApplicationRepository).findById(argCap.capture());

        assertEquals(applicationId, argCap.getValue());
        assertEquals(itApplication, foundITApplication);
    }

    @Test
    void givenITApplication_whenCreateITApplication_returnsITApplication(){

        //Given
        ITApplication itApplication = new ITApplication("test", "tech", new Environment("test"));
        ArgumentCaptor<ITApplication> argCap = ArgumentCaptor.forClass(ITApplication.class);

        //When
        itApplicationService.createITApplication(itApplication);

        //Then
        verify(itApplicationRepository).save(argCap.capture());

        assertEquals(itApplication.getId(), argCap.getValue().getId());
        assertEquals(itApplication, argCap.getValue());
    }

    @Test
    void givenITApplication_ITApplicationId_whenUpdateITApplication_returnsITApplication(){

        //Given
        Long itApplicationId = 5L;
        ITApplication oldITApp = new ITApplication("test", "test", new Environment("test"));
        oldITApp.setId(itApplicationId);
        when(itApplicationRepository.findById(itApplicationId)).thenReturn(Optional.of(oldITApp));

        ITApplication newITApp = new ITApplication("app name", "app tech", "app version", "2012.05.30", "2012.05.30", 0, 0, 0, 0, 0, 0, 0, 0, "EUR", 0, 0.0, 0.0, TimeValue.ELIMINATE, new Environment("test"));

        //When
        itApplicationService.updateITApplication(itApplicationId, newITApp);
        ArgumentCaptor<ITApplication> argCap = ArgumentCaptor.forClass(ITApplication.class);

        //Then
        verify(itApplicationRepository).save(argCap.capture());
        assertEquals(oldITApp.getId(), argCap.getValue().getId());
        assertEquals(newITApp.getName(), argCap.getValue().getName());
    }

    @Test
    void givenITApplicationId_whenDeleteITApplication_returnsITApplicationDeleted(){

        //Given
        Long itApplicationId = 5L;
        ITApplication itApplication = new ITApplication("test", "test", new Environment("test"));
        when(itApplicationRepository.findById(itApplicationId)).thenReturn(Optional.of(itApplication));

        //When
        Boolean deleted = itApplicationService.deleteITApplication(itApplicationId);

        //Then
        verify(itApplicationRepository).delete(itApplication);
        assertEquals(true, deleted);
    }
    
}
