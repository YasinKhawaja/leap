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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import edu.ap.group10.leapwebapp.company.Company;
import edu.ap.group10.leapwebapp.company.CompanyRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class EnvironmentServiceUnitTest {

    @Mock
    private CompanyRepository companyRepositoryMock;

    @Mock
    private EnvironmentRepository environmentRepositoryMock;
    // sut => system under test
    @InjectMocks
    private EnvironmentService sut;

    @Test
    void givenEnvironmentId_whenGetEnvironmentById_returnsEnvironmentFound() {
        // given
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        String name = "Siemens";
        Environment environment = new Environment(name, company);
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
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        company.setId(1L);
        when(environmentRepositoryMock.findAll()).thenReturn(Arrays.asList(new Environment("EnvironmentTest0", company),
                new Environment("EnvironmentTest1", company), new Environment("EnvironmentTest2", company)));

        List<Environment> actualEnvsFound = sut.getAllEnvironments(company.getId().toString());

        // Then
        verify(environmentRepositoryMock).findAll();

        assertEquals("EnvironmentTest0", actualEnvsFound.get(0).getName());
        assertEquals("EnvironmentTest1", actualEnvsFound.get(1).getName());
        assertEquals("EnvironmentTest2", actualEnvsFound.get(2).getName());
    }

    @Test
    void givenEnvironmentName_whenCreateEnvironment_returnsEnvironmentCreated() {
        // Given
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        company.setId(1L);
        String envName = "EnvironmentTest";
        ArgumentCaptor<Environment> aCaptor = ArgumentCaptor.forClass(Environment.class);

        Mockito.when(companyRepositoryMock.findById(company.getId())).thenReturn(Optional.of(company));

        // When
        sut.createEnvironment(envName, company.getId().toString());

        // Then
        verify(environmentRepositoryMock).save(aCaptor.capture());

        // Environment actEnvToBeCreated = aCaptor.getValue();
        assertEquals(envName, aCaptor.getValue().getName());
    }

    @Test
    void givenNewEnvironmentName_whenUpdateEnvironment_returnsEnvironmentNameIsUpdated() {
        // Given
        Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
        company.setId(1L);
        String name = "Siemens";
        Environment environment = new Environment(name, company);
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