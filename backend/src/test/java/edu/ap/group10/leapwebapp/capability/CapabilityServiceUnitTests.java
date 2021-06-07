package edu.ap.group10.leapwebapp.capability;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import edu.ap.group10.leapwebapp.environment.Environment;
import edu.ap.group10.leapwebapp.environment.EnvironmentRepository;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class CapabilityServiceUnitTests {

  @Mock
  private CapabilityRepository capabilityRepositoryMock;

  @Mock
  private EnvironmentRepository environmentRepositoryMock;

  @InjectMocks
  private CapabilityService sut;

  @Test
  void givenCapabilities_whenGetAllCapabilities_returnsAllCapabilites() {
    // When
    when(capabilityRepositoryMock.findAll())
        .thenReturn(Arrays.asList(new Capability("test1"), new Capability("test2"), new Capability("test3")));

    List<Capability> capabilities = sut.getAllCapabilities();

    // Then
    verify(capabilityRepositoryMock).findAll();

    assertEquals("test1", capabilities.get(0).getName());
    assertEquals("test2", capabilities.get(1).getName());
    assertEquals("test3", capabilities.get(2).getName());
  }

  @Test
  void givenEnvironmentId_whenGetAllCapabilitiesInEnvironment_returnsCapabilitiesInEnvironment() {
    // given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    String name = "Siemens";
    Environment environment = new Environment(name, company);
    environment.setId(1L);
    Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

    Capability capability1 = new Capability("Youth");
    capability1.setEnvironment(environment);
    Capability capability2 = new Capability("Trainers");
    capability2.setEnvironment(environment);
    Mockito.when(capabilityRepositoryMock.findByEnvironment(environment))
        .thenReturn(Arrays.asList(capability1, capability2));

    // when
    List<Capability> capabilities = sut.getAllCapabilitiesInEnvironment(environment.getId());

    // then
    ArgumentCaptor<Environment> environmentArgumentCaptor = ArgumentCaptor.forClass(Environment.class);
    verify(capabilityRepositoryMock).findByEnvironment(environmentArgumentCaptor.capture());

    assertEquals(capability1.getEnvironment().getId(), capabilities.get(0).getEnvironment().getId());
    assertEquals(capability2.getEnvironment().getId(), capabilities.get(1).getEnvironment().getId());

  }

  @Test
  void givenEnvironmetId_whenGetACapabilityInEnvironment_returnsCapabilityInEnvironment() {
    // given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    String name = "Siemens";
    Environment environment = new Environment(name, company);
    environment.setId(1L);
    Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

    Capability capability1 = new Capability("Youth");
    capability1.setEnvironment(environment);
    capability1.setId(1L);
    Capability capability2 = new Capability("Trainers");
    capability2.setEnvironment(environment);
    capability2.setId(2L);
    Mockito.when(capabilityRepositoryMock.findByEnvironment(environment))
        .thenReturn(Arrays.asList(capability1, capability2));

    // when
    sut.getCapability(environment.getId(), capability1.getId());
    List<Capability> capabilities = sut.getAllCapabilitiesInEnvironment(environment.getId());
    List<Capability> capabilityFound = capabilities.stream().filter(cap -> cap.getId().equals(capability1.getId()))
        .collect(Collectors.toList());

    // then
    assertEquals(capability1, capabilityFound.get(0));

  }

  @Test
  void givenCapabilityId_whenGetCapabilityById_returnsCapability() {

    // given
    Capability capability = new Capability("Youth");
    capability.setId(1L);
    Mockito.when(capabilityRepositoryMock.findById(1L)).thenReturn(Optional.of(capability));

    // when
    sut.getCapabilityById(capability.getId());

    // then
    ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    verify(capabilityRepositoryMock).findById(longArgumentCaptor.capture());

    assertEquals(longArgumentCaptor.getValue(), capability.getId());
  }

  @Test
  void givenCapabilityEnvironmentIdParentCapabilityId_whenCreateCapability_returnsCapabilityCreated() {
    // given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    String name = "Siemens";
    Environment environment = new Environment(name, company);
    environment.setId(1L);
    Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

    Capability capabilityParent = new Capability("Youth");
    capabilityParent.setEnvironment(environment);
    capabilityParent.setId(1L);
    capabilityParent.setLevel(2);

    Capability capabilityToCreate = new Capability("Trainers");
    capabilityToCreate.setEnvironment(environment);
    capabilityToCreate.setId(2L);

    Mockito.when(capabilityRepositoryMock.findByEnvironment(environment))
        .thenReturn(Arrays.asList(capabilityParent, capabilityToCreate));

    // when
    sut.createCapability(environment.getId(), capabilityParent.getId(), capabilityToCreate);
    List<Capability> capabilitiesList = sut.getAllCapabilitiesInEnvironment(environment.getId());
    List<Capability> capabilityParentList = capabilitiesList.stream()
        .filter(cap -> cap.getId().equals(capabilityParent.getId())).collect(Collectors.toList());

    capabilityToCreate.setLevel(capabilityParentList.get(0).getLevel() + 1);
    capabilityToCreate.setEnvironment(environment);
    capabilityToCreate.setParent(capabilityParentList.get(0));

    // then
    ArgumentCaptor<Capability> capabilityArgumentCaptor = ArgumentCaptor.forClass(Capability.class);
    verify(capabilityRepositoryMock).save(capabilityArgumentCaptor.capture());

    assertEquals(capabilityArgumentCaptor.getValue().getName(), capabilityToCreate.getName());
    assertEquals(3, capabilityArgumentCaptor.getValue().getLevel());
    assertEquals(capabilityArgumentCaptor.getValue().getParent(), capabilityParent);
    assertEquals(capabilityArgumentCaptor.getValue().getEnvironment(), environment);
    assertEquals(capabilityArgumentCaptor.getValue(), capabilityToCreate);

  }

  @Test
  void givenCapabilityEnvironmentIdCapabilityId_whenUpdateCapability_returnsUpdatedCapability() {
    // given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    String name = "Siemens";
    Environment environment = new Environment(name, company);
    environment.setId(1L);
    Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

    Capability capability = new Capability("Youth");
    capability.setEnvironment(environment);
    capability.setId(1L);
    capability.setLevel(2);

    String newNameCapability = "Philips";
    Integer newResourcesQualityCapability = 5;

    Mockito.when(capabilityRepositoryMock.findByEnvironment(environment)).thenReturn(Arrays.asList(capability));

    // when
    sut.updateCapability(environment.getId(), capability.getId(), capability);

    List<Capability> capabilitiesList = sut.getAllCapabilitiesInEnvironment(environment.getId());
    List<Capability> capabilityFound = capabilitiesList.stream().filter(cap -> cap.getId().equals(capability.getId()))
        .collect(Collectors.toList());

    // then
    capabilityFound.get(0).setName(newNameCapability);
    capabilityFound.get(0).setResourcesQuality(newResourcesQualityCapability);

    ArgumentCaptor<Capability> capabilityArgumentCaptor = ArgumentCaptor.forClass(Capability.class);
    verify(capabilityRepositoryMock).save(capabilityArgumentCaptor.capture());

    assertEquals(capabilityArgumentCaptor.getValue().getName(), newNameCapability);
    assertEquals(capabilityArgumentCaptor.getValue().getResourcesQuality(), newResourcesQualityCapability);
  }

  @Test
  void givenEnvironmentIdCapabilityId_whenDeleteCapability_returnsCapabilityIsDeleted() {
    // given
    Company company = new Company("1", "Test Company", "sv@gmail.com", "kerkstraat", 3, 5, "Mortsel", "België", "HR", "?");
    String name = "Siemens";
    Environment environment = new Environment(name, company);
    environment.setId(1L);
    Mockito.when(environmentRepositoryMock.findById(1L)).thenReturn(Optional.of(environment));

    Capability capability = new Capability("Youth");
    capability.setEnvironment(environment);
    capability.setId(1L);
    capability.setLevel(2);

    Mockito.when(capabilityRepositoryMock.findByEnvironment(environment)).thenReturn(Arrays.asList(capability));

    // when
    sut.deleteCapability(environment.getId(), capability.getId());

    List<Capability> capabilitiesList = sut.getAllCapabilitiesInEnvironment(environment.getId());
    List<Capability> capabilityFound = capabilitiesList.stream().filter(cap -> cap.getId().equals(capability.getId()))
        .collect(Collectors.toList());

    // then
    capabilityFound.get(0);
    ArgumentCaptor<Capability> capabilityArgumentCaptor = ArgumentCaptor.forClass(Capability.class);
    verify(capabilityRepositoryMock).delete(capabilityArgumentCaptor.capture());

    assertEquals(capability.getName(), capabilityArgumentCaptor.getValue().getName());

  }

}
