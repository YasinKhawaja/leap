package edu.ap.group10.leapwebapp.businessprocess;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcess;
import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcessRepository;
import edu.ap.group10.leapwebapp.businessprocesses.BusinessProcessService;
import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.environment.Environment;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class BusinessProcessServiceUnitTests {

    @Mock
    private BusinessProcessRepository businessProcessRepositoryMock;

    @InjectMocks
    private BusinessProcessService sut;

    private static final Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5,
            "Mortsel", "België", "", "");
    private Environment environment = new Environment("Test environment", company);

    @Test
    void givenEnvironmentId_whenGetAllBusinessProcessesEnvironment_returnsListBusinessProcesses() {

        // given
        Long environmentId = 1L;
        environment.setId(environmentId);

        // when
        when(businessProcessRepositoryMock.findAll())
                .thenReturn(Arrays.asList(new BusinessProcess("Sales", "Income of the year 2021", environment),
                        new BusinessProcess("HR", "Amount of staff", environment)));
        List<BusinessProcess> businessProcesses = sut.getAllBusinessProcessesEnvironment(environmentId.toString());

        // then
        assertEquals("Sales", businessProcesses.get(0).getName());
        assertEquals("Income of the year 2021", businessProcesses.get(0).getDescription());
        assertEquals("HR", businessProcesses.get(1).getName());
        assertEquals("Amount of staff", businessProcesses.get(1).getDescription());

        assertEquals(environmentId, businessProcesses.get(0).getEnvironment().getId());
        assertEquals(environmentId, businessProcesses.get(1).getEnvironment().getId());

        assertEquals(2, businessProcesses.size());

    }

    @Test
    void givenBusinessProcess_whenAddBusinessProcess_returnsBusinessProcessSaved() {
        // given
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);
        ArgumentCaptor<BusinessProcess> argumentCaptor = ArgumentCaptor.forClass(BusinessProcess.class);

        // when
        sut.addBusinessProcess(businessProcess);

        // then
        verify(businessProcessRepositoryMock).save(argumentCaptor.capture());

        assertEquals(businessProcess.getId(), argumentCaptor.getValue().getId());
        assertEquals(businessProcess.getName(), argumentCaptor.getValue().getName());
        assertEquals(businessProcess, argumentCaptor.getValue());

    }

    @Test
    void givenBusinessProcess_whenUpdateBusinessProcess_returnsUpdatedBusinessProcess() {

        // given
        Long businessProcessId = 5L;
        BusinessProcess businessProcessOld = new BusinessProcess("Sales", "Income of the year 2021", environment);
        businessProcessOld.setId(businessProcessId);
        when(businessProcessRepositoryMock.findById(businessProcessId)).thenReturn(Optional.of(businessProcessOld));

        BusinessProcess businessProcessNew = new BusinessProcess("HR", "Amount of staff", environment);

        // when
        sut.updateBusinessProcess(businessProcessId, businessProcessNew);
        ArgumentCaptor<BusinessProcess> argumentCaptor = ArgumentCaptor.forClass(BusinessProcess.class);

        // then
        verify(businessProcessRepositoryMock).save(argumentCaptor.capture());

        assertEquals(businessProcessNew.getName(), argumentCaptor.getValue().getName());

    }

    @Test
    void givenBusinessProcess_whenDeleteBusinessProcess_returnsBusinessProcessDeleted() {

        // given
        Long businessProcessId = 5L;
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);
        when(businessProcessRepositoryMock.findById(businessProcessId)).thenReturn(Optional.of(businessProcess));

        //when
        sut.deleteBusinessProcess(businessProcessId);
        ArgumentCaptor<BusinessProcess> argumentCaptor = ArgumentCaptor.forClass(BusinessProcess.class);

        //then
        verify(businessProcessRepositoryMock).delete(argumentCaptor.capture());

        assertEquals(businessProcess.getName(), argumentCaptor.getValue().getName());

    }

    @Test
    void givenBusinessProcess_whenGetBusinessProcess_returnsBusinessProcess() {

        // given
        Long businessProcessId = 5L;
        BusinessProcess businessProcess = new BusinessProcess("Sales", "Income of the year 2021", environment);
        when(businessProcessRepositoryMock.findById(businessProcessId)).thenReturn(Optional.of(businessProcess));

        //when
        BusinessProcess businessProcessFound = sut.getBusinessProcess(businessProcessId);
        
        //then
        assertEquals(businessProcess, businessProcessFound);

    }

}
